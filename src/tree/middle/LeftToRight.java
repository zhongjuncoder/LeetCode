package tree.middle;

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

}
