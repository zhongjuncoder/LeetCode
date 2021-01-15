package tree.easy;

import tree.Node;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 自顶向下
 */
public class TopToBottom {

    /**
     * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
     * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
     * 输出：32
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        if (root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }
        if (root.val <= high) {
            sum += rangeSumBST(root.right, low, high);
        }
        return sum;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的后序遍历后的值。
     */
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    public void postorder(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (root.children != null) {
            for (Node child : root.children) {
                postorder(child, list);
            }
        }
        list.add(root.val);
    }

}
