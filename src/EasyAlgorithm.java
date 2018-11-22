import java.util.*;

public class EasyAlgorithm {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
/*        treeNode.left = new TreeNode(5);
        treeNode.right = new TreeNode(1);
        treeNode.left.left = new TreeNode(6);
        treeNode.left.right = new TreeNode(2);
        treeNode.left.right.left = new TreeNode(7);
        treeNode.left.right.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(9);
        treeNode.right.right = new TreeNode(8);*/

        TreeNode root = new TreeNode(2);
/*        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);*/

        System.out.println(leafSimilar(treeNode, root));

    }

    /**
     * <p>
     * 771. Jewels and Stones
     * <p>
     * tip:字符包含大小写
     *
     * @param J 字符串里面的字符代表宝石类型，类型不重复("aA")
     * @param S 字符串里面的字符代表石头类型,类型可能会重复("aAAbbbb")
     * @return 石头里面有多少个是宝石(3)
     * @see #numJewelsInStonesBetter(String, String)
     */
    public static int numJewelsInStones(String J, String S) {
        int result = 0;
        HashSet<Character> hashSet = new HashSet<>(S.length());
        for (char c : J.toCharArray()) {
            hashSet.add(c);
        }

        for (char c : S.toCharArray()) {
            if (hashSet.contains(c)) {
                result++;
            }
        }
        return result;
    }

    public static int numJewelsInStonesBetter(String J, String S) {
        int result = 0;
        for (char c : S.toCharArray()) {
            if (J.indexOf(c) != -1) {
                result++;
            }
        }
        return result;
    }

    /**
     * 929. Unique Email Addresses
     * <p>
     * 判断邮箱地址不一样的个数。在"@"前面的"."可以忽略掉，"alice.z@leetcode.com"和"alicez@leetcode.com"是指向同一个地址的
     * 在"@"前面的"+"号到"@"之间的字符都是无效字符，"my+name@email.com"和"my@email.com"是指向同一个地址
     * </p>
     *
     * @param emails 邮箱地址数组
     * @return 邮箱不是指向同一个地址的个数
     */
    public static int numUniqueEmails(String[] emails) {
        HashSet<String> hashSet = new HashSet<>();
        for (String string : emails) {
            if (string.contains("+")) {
                //先删掉"+"到"@"之间的无效字符
                string = string.substring(0, string.indexOf("+")) + string.substring(string.indexOf("@"), string.length());
            }
            while (string.indexOf(".") < string.indexOf("@")) {
                //如果"@"前面有"."的则删掉
                string = string.substring(0, string.indexOf(".")) + string.substring(string.indexOf(".") + 1, string.length());
            }
            hashSet.add(string);
        }
        return hashSet.size();
    }

    /**
     * 804. Unique Morse Code Words
     * <p>
     * 摩斯密码，每个小写字母都有对应的字符串表示，transform代表了从a到z的字符串表示。
     * </p>
     * <p>
     * Input: words = ["gin", "zen", "gig", "msg"]
     * Output: 2
     * Explanation:
     * The transformation of each word is:
     * "gin" -> "--...-."
     * "zen" -> "--...-."
     * "gig" -> "--...--."
     * "msg" -> "--...--."
     * <p>
     * There are 2 different transformations, "--...-." and "--...--.".
     * </p>
     *
     * @return words翻译为摩斯密码后字符串不同的个数
     */
    public int uniqueMorseRepresentations(String[] words) {
        HashSet<String> hashSet = new HashSet<>();
        String[] transform = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            result.delete(0, result.length());
            for (char c : word.toCharArray()) {
                result.append(transform[((int) c) - (int) 'a']);
            }
            hashSet.add(result.toString());
        }
        return hashSet.size();
    }

    /**
     * 905. Sort Array By Parity
     * <p>
     * 给一个都是正整数的数组，要求重新排列为前面都是偶数，接着都是负数的数组，不要求排序
     *
     * @see #sortArrayByParityBetter(int[])
     */
    public int[] sortArrayByParity(int[] A) {
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();
        int[] result;

        for (Integer integer : A) {
            if (integer % 2 == 0) {
                evenNumbers.add(integer);
            } else {
                oddNumbers.add(integer);
            }
        }

        result = new int[evenNumbers.size() + oddNumbers.size()];
        int size = evenNumbers.size();
        for (int i = 0; i < size; i++) {
            result[i] = evenNumbers.get(i);
        }
        for (int i = 0; i < oddNumbers.size(); i++) {
            result[i + size] = oddNumbers.get(i);
        }
        return result;
    }

    public int[] sortArrayByParityBetter(int[] A) {
        int i = 0, j = A.length - 1;
        while (i < j) {
            if (A[i] % 2 == 0) {
                i++;
            } else {
                int temp = A[j];
                A[j] = A[i];
                A[i] = temp;
                j--;
            }
        }
        return A;
    }

    /**
     * 832. Flipping an Image
     * <p>
     * 给一个二维数组，把每一行的元素翻转过来，即[0,1,1,0,0]翻转后变为[0,0,1,1,0]。再把整个数组的0变为1,1变为0。
     * <p>
     * Input: [[1,1,0],[1,0,1],[0,0,0]]
     * Output: [[1,0,0],[0,1,0],[1,1,1]]
     * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
     * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
     *
     * @param A 一个只包含0和1的二维数组
     * @see #flipAndInvertImageBetter(int[][])
     */
    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int x = 0, y = A[i].length - 1;     //在数组的左右两边同时往中间遍历
            while (x < y) {
                if (A[i][x] != A[i][y]) {
                    //如果数据不一样就翻转
                    int temp = A[i][y];
                    A[i][y] = A[i][x];
                    A[i][x] = temp;
                }

                //把0变为1,1变为0
                if (A[i][x] == 0) {
                    A[i][x] = 1;
                } else {
                    A[i][x] = 0;
                }

                if (A[i][y] == 0) {
                    A[i][y] = 1;
                } else {
                    A[i][y] = 0;
                }
                x++;
                y--;
            }

            if (x == y) {
                //如果数组是奇数的话，再把中间那个数翻转
                if (A[i][x] == 0) {
                    A[i][x] = 1;
                } else {
                    A[i][x] = 0;
                }
            }
        }
        return A;
    }

    public int[][] flipAndInvertImageBetter(int[][] A) {
        for (int[] row : A) {
            for (int left = 0, right = row.length - 1; left <= right; left++, right--) {
                int temp = row[left];
                row[left] = row[right] ^ 1;     //翻转的同时进行异或取反
                row[right] = temp ^ 1;
            }
        }
        return A;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 617. Merge Two Binary Trees
     * <p>
     * 给两个二叉树，要求把他们的对应结点的值相加
     * <p>
     * Input:
     * Tree 1                     Tree 2
     * 1                         2
     * / \                       / \
     * 3   2                     1   3
     * /                           \   \
     * 5                             4   7
     * Output:
     * Merged tree:
     * 3
     * / \
     * 4   5
     * / \   \
     * 5   4   7
     *
     * @see #mergeTreesBetter(TreeNode, TreeNode)
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 != null) {
            return t2;
        }
        mergeTree(t1, t2);
        return t1;
    }

    private void mergeTree(TreeNode t1, TreeNode t2) {
        if (t1 != null && t2 != null) {
            t1.val = t1.val + t2.val;
            if (t1.left == null && t2.left != null) {
                t1.left = new TreeNode(0);
            }
            mergeTree(t1.left, t2.left);

            if (t1.right == null && t2.right != null) {
                t1.right = new TreeNode(0);
            }
            mergeTree(t1.right, t2.right);
        } else if (t1 != null) {
            mergeTree(t1.left, null);
            mergeTree(t1.right, null);
        }
    }

    public TreeNode mergeTreesBetter(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    private TreeNode mTreeNode;

    /**
     * 700:https://leetcode.com/problems/search-in-a-binary-search-tree/description/
     * <p>
     * 给定一个二叉搜索树（左节点的值比根节点小，又节点的值比根节点大），要求在二叉树中找到值为val的结点并返回
     */
    public TreeNode searchBST(TreeNode root, int val) {
        //          4
        //      2       7
        //    1    3
        //val为2，则返回
        //      2
        //    1    3
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val < val) {
            if ((mTreeNode = searchBST(root.right, val)) != null) {
                return mTreeNode;
            }
        } else {
            if ((mTreeNode = searchBST(root.left, val)) != null) {
                return mTreeNode;
            }
        }

        return null;
    }

    /**
     * 852. Peak Index in a Mountain Array
     * <p>
     * 给定一个数组，长度限制为3<=length<=10000，值限制为0<=A[i]<=10^6。
     * 数组存在一个数满足A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]，请求出i为多少
     * <p>
     * Input: [0,2,1,0]
     * Output: 1
     */
    public static int peakIndexInMountainArray(int[] A) {
        //其实就是找出数组中最大那个数的下标，因为数组是先按递增到最大值，然后再递减的，所以也是可以用二分查找的
        int start = 0, end = A.length - 1;
        int middle;
        while (start <= end) {
            middle = start + (end - start) / 2;
            if (A[middle] > A[middle - 1] && A[middle] > A[middle + 1]) {
                return middle;
            } else if (A[middle] > A[middle - 1]) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 922 https://leetcode.com/problems/sort-array-by-parity-ii/description/
     * <p>
     * 给定一个数组，数组的长度为偶数，数组里面的值一半为奇数，一半为偶数，要求把奇数放在数组下标为奇数的位置，偶数放在数组下标为偶数的位置,顺序不定
     * <p>
     * Input: [4,2,5,7]
     * <p>
     * Output: [4,5,2,7]
     * <p>
     * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
     *
     * @see #sortArrayByParityBetter(int[])
     */
    public static int[] sortArrayByParityII(int[] A) {
        int start = 0, end = A.length - 1;
        while (start < end) {
            while (start < end && A[start] % 2 == start % 2) {
                start++;
            }
            //start代表的下标和值的奇偶数不一致
            if (start < end) {
                int i = end;
                while (i > start && A[i] % 2 != start % 2) {
                    i--;        //找到下标为i的值和start位置的奇偶性一样的
                }
                swap(A, start, i);
                start++;
            }

            while (start < end && A[end] % 2 == end % 2) {
                end--;
            }

            if (start < end) {
                int i = start;
                while (i < end && A[i] % 2 != end % 2) {
                    i++;
                }
                swap(A, end, i);
                end--;
            }
        }
        return A;
    }

    public static int[] sortArrayByParityIIBetter(int[] A) {
        int j = 1;      //i为偶数的下标，j为奇数的下标
        for (int i = 0; i < A.length; i += 2) {
            if (A[i] % 2 != 0) {
                //如果数组偶数下标的值为奇数，则找到数组奇数下标的值为偶数的位置，交换即可。因为数组本来是一半奇数一般偶数的，所以肯定能找到。
                while (A[j] % 2 == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    /**
     * 867:https://leetcode.com/problems/transpose-matrix/description/
     * <p>
     * 将二维数组的行和列颠倒过来
     * </p>
     */
    public int[][] transpose(int[][] A) {
        int[][] array = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                array[j][i] = A[i][j];
            }
        }
        return array;
    }

    private List<Integer> mIntegers = new ArrayList<>();

    /**
     * 589:https://leetcode.com/problems/n-ary-tree-preorder-traversal/description/
     * <p>
     * 给定一棵N叉数，要求从前序遍历求出结点的值
     */
    public List<Integer> preorder(Node root) {
        // For example, given a 3-ary tree:
        //               1
        //       3       2         4
        //   5       6
        //Return its preorder traversal as: [1,3,5,6,2,4].
        preorderNode(root);
        return mIntegers;
    }

    public void preorderNode(Node root) {
        if (root == null) {
            return;
        }
        mIntegers.add(root.val);
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                preorder(root.children.get(i));
            }
        }
    }

    /**
     * 590:https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
     * <p>
     * 后序遍历N叉树
     */
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return mIntegers;
        }
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                postorder(root.children.get(i));
            }
        }
        mIntegers.add(root.val);
        return mIntegers;
    }

    /**
     * 559:https://leetcode.com/problems/maximum-depth-of-n-ary-tree/description/
     * <p>
     * 求N叉树的最大深度
     * <p>
     * 思路：假如只有一个结点，那最大深度为1；假如有多个结点，那最大深度为根结点+深度最大的子结点
     */
    public int maxDepth(Node root) {
        //                  1
        //          3       2       4
        //      5       6
        //return 3
        if (root == null) {
            return 0;
        }
        if (root.children == null) {
            return 1;
        }
        int maxDepth = 0;
        for (Node node : root.children) {
            maxDepth = Math.max(maxDepth, maxDepth(node));  //求出深度最大的那个子结点的深度
        }
        return 1 + maxDepth;    //当前结点的深度最大的那个子结点的深度加上自己（1）
    }

    class Node {
        int val;
        List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private int[] intArray;

    /**
     * 198:https://leetcode.com/problems/house-robber/description/
     * <p>
     * 小偷去到一条街上准备偷房子里面的钱，但是相连的房子有连接的安全系统，如果相连的房子在同一晚被闯入则会自动报警。求出小偷最多能偷出多少钱。
     * </p>
     * Input: [1,2,3,1]
     * Output: 4
     * <p>
     * Input: [5,7,1,2,4]
     * Output: 11
     *
     * @param nums 代表每个房子拥有的钱
     */
    public int rob(int[] nums) {
        intArray = new int[nums.length];
        Arrays.fill(intArray, -1);
        return robMoney(nums, nums.length - 1);
    }

    private int robMoney(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }
        if (intArray[index] != -1) {
            return intArray[index];
        }
        //从最后往前推算，要么选择偷index和index-2的房子，要么偷index-1的房子
        int result = Math.max(robMoney(nums, index - 2) + nums[index], robMoney(nums, index - 1));
        intArray[index] = result;
        return result;
    }

    /**
     * 121:https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
     * <p>
     * 给一个数组代表每天的股价，给你一次买进卖出（必须先买进才能卖出，而且不能在同一天）的机会（也可以不买），求最大能赚到的钱。
     * </p>
     * Input: [7,1,5,3,6,4]
     * Output: 5。在价格为1的时候买进，在价格为6的时候卖出，能赚6块。
     * <p>
     * Input: [7,6,4,3,1]
     * Output: 0。怎么买都是亏的，所以不买
     * <p>
     * 把每天的股价用坐标系画出来连成线，就会发现其实就是求y值相差最大的两个点的距离。
     * </p>
     */
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int result = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > result) {
                result = price - minPrice;
            }
        }
        return result;
    }

    /**
     * 942:https://leetcode.com/problems/di-string-match/description/
     * <p>
     * Input: "IDID"
     * Output: [0,4,1,3,2]
     * </p>
     * 不明白这题想考什么
     *
     * @param S 里面只有I（代表递增）和D（代表递减）两种字母
     * @return A 返回一个整型数组，要求当S[i]=="I"时，A[i]<[i+1]；当S[i]=="D"时，A[i]>A[i+1]。
     */
    public int[] diStringMatch(String S) {
        int[] result = new int[S.length() + 1];
        int increase = 0, decrease = S.length();
        if (S.charAt(0) == 'I') {
            result[0] = increase++;
        } else {
            result[0] = decrease--;
        }

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == 'I') {
                result[i] = increase++;
            } else {
                result[i] = decrease--;
            }
        }

        if (S.charAt(S.length() - 1) == 'I') {
            result[S.length()] = increase;
        } else {
            result[S.length()] = decrease;
        }
        return result;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 876：https://leetcode.com/problems/middle-of-the-linked-list/description/
     * <p>
     * Input: [1,2,3,4,5]
     * Output: Node 3 from this list (Serialization: [3,4,5])
     * <p>
     * Input: [1,2,3,4,5,6]
     * Output: Node 4 from this list (Serialization: [4,5,6])
     *
     * @return 单链表的中间节点，如果有两个中间节点则返回第二个
     */
    public ListNode middleNode(ListNode head) {
        int count = 0;
        for (ListNode listNode = head; listNode != null; listNode = listNode.next) {
            count++;
        }
        for (int i = 0; i < count / 2; i++) {
            head = head.next;
        }
        return head;
    }

    /**
     * 采用快慢指针的方法，快指针每次走两步，慢指针每次走一步，当快指针走到终点时，慢指针则刚好到中点
     */
    private ListNode middleNodeBetter(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 821:https://leetcode.com/problems/shortest-distance-to-a-character/description/
     * <p>
     * 给定一个字符串S，和一个字符C，求出字符串中每个字符距离字符C的最短距离。S的长度为[1,10000]，C保证会在S中有，全部字符都是小写。
     * <p>
     * Input: S = "loveleetcode", C = 'e'
     * <p>
     * Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]
     */
    public static int[] shortestToChar(String S, char C) {
        int[] result = new int[S.length()];
        int index = -10000; //代表字符串在S中所在的位置，初始值为字符串的最大长度负数
        //先从头到尾遍历一次，这样可以求出字符距离左边的C（如果有的话）的距离
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C) {
                index = i;
            }
            result[i] = i - index;
        }

        index = 10000;      //重新赋值为字符串的最大长度值
        //然后从尾到头遍历一次，可以求出字符距离右边的C（如果有的话）的距离，取字符离左边和右边最小的距离值。
        //因为C是在S中存在的，所以要么在字符左边，要么在字符右边，要么是自己，要么左右边都有
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == C) {
                index = i;
            }
            result[i] = Math.min(result[i], index - i);
        }
        return result;
    }

    private static List<Integer> sFirstIntList = new ArrayList<>();
    private static List<Integer> sSecondIntList = new ArrayList<>();

    /**
     * 872:https://leetcode.com/problems/leaf-similar-trees/description/
     * <p>
     * 给定两个二叉树，判断它们的叶子序列是否相同
     */
    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //                        3
        //               5                   1
        //         6          2          9       8
        //               7        4
        //则叶子序列为(6,7,4,9,8)
        getTreeLeaf(root1, sFirstIntList);
        getTreeLeaf(root2, sSecondIntList);
        if (sFirstIntList.size() != sSecondIntList.size()) {
            return false;
        }
        for (Integer aSFirstIntList : sFirstIntList) {
            if (!sSecondIntList.contains(aSFirstIntList)) {
                return false;
            }
        }
        return sFirstIntList.equals(sSecondIntList);
    }

    private static void getTreeLeaf(TreeNode treeNode, List<Integer> list) {
        if (treeNode == null) {
            return;
        }
        if (treeNode.left == null && treeNode.right == null) {
            list.add(treeNode.val);
            return;
        }

        getTreeLeaf(treeNode.left, list);
        getTreeLeaf(treeNode.right, list);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
