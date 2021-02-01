package tree.middle;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class TopToBottom {

    /**
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     * <p>
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树 。
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     * - 空数组，无子节点。
     * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     * - 空数组，无子节点。
     * - 只有一个元素，所以子节点是一个值为 1 的节点。
     * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     * - 只有一个元素，所以子节点是一个值为 0 的节点。
     * - 空数组，无子节点。
     * <p>
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(-1);
        constructMaximumBinaryTreeRecursion(root, nums, 0, nums.length - 1);
        return root;
    }

    private void constructMaximumBinaryTreeRecursion(TreeNode treeNode, int[] nums, int start, int end) {
        if (treeNode == null || start > end) {
            return;
        }
        int max = Integer.MIN_VALUE;
        int index = -1;
        for (int i = start; i <= end; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        treeNode.val = max;

        if (index > start) {
            TreeNode left = new TreeNode(-1);
            treeNode.left = left;
            constructMaximumBinaryTreeRecursion(left, nums, start, index - 1);
        }

        if (index < end) {
            TreeNode right = new TreeNode(-1);
            treeNode.right = right;
            constructMaximumBinaryTreeRecursion(right, nums, index + 1, end);
        }
    }

    private int mSum = 0;

    private int mMaxDepth = 0;

    public int deepestLeavesSum(TreeNode root) {
        deepestLeavesSum(root, 0);
        return mSum;
    }

    private void deepestLeavesSum(TreeNode treeNode, int depth) {
        if (treeNode == null) {
            return;
        }

        if (mMaxDepth == depth) {
            mSum += treeNode.val;
        } else if (depth > mMaxDepth) {
            mMaxDepth = depth;
            mSum = treeNode.val;
        }

        int nextDepth = ++depth;
        deepestLeavesSum(treeNode.left, nextDepth);
        deepestLeavesSum(treeNode.right, nextDepth);
    }

}
