package tree.middle;

import tree.TreeNode;

import java.util.*;

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

    /**
     * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
     * <p>
     * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
     * 如果不存在祖父节点值为偶数的节点，那么返回 0 。
     * <p>
     * 输入：root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
     * 输出：18
     * 解释：图中红色节点的祖父节点的值为偶数，蓝色节点为这些红色节点的祖父节点。
     * <p>
     * 链接：https://leetcode-cn.com/problems/sum-of-nodes-with-even-valued-grandparent
     */
    public int sumEvenGrandparent(TreeNode root) {
        sumEvenGrandparent(root, false, false);
        return mSum;
    }

    private void sumEvenGrandparent(TreeNode treeNode, boolean grandParentIsEven, boolean parentIsEven) {
        if (treeNode == null) {
            return;
        }
        if (grandParentIsEven) {
            mSum += treeNode.val;
        }

        boolean isEven = treeNode.val % 2 == 0;
        sumEvenGrandparent(treeNode.left, parentIsEven, isEven);
        sumEvenGrandparent(treeNode.right, parentIsEven, isEven);
    }

    private HashMap<Integer, Integer> hashMap = new HashMap<>();

    /**
     * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
     * <p>
     * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
     * <p>
     * 示例 1：
     * 输入:
     * <p>
     * 5
     * /  \
     * 2   -3
     * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
     * <p>
     * 示例 2：
     * 输入：
     * <p>
     * 5
     * /  \
     * 2   -5
     * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
     * <p>
     * 链接：https://leetcode-cn.com/problems/most-frequent-subtree-sum
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        findFrequentTreeSumRecursion(root, root.val);
        List<Integer> list = new LinkedList<>();
        for (Integer integer : hashMap.keySet()) {
            list.add(hashMap.get(integer));
        }
        Collections.sort(list);
        int maxCount = list.get(list.size() - 1);
        boolean hasMax = false;
        for (int i = list.size() - 2; i >= 0; i--) {
            if (list.get(i) < maxCount) {
                hasMax = true;
                break;
            }
        }

        List<Integer> integers = new ArrayList<>();
        if (hasMax) {
            for (Integer integer : hashMap.keySet()) {
                int value = hashMap.get(integer);
                if (value == maxCount) {
                    integers.add(integer);
                }
            }
        } else {
            integers.addAll(hashMap.keySet());
        }
        int[] result = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            result[i] = integers.get(i);
        }
        return result;
    }

    private int findFrequentTreeSumRecursion(TreeNode treeNode, int sum) {
        if (treeNode == null) {
            return sum;
        }
        int currentSum = sum;
        if (treeNode.left != null) {
            currentSum += findFrequentTreeSumRecursion(treeNode.left, treeNode.left.val);
        }
        if (treeNode.right != null) {
            currentSum += findFrequentTreeSumRecursion(treeNode.right, treeNode.right.val);
        }
        int count = hashMap.getOrDefault(currentSum, 0);
        hashMap.put(currentSum, ++count);
        return currentSum;
    }

    private int mMax = Integer.MIN_VALUE;

    /**
     * 题目只需要找到出现次数最多的子树元素和，所以记录下出现次数最多的是多少次数即可，省去了排序和去找最大次数
     */
    public int[] findFrequentTreeSumBetter(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        findFrequentTreeSumRecursion(root, root.val);
        List<Integer> integers = new ArrayList<>();     //get的时候LinkedList的复杂度是O(n)
        for (Integer integer : hashMap.keySet()) {
            if (hashMap.get(integer) == mMax) {
                integers.add(integer);
            }
        }
        int[] result = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            result[i] = integers.get(i);
        }
        return result;
    }

    private int findFrequentTreeSumRecursionBetter(TreeNode treeNode, int sum) {
        if (treeNode == null) {
            return sum;
        }

        int currentSum = sum;
        if (treeNode.left != null) {
            currentSum += findFrequentTreeSumRecursionBetter(treeNode.left, treeNode.left.val);
        }
        if (treeNode.right != null) {
            currentSum += findFrequentTreeSumRecursionBetter(treeNode.right, treeNode.right.val);
        }

        int count = hashMap.getOrDefault(currentSum, 0);
        count = ++count;
        hashMap.put(currentSum, count);
        mMax = Math.max(mMax, count);
        return currentSum;
    }

}
