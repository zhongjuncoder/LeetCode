
import java.util.*;

public class EasyAlgorithm {

    public static void main(String[] args) {
        rotatedDigits(100);
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

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    '}';
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
     *
     * @see #maxProfitUpgrade(int[])
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

    /**
     * 766:https://leetcode.com/problems/toeplitz-matrix/description/
     * <p>
     * 给定一个M*N的二维数组，判断这个数组从左上角到右下角的每个对角线是否都具有相同的元素
     * </p>
     * matrix = [
     * [1,2,3,4],
     * [5,1,2,3],
     * [9,5,1,2]
     * ]
     * Output: True
     * <p>
     * matrix = [
     * [1,2],
     * [2,2]
     * ]
     * Output: False
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (row > 0 && column > 0 && matrix[row][column] != matrix[row - 1][column - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 884:https://leetcode.com/problems/uncommon-words-from-two-sentences/description/
     * <p>
     * 找出两个字符串中只出现一次的单词
     * </p>
     * Input: A = "this apple is sweet", B = "this apple is sour"
     * Output: ["sweet","sour"]
     * <p>
     * Input: A = "apple apple", B = "banana"
     * Output: ["banana"]
     */
    public String[] uncommonFromSentences(String A, String B) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String a : A.split(" ")) {
            hashMap.put(a, hashMap.getOrDefault(a, 0) + 1);
        }
        for (String b : B.split(" ")) {
            hashMap.put(b, hashMap.getOrDefault(b, 0) + 1);
        }

        List<String> list = new ArrayList<>();
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) == 1) {
                list.add(key);
            }
        }

        return list.toArray(new String[0]);
    }

    /**
     * 868:https://leetcode.com/problems/binary-gap/description/
     * <p>
     * 给一个整数[1,10^9]，求出它的二进制中两个1相距的最长距离
     * </p>
     * Input: 22
     * Output: 2
     * Explanation:
     * 22 in binary is 0b10110.
     * <p>
     * Input: 8
     * Output: 0
     * Explanation:
     * 8 in binary is 0b1000.
     *
     * @see #binaryGapBetter(int)
     */
    public static int binaryGap(int N) {
        String string = Integer.toBinaryString(N);
        int max = 0, start = 0;
        System.out.println(string);
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '1') {
                max = Math.max(max, i - start);
                start = i;
            }
        }
        return max;
    }

    private int binaryGapBetter(int N) {
        int max = 0, start = -1;
        for (int i = 0; i < 32; i++) {
            if (((N >> i) & 1) == 1) {
                if (start != -1) {
                    max = Math.max(max, i - start);
                }
                start = i;
            }
        }
        return max;
    }

    private TreeNode current;

    /**
     * 897:https://leetcode.com/problems/increasing-order-search-tree/description/
     * <p>
     * 给定一颗平衡二叉树（左小右大），重新构造该数令其的根结点值最小（右大），并且没有左结点
     * </p>
     * Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]
     * <p>
     * //        5
     * //      / \
     * //     3    6
     * //    / \    \
     * //   2   4    8
     * //  /        / \
     * // 1        7   9
     * <p>
     * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
     * <p>
     * //  1
     * //   \
     * //    2
     * //     \
     * //      3
     * //       \
     * //        4
     * //         \
     * //          5
     * //           \
     * //            6
     * //             \
     * //              7
     * //               \
     * //                8
     * //                 \
     * //                  9
     */
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode(0);
        current = ans;
        rearrangeBST(root);
        return ans.right;
    }

    private void rearrangeBST(TreeNode root) {
        if (root == null) {
            return;
        }
        rearrangeBST(root.left);
        root.left = null;
        current.right = root;
        current = root;
        rearrangeBST(root.right);
    }

    private static void printTreeNode(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.val);
        printTreeNode(treeNode.left);
        printTreeNode(treeNode.right);
    }

    private List<List<Integer>> mLists = new ArrayList<>();

    /**
     * 492:https://leetcode.com/problems/n-ary-tree-level-order-traversal/description/
     * <p>
     * 给定一颗N叉树，获取从上到下，从左到右各个层级结点的值
     * </p>
     * //                       1
     * //           3           2           4
     * //       5       6
     * output:[
     * [1],
     * [3,2,4],
     * [5,6]
     * ]
     */
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return mLists;
        }
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        mLists.add(list);
        getNodeValue(root.children);
        return mLists;
    }

    private void getNodeValue(List<Node> children) {
        if (children == null || children.isEmpty()) {
            return;
        }

        List<Integer> list = new ArrayList<>(children.size());
        List<Node> nodeList = new ArrayList<>();
        for (Node node : children) {
            list.add(node.val);
            nodeList.addAll(node.children);
        }
        mLists.add(list);
        getNodeValue(nodeList);
    }

    /**
     * 917:https://leetcode.com/problems/reverse-only-letters/description/
     * <p>
     * 翻转字母。给定一个字符串，要求将里面的字母翻转过来，其他字符的位置不变。
     * </p>
     * Input: "ab-cd"
     * Output: "dc-ba"
     * <p>
     * Input: "Test1ng-Leet=code-Q!"
     * Output: "Qedo1ct-eeLg=ntse-T!
     */
    public String reverseOnlyLetters(String S) {
        int i = 0, j = S.length() - 1;
        char[] chars = S.toCharArray();
        while (i < j) {
            while (i < S.length() - 1 && !Character.isLetter(S.charAt(i))) {
                i++;
            }
            while (0 <= j && !Character.isLetter(S.charAt(j))) {
                j--;
            }
            if (i < j) {
                char c = chars[i];
                chars[i] = chars[j];
                chars[j] = c;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    /**
     * 496:https://leetcode.com/problems/next-greater-element-i/description/
     * <p>
     * 给定两个数组，数组里面的元素不重复，第一个数组是第二个数组的子集。
     * 要求将第一个数组的元素改为第二个数组同样元素位置之后第一个比它大的数，如果没有则改为-1。
     * </p>
     * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * Output: [-1,3,-1]
     * Explanation:4在num2中后面的数是2，比它小，则改为-1；1在num2后是3，比它大，改为3；2在num2后没有元素，改为-1
     *
     * @see #nextGreaterElementBetter(int[], int[])
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int index = 0;
        while (index < nums1.length) {
            boolean flag = false;
            for (int i = 0; i < nums2.length; i++) {
                if (nums2[i] == nums1[index]) {
                    if (i + 1 < nums2.length && nums2[i + 1] > nums1[index]) {
                        nums1[index] = nums2[i + 1];
                    } else {
                        for (int j = i + 2; j < nums2.length; j++) {
                            if (nums2[j] > nums1[index]) {
                                nums1[index] = nums2[j];
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            nums1[index] = -1;
                        }
                    }
                    break;
                }
            }
            index++;
        }
        return nums1;
    }

    public int[] nextGreaterElementBetter(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            hashMap.put(nums2[i], i);
        }

        int index = 0;
        while (index < nums1.length) {
            boolean flag = false;
            for (int i = hashMap.get(nums1[index]) + 1; i < nums2.length; i++) {
                if (nums1[index] < nums2[i]) {
                    nums1[index] = nums2[i];
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                nums1[index] = -1;
            }
            index++;
        }
        return nums1;
    }

    /**
     * 937:https://leetcode.com/problems/reorder-log-files/description/
     * <p>
     * 重新排列日志文件。给定一个日志字符串数组，每个日志都是以空格分开的单词串（包含数字），第一个单词为标识符，代表标识符后面的都是小写字母或者数字。
     * 要求将日志文件重新排序，标识符后面都是字母的按字典排序再前面（排序不包括标识符），数字的按原来的顺序排在后面。
     *
     * @param logs 0 <= logs.length <= 100      3 <= logs[i].length <= 100
     * @see #reorderLogFilesBetter(String[])
     */
    public String[] reorderLogFiles(String[] logs) {
        List<String> result = new ArrayList<>(logs.length);
        List<String> digitList = new ArrayList<>();
        for (String s : logs) {
            int index = s.indexOf(" ");
            if (Character.isDigit(s.charAt(index + 1))) {
                digitList.add(s);
            } else {
                result.add(s);
            }
        }
        result.sort(Comparator.comparing(o -> o.substring(o.indexOf(" ") + 1, o.length())));
        result.addAll(digitList);
        return result.toArray(logs);
    }

    public static String[] reorderLogFilesBetter(String[] logs) {
        Arrays.sort(logs, (o1, o2) -> {
            int oneIndex = o1.indexOf(" ");
            int secondIndex = o2.indexOf(" ");
            boolean oneIsDigit = Character.isDigit(o1.charAt(oneIndex + 1));
            boolean secondIsDigit = Character.isDigit(o2.charAt(secondIndex + 1));
            if (!oneIsDigit && !secondIsDigit) {
                return o1.substring(oneIndex + 1, o1.length()).compareTo(o2.substring(secondIndex + 1, o2.length()));
            }
            return oneIsDigit ? (secondIsDigit ? 0 : 1) : -1;
        });
        return logs;
    }

    /**
     * 824:https://leetcode.com/problems/goat-latin/description/
     * <p>
     * 给定一个一个字符串，每个单词以空格隔开。
     * 要求：1，如果单词以元音开头（a,e,i,o,or,u)，就把"ma"添加到单词的结尾。2，如果不是已元音开头，则把第一个字母移到结尾，然后添加"ma"到结尾。
     * 3，当前是第几个单词就添加几个"a"在结尾。
     * </p>
     * Input: "I speak Goat Latin"
     * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
     *
     * @see #toGoatLatinBetter(String)
     */
    public String toGoatLatin(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder aBuilder = new StringBuilder("a");
        String[] letters = S.split(" ");
        for (String letter : letters) {
            if (letter.startsWith("a") || letter.startsWith("e") || letter.startsWith("i") || letter.startsWith("o") || letter.startsWith("u")
                    || letter.startsWith("A") || letter.startsWith("E") || letter.startsWith("I") || letter.startsWith("O") || letter.startsWith("U")) {
                stringBuilder.append(letter);
            } else {
                stringBuilder.append(letter, 1, letter.length());
                stringBuilder.append(letter, 0, 1);
            }
            stringBuilder.append("ma");
            stringBuilder.append(aBuilder);
            stringBuilder.append(" ");

            aBuilder.append("a");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    public String toGoatLatinBetter(String S) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder aString = new StringBuilder("a");
        HashSet<Character> hashSet = new HashSet<>();
        hashSet.add('a');
        hashSet.add('e');
        hashSet.add('i');
        hashSet.add('o');
        hashSet.add('u');
        hashSet.add('A');
        hashSet.add('E');
        hashSet.add('I');
        hashSet.add('O');
        hashSet.add('U');

        String[] letters = S.split(" ");
        for (String letter : letters) {
            if (hashSet.contains(letter.charAt(0))) {
                stringBuilder.append(letter);
            } else {
                stringBuilder.append(letter, 1, letter.length());
                stringBuilder.append(letter, 0, 1);
            }
            stringBuilder.append("ma");
            stringBuilder.append(aString);
            stringBuilder.append(" ");

            aString.append("a");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    /**
     * 896:https://leetcode.com/problems/monotonic-array/description/
     * <p>
     * 判断一个数组是不是单调递增或者单调递减(如果相邻的两个数是同样的也可以)
     */
    public boolean isMonotonic(int[] A) {
        byte b = -1;
        for (int i = 1; i < A.length; i++) {
            if (b == -1) {
                if (A[i] > A[i - 1]) {
                    b = 1;
                } else if (A[i] < A[i - 1]) {
                    b = 0;
                }
            } else {
                if (b == 1) {
                    if (A[i] < A[i - 1]) {
                        return false;
                    }
                } else {
                    if (A[i] > A[i - 1]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 812:https://leetcode.com/problems/largest-triangle-area/description/
     * <p>
     * 给定一个二维数组，代表在二维坐标上的点，请求出这些点组成的三角形面积最大是多少
     * </p>
     */
    public double largestTriangleArea(int[][] points) {
        double area = 0;
        int length = points.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    area = Math.max(area, calculateArea(points[i], points[j], points[k]));
                }
            }
        }
        return area;
    }

    /**
     * 知道三个点，所以可以通过三阶行列式来求解组成的三角形的面积
     * //         P[0]  P[1]    1
     * // S=0.5*{ Q[0]  Q[1]    1 }
     * //         R[0]  R[1]    1
     * 解法为正对角线相乘的值总和-负对角线相乘的值总和。即P[0]*Q[1]*1+P[1]*1*R[0]+1*Q[0]*R[1]-(R[0]*Q[1]*1+R[1]*1*P[0]+1*Q[0]*P[1])
     *
     * @param P P[0]代表横坐标，P[1]代表纵坐标
     * @return 三个点组成的三角形的面积
     */
    private double calculateArea(int[] P, int[] Q, int[] R) {
        return 0.5 * Math.abs(P[0] * Q[1] + Q[0] * R[1] + R[0] * P[1] - P[1] * Q[0] - Q[1] * R[0] - R[1] * P[0]);
    }

    /**
     * 784:https://leetcode.com/problems/letter-case-permutation/description/
     * <p>
     * 给定一个只包含字母和数字的字符串，求出它的字母大小写的全排列。
     * </p>
     * Input: S = "a1b2"
     * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
     */
    public static List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        list.add(S);
        permutationLetterCase(S, 0, list);
        return list;
    }

    public static void permutationLetterCase(String string, int index, List<String> stringList) {
        if (index >= string.length()) {
            return;
        }
        char c = string.charAt(index);
        if (Character.isDigit(c)) {
            permutationLetterCase(string, ++index, stringList);
            return;
        }
        List<String> list = new ArrayList<>();
        for (String s : stringList) {
            StringBuilder stringBuilder = new StringBuilder(s.substring(0, index));
            if (Character.isLowerCase(c)) {
                stringBuilder.append(Character.toUpperCase(c));
            } else {
                stringBuilder.append(Character.toLowerCase(c));
            }
            if (index + 1 < string.length()) {
                stringBuilder.append(s, index + 1, s.length());
            }
            list.add(stringBuilder.toString());
        }
        stringList.addAll(list);
        permutationLetterCase(string, ++index, stringList);
    }

    public static List<String> letterCasePermutationBetter(String S) {
        List<String> list = new ArrayList<>();
        permutationLetterCase(S, "", 0, list);
        return list;
    }

    private static void permutationLetterCase(String S, String letter, int index, List<String> list) {
        if (letter.length() == S.length()) {
            list.add(letter);
            return;
        }
        char c = S.charAt(index);
        permutationLetterCase(S, letter + c, ++index, list);
        if (Character.isLowerCase(c)) {
            permutationLetterCase(S, letter + Character.toUpperCase(c), index, list);
        } else if (Character.isUpperCase(c)) {
            permutationLetterCase(S, letter + Character.toLowerCase(c), index, list);
        }

    }

    /**
     * 258:https://leetcode.com/problems/add-digits/description/
     * <p>
     * 给定一个非负整数，求重复将它各位的数相加后(结果作为下次的数)只剩下一位的数。
     * </p>
     * Input: 38
     * Output: 2
     * Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
     *
     * @see #addDigitsBetter(int)
     */
    public static int addDigits(int num) {
        if (num < 10) {
            return num;
        }
        int result = 0;
        while (true) {
            result += num % 10;
            num /= 10;
            if (num == 0) {
                if (result < 10) {
                    break;
                }
                num = result;
                result = 0;
            }
        }
        return result;
    }

    public static int addDigitsBetter(int num) {
        if (num < 10) {
            return num;
        }
        return num % 9 == 0 ? 9 : num % 9;
    }

    /**
     * 283:https://leetcode.com/problems/move-zeroes/description/
     * <p>
     * 给定一个数组，要求将数组中的0放到后面，其他元素保持原来的顺序放在前面
     *
     * @see #moveZeroesBetter(int[])
     */
    public void moveZeroes(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] != 0) {
                    break;
                }
                swap(nums, j + 1, j);
            }
        }
    }

    public void moveZeroesBetter(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index] = nums[i];
                index++;
            }
        }
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    /**
     * 748:https://leetcode.com/problems/shortest-completing-word/description/
     * 给定一个字符串licensePlate，里面包含数字，空格，字母（大小写）。再给定一个字符串数组，里面包含都是小写的字母。
     * 要求在字符串数组中找到最短能匹配字符串中的字母（忽略大小写）的字符串。如果长度一样，返回第一个出现的。
     * <p>
     * Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
     * Output: "steps"
     * Explanation:"1s3PSt"可以看作为“spst”，在words中“step”少了个s不匹配，“steps”刚好全部匹配。
     * </p>
     * Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
     * Output: "pest"
     * Explanation:只有一个“s”，words中全部匹配，但是“pest”是长度最短的。
     *
     * @see #shortestCompletingWordBetter(String, String[])
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        String result = null;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : licensePlate.toCharArray()) {
            if (Character.isLetter(c)) {
                stringBuilder.append(Character.toLowerCase(c));
            }
        }

        for (String string : words) {
            if (string.length() >= stringBuilder.length()) {
                hashMap.clear();
                for (char c : string.toLowerCase().toCharArray()) {
                    hashMap.put(c, hashMap.get(c) == null ? 1 : hashMap.get(c) + 1);
                }

                boolean contain = true;
                for (char c : stringBuilder.toString().toCharArray()) {
                    if (!hashMap.containsKey(c) && hashMap.get(c) <= 0) {
                        contain = false;
                        break;
                    } else {
                        hashMap.put(c, hashMap.get(c) - 1);
                    }
                }

                if (contain) {
                    if (result == null || result.length() > string.length()) {
                        result = string;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 利用桶排序的思想，把字母换为int存在数组了，这样就可以直接比较各个字母了
     */
    public String shortestCompletingWordBetter(String licensePlate, String[] words) {
        String result = null;
        int[] license = getCharToIntArray(licensePlate.toLowerCase().toCharArray());
        for (String string : words) {
            if (result == null || result.length() > string.length()) {
                int[] wordInt = getCharToIntArray(string.toLowerCase().toCharArray());
                boolean contain = true;
                for (int i = 0; i < license.length; i++) {
                    if (license[i] != 0 && wordInt[i] < license[i]) {
                        contain = false;
                        break;
                    }
                }
                if (contain) {
                    result = string;
                }
            }
        }
        return result;
    }

    private int[] getCharToIntArray(char[] chars) {
        int[] array = new int[26];
        for (char c : chars) {
            int charToInt = c - 'a';
            if (charToInt >= 0 && charToInt < 26) {
                array[charToInt]++;
            }
        }
        return array;
    }

    static class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", importance=" + importance +
                    ", subordinates=" + subordinates +
                    '}';
        }
    }

    /**
     * 690:https://leetcode.com/problems/employee-importance/description/
     * <p>
     * 给定一组员工表，包含员工自己的id和重要性以及下属的id。
     * 求出给定id的那个员工及其下属员工（比如经理下面是组长，组长下面是开发人员，则整个关系链都算）的重要性之和。
     * </p>
     * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
     * Output: 11
     */
    public static int getImportance(List<Employee> employees, int id) {
        HashMap<Integer, Employee> hashMap = new HashMap<>(employees.size());
        for (Employee employee : employees) {
            hashMap.put(employee.id, employee);
        }
        hashMap.put(-1, new Employee());
        recursiveGetImportance(hashMap.get(id), hashMap);
        return hashMap.get(-1).id;
    }

    private static void recursiveGetImportance(Employee employee, HashMap<Integer, Employee> hashMap) {
        hashMap.get(-1).id += employee.importance;
        if (employee.subordinates == null || employee.subordinates.isEmpty()) {
            return;
        }

        for (Integer integer : employee.subordinates) {
            recursiveGetImportance(hashMap.get(integer), hashMap);
        }
    }

    /**
     * 同样的代码，别人打败100%的人，我这最多60%多，是我打开的方式不对？
     */
    private int getImportanceBetter(List<Employee> list, int id) {
        HashMap<Integer, Employee> hashMap = new HashMap<>();
        for (Employee employee : list) {
            hashMap.put(employee.id, employee);
        }
        return recursiveGetImportance(hashMap, id);
    }

    /**
     * 把HashMap作为参数传递只打败了30%多的人，如果把HashMap作为类属性使用则可以打败60%多的人，参数传递开销这么大的吗？
     */
    private int recursiveGetImportance(HashMap<Integer, Employee> hashMap, int id) {
        Employee employee = hashMap.get(id);
        int result = employee.importance;
        for (Integer integer : employee.subordinates) {
            result += recursiveGetImportance(hashMap, integer);
        }
        return result;
    }

    /**
     * 389:https://leetcode.com/problems/find-the-difference/description/
     * <p>
     * 给定两个只包含小写字母的字符串s和t，其中t比s多一个字母，要求找出那个字母
     * </p>
     * Input:
     * s = "abcd"
     * t = "abdce"
     * Output:'e'
     *
     * @see #findTheDifferenceBetter(String, String)
     */
    public char findTheDifference(String s, String t) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            int count = hashMap.getOrDefault(c, 0) + 1;
            hashMap.put(c, count);
        }
        for (char c : t.toCharArray()) {
            if (!hashMap.containsKey(c)) {
                return c;
            }
            if (hashMap.get(c) == 0) {
                return c;
            } else {
                int count = hashMap.get(c) - 1;
                hashMap.put(c, count);
            }
        }
        return ' ';
    }

    private char findTheDifferenceBetter(String s, String t) {
        int sInt = 0, tInt = 0;
        for (char c : s.toCharArray()) {
            sInt += c;
        }

        for (char c : t.toCharArray()) {
            tInt += c;
        }
        return (char) (tInt - sInt);
    }

    /**
     * 448:https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/description/
     * <p>
     * 给定一个整型数组，数组里面的元素大小为[1,n]，n为数组大小。有些数字出现两次，有些一次，要求找出没出现的数字。
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int value = Math.abs(nums[i]) - 1;
            if (nums[value] > 0) {
                nums[value] = -nums[value];     //改为负数标记该位置的数字出现过
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                list.add(i + 1);
            }
        }
        return list;
    }

    /**
     * 169:https://leetcode.com/problems/majority-element/description/
     * <p>
     * 给定一个整型数组，请找出出现次数大于n/2的那个数（保证存在)
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int half = nums.length / 2 + nums.length % 2;
        for (Integer integer : nums) {
            int count = hashMap.getOrDefault(integer, 0) + 1;
            if (count == half) {
                return integer;
            }
            hashMap.put(integer, count);
        }
        return -1;
    }

    private static int majorityElementBetter(int[] nums) {
        int num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                num = nums[i];
            }
            if (nums[i] == num) {
                count++;
            } else {
                count--;
            }
        }
        return num;
    }

    /**
     * 122:https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
     * <p>
     * 给定一个数组，数组里面的数代表当天的股价，可以多次买入卖出（不能在同一天），求能获得的最大利润
     *
     * @see #maxProfitUpgradeBetter(int[])
     */
    public int maxProfitUpgrade(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int result = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                result += prices[i] - min;
            }
            min = prices[i];
        }
        return result;
    }

    /**
     * 比上面节省了一个int
     */
    private int maxProfitUpgradeBetter(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                result += prices[i] - prices[i - 1];
            }
        }
        return result;
    }

    /**
     * 217:https://leetcode.com/problems/contains-duplicate/description/
     * <p>
     * 判断一个整型数组里面是否有重复的元素。
     */
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 788:https://leetcode.com/problems/rotated-digits/description/
     * <p>
     * 给定一个整数N，求出在[1,N]之中是“good”的数是多少。“good”的判断为，一个数的每一位如果是0,1,8则不变，2和5互相翻转，6和9互相翻转。
     * 如果包含其他的数（3,4,7）则这个数不是“good”，如果翻转后没变则也不是”good“
     * </p>
     * Input: 10
     * Output: 4
     * Explanation:
     * There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
     * Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
     */
    public static int rotatedDigits(int N) {
        // TODO: 2018/12/11 finish
        int result = 0;
        int[] ints = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (i < 10) {
                if (i == 2) {
                    ints[i] = 5;
                } else if (i == 5) {
                    ints[i] = 2;
                } else if (i == 6) {
                    ints[i] = 9;
                } else if (i == 9) {
                    ints[i] = 6;
                } else {
                    ints[i] = i;
                }
            }
        }
        return result;
    }

    /**
     * 349:https://leetcode.com/problems/intersection-of-two-arrays/description/
     * <p>
     * 给定两个数组，求出它们的交集（不能有重复元素）
     * </p>
     * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * Output: [9,4]
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (Integer integer : nums1) {
            hashSet.add(integer);
        }

        for (Integer integer : nums2) {
            if (hashSet.contains(integer)) {
                result.add(integer);
            }
        }
        int[] ints = new int[result.size()];
        int index = 0;
        for (Integer integer : result) {
            ints[index++] = integer;
        }
        return ints;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
