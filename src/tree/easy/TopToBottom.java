package tree.easy;

import tree.Node;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 自顶向下
 */
public class TopToBottom {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode node1 = new TreeNode(1);
        root.left = node1;
        node1.right = new TreeNode(2);

        root.right = new TreeNode(4);
        System.out.println(kthLargest(root, 1));
    }

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

    /**
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        } else if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    private static int count = 0;

    /**
     * 给定一棵二叉搜索树，请找出其中第k大的节点。
     * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
     */
    public static int kthLargest(TreeNode root, int k) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int result = kthLargest(root.right, k);
        return (++count == k) ? root.val : (count > k) ? result : kthLargest(root.left, k);
    }

    private TreeNode treeNode;

    private TreeNode rootTree;

    /**
     * 给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
     * https://leetcode-cn.com/problems/increasing-order-search-tree/
     */
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return rootTree;
        }
        increasingBST(root.left);
        if (treeNode == null) {
            treeNode = new TreeNode(root.val);
            rootTree = treeNode;
        } else {
            treeNode.right = new TreeNode(root.val);
            treeNode = treeNode.right;
        }
        increasingBST(root.right);
        return rootTree;
    }

    /**
     * 给定一个 N 叉树，找到其最大深度。
     * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
     * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
     */
    public int maxDepth(Node root) {
        int max = 0;
        if (root != null && root.children != null) {
            int childMax = 0;
            for (Node child : root.children) {
                childMax = Math.max(childMax, maxDepth(child));
            }
            max = Math.max(max, childMax) + 1;
        }
        return max;
    }

}
