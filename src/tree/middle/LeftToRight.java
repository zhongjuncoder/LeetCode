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

}
