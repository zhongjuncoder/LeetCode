package tree.middle;

import tree.ListNode;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeftToRight {

    /**
     * 给你一棵二叉树，请你返回层数最深的叶子节点的和。
     * <p>
     * 输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
     * 输出：15
     * <p>
     * 链接：https://leetcode-cn.com/problems/deepest-leaves-sum
     */
    public int deepestLeavesSum(TreeNode root) {
        int sum = 0;
        if (root != null) {
            List<TreeNode> treeNodes = new LinkedList<>();
            treeNodes.add(root);
            while (!treeNodes.isEmpty()) {
                sum = 0;
                int size = treeNodes.size();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = treeNodes.remove(0);
                    sum += treeNode.val;

                    if (treeNode.left != null) {
                        treeNodes.add(treeNode.left);
                    }

                    if (treeNode.right != null) {
                        treeNodes.add(treeNode.right);
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
     * <p>
     * 示例：
     * <p>
     * 输入：[1,2,3,4,5,null,7,8]
     * <p>
     * 1
     * /  \
     * 2    3
     * / \    \
     * 4   5    7
     * /
     * 8
     * <p>
     * 输出：[[1],[2,3],[4,5,7],[8]]
     * <p>
     * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return null;
        }
        List<ListNode> listNodes = new LinkedList<>();
        List<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(tree);
        while (!treeNodes.isEmpty()) {
            int size = treeNodes.size();
            ListNode node = null;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = treeNodes.remove(0);
                if (treeNode.left != null) {
                    treeNodes.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNodes.add(treeNode.right);
                }
                if (node == null) {
                    node = new ListNode(treeNode.val);
                    listNodes.add(node);
                } else {
                    ListNode listNode = new ListNode(treeNode.val);
                    node.next = listNode;
                    node = listNode;
                }
            }
        }
        return listNodes.toArray(new ListNode[listNodes.size()]);
    }

    /**
     * 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
     * <p>
     * 请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
     * <p>
     * 输入：root = [1,7,0,7,-8,null,null]
     * 输出：2
     * 解释：
     * 第 1 层各元素之和为 1，
     * 第 2 层各元素之和为 7 + 0 = 7，
     * 第 3 层各元素之和为 7 + -8 = -1，
     * 所以我们返回第 2 层的层号，它的层内元素之和最大。
     * <p>
     * 链接：https://leetcode-cn.com/problems/maximum-level-sum-of-a-binary-tree
     */
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        int maxSum = Integer.MIN_VALUE;
        List<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.add(root);

        int floor = 0;
        while (!treeNodeList.isEmpty()) {
            floor++;
            int size = treeNodeList.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = treeNodeList.remove(0);
                sum += treeNode.val;

                if (treeNode.left != null) {
                    treeNodeList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNodeList.add(treeNode.right);
                }
            }
            if (sum > maxSum) {
                maxSum = sum;
                depth = floor;
            }
        }
        return depth;
    }

    /**
     * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
     * <p>
     * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
     * <p>
     * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
     * <p>
     * 示例：
     * <p>
     * 输入：root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
     * 输出：true
     * 解释：我们翻转值为 1，3 以及 5 的三个节点。
     * <p>
     * 提示：
     * <p>
     * 每棵树最多有 100 个节点。
     * 每棵树中的每个值都是唯一的、在 [0, 99] 范围内的整数。
     * <p>
     * 链接：https://leetcode-cn.com/problems/flip-equivalent-binary-trees
     */
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)) || (flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }

    /**
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * <p>
     * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> treeNodeList = new LinkedList<>();
        treeNodeList.add(root);
        boolean leftToRight = true;
        while (!treeNodeList.isEmpty()) {
            int size = treeNodeList.size();
            List<Integer> integers = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = treeNodeList.remove(0);

                if (treeNode.left != null) {
                    treeNodeList.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    treeNodeList.add(treeNode.right);
                }
                if (leftToRight) {
                    integers.add(treeNode.val);
                } else {
                    integers.add(0, treeNode.val);
                }
            }
            leftToRight = !leftToRight;
            result.add(integers);
        }
        return result;
    }

}
