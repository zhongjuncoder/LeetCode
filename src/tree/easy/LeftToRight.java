package tree.easy;


import tree.Node;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 从左到右
 */
public class LeftToRight {

    public static void main(String[] args) {

    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        mirrorTree(left);
        mirrorTree(right);
        return root;
    }

    /**
     * 翻转二叉树
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        invertTree(left);
        invertTree(right);
        return root;
    }

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * <p>
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        } else if (t1 != null && t2 == null) {
            return t1;
        } else if (t1 == null) {
            return t2;
        } else {
            t1.val += t2.val;
            t1.left = mergeTrees(t1.left, t2.left);
            t1.right = mergeTrees(t1.right, t2.right);
            return t1;
        }
    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return bst(nums, 0, nums.length - 1);
    }

    private TreeNode bst(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int middle = (startIndex + endIndex) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = bst(nums, startIndex, middle - 1);
        root.right = bst(nums, middle + 1, endIndex);
        return root;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        preorder(root, list);
        return list;
    }

    private void preorder(Node root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            if (root.children != null) {
                for (Node child : root.children) {
                    preorder(child, list);
                }
            }
        }
    }

    /**
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
     * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listList = new LinkedList<>();
        if (root != null) {
            List<TreeNode> nodeList = new LinkedList<>();
            nodeList.add(root);
            while (!nodeList.isEmpty()) {
                List<Integer> list = new LinkedList<>();
                int size = nodeList.size();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = nodeList.remove(0);
                    if (treeNode.left != null) {
                        nodeList.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        nodeList.add(treeNode.right);
                    }
                    list.add(treeNode.val);
                }
                listList.add(list);
            }
        }
        return listList;
    }

    /**
     * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        if (root != null) {
            Deque<TreeNode> linkedList = new LinkedList<>();
            linkedList.add(root);
            while (!linkedList.isEmpty()) {
                List<Integer> list = new ArrayList<>();
                int size = linkedList.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = linkedList.removeFirst();
                    list.add(node.val);
                    if (node.left != null) {
                        linkedList.add(node.left);
                    }
                    if (node.right != null) {
                        linkedList.add(node.right);
                    }
                }
                lists.add(0, list);
            }
        }
        return lists;
    }

}
