package tree.middle;

import tree.ListNode;
import tree.TreeNode;

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

}
