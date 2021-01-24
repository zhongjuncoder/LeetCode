package tree.easy;

import tree.Node;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 自顶向下
 */
public class TopToBottom {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(2);
        root.left = node1;
        root.right = new TreeNode(2);

        TreeNode node2 = new TreeNode(3);
        node1.left = node2;
        node1.right = new TreeNode(3);

        node2.left = new TreeNode(4);
        node2.right = new TreeNode(4);
        System.out.println(isBalanced(root));
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

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        return root;
    }

    /**
     * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
     * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
     * https://leetcode-cn.com/problems/univalued-binary-tree/
     */
    public boolean isUnivalTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean leftResult = true;
        boolean rightResult = true;
        if (root.left != null) {
            if (root.val != root.left.val) {
                leftResult = false;
            } else {
                leftResult = isUnivalTree(root.left);
            }
        }
        if (leftResult) {
            if (root.right != null) {
                if (root.val != root.right.val) {
                    rightResult = false;
                } else {
                    rightResult = isUnivalTree(root.right);
                }
            }
        }
        return leftResult && rightResult;
    }

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * https://leetcode-cn.com/problems/same-tree/
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null || q == null) || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
     */
    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        for (int i = 1; i < list.size(); i++) {
            int tem = list.get(i) - list.get(i - 1);
            min = Math.min(min, tem);
        }
        return min;
    }

    private void dfs(TreeNode treeNode, List<Integer> list) {
        if (treeNode != null) {
            dfs(treeNode.left, list);
            list.add(treeNode.val);
            dfs(treeNode.right, list);
        }
    }

    private int mMin = Integer.MAX_VALUE;

    private int mInteger = -1;

    public int getMinimumDifferenceBetter(TreeNode root) {
        dfs(root);
        return mMin;
    }

    private void dfs(TreeNode treeNode) {
        if (treeNode != null) {
            dfs(treeNode.left);
            if (mInteger != -1) {
                mMin = Math.min(treeNode.val - mInteger, mMin);
            }
            mInteger = treeNode.val;
            dfs(treeNode.right);
        }
    }

    private int mSum = 0;

    /**
     * 给定一个二叉树，计算 整个树 的坡度 。
     * 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一样。空结点的坡度是 0 。
     * 整个树 的坡度就是其所有节点的坡度之和。
     * https://leetcode-cn.com/problems/binary-tree-tilt/
     */
    public int findTilt(TreeNode root) {
        findTiltRecursion(root);
        return mSum;
    }

    public int findTiltRecursion(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        int leftSum;
        int rightSum;
        leftSum = findTiltRecursion(treeNode.left);
        rightSum = findTiltRecursion(treeNode.right);
        mSum += Math.abs(leftSum - rightSum);
        return leftSum + rightSum + treeNode.val;
    }

    /**
     * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
     * https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
     * ***
     */
    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isBalancedRecursion(root) != -1;
    }

    public static int isBalancedRecursion(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalancedRecursion(root.left);
        if (left == -1) {
            return -1;
        }

        int right = isBalancedRecursion(root.right);
        if (right == -1) {
            return -1;
        }

        return (Math.abs(left - right) <= 1) ? Math.max(left, right) + 1 : -1;
    }

    private HashSet<Integer> hashSet = new HashSet<>();

    /**
     * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
     * <p>
     * 案例 1:
     * <p>
     * 输入:
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     * <p>
     * Target = 9
     * <p>
     * 输出: True
     * <p>
     * https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/
     */
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (hashSet.contains(root.val)) {
            return true;
        } else {
            hashSet.add(k - root.val);
        }
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
