import java.util.LinkedList;

public class TreeAlgorithm {

    public static void main(String[] args) {
        Tree tree1 = new Tree(1);
        Tree tree2 = new Tree(2);
        Tree tree3 = new Tree(3);
        Tree tree4 = new Tree(4);
        Tree tree5 = new Tree(5);
        Tree tree6 = new Tree(6);
        Tree tree7 = new Tree(7);
        Tree tree8 = new Tree(8);
        Tree tree9 = new Tree(9);
        Tree tree10 = new Tree(10);
        Tree tree11 = new Tree(11);
        Tree tree12 = new Tree(12);
        Tree tree13 = new Tree(13);
        Tree tree14 = new Tree(14);

        tree1.left = tree2;
        tree1.right = tree3;

        tree2.left = tree4;
        tree2.right = tree5;

        tree3.left = tree6;
        tree3.right = tree7;

        tree4.left = tree8;
        tree4.right = tree9;

        tree5.left = tree10;
        tree5.right = tree11;

        tree6.left = tree12;
        tree6.right = tree13;

        tree8.left = tree14;


        fineTreeLevelMax(tree1);

        exchangeTree(tree1);

        fineTreeLevelMax(tree1);

    }

    /**
     * 前序遍历
     */
    private static void preSort(Tree tree) {
        if (tree == null) {
            return;
        }

        System.out.println(tree.value);
        preSort(tree.left);
        preSort(tree.right);
    }

    /**
     * 后序遍历
     */
    private static void postOrder(Tree tree) {
        if (tree == null) {
            return;
        }

        postOrder(tree.left);
        postOrder(tree.right);
        System.out.println(tree.value);
    }

    /**
     * 层次遍历
     */
    private static void levelTraversal(Tree tree) {
        if (tree == null) {
            return;
        }
        LinkedList<Tree> linkedList = new LinkedList<>();
        linkedList.add(tree);
        while (!linkedList.isEmpty()) {
            Tree temp = linkedList.removeFirst();
            System.out.println(temp.value + " ");

            if (temp.left != null) {
                linkedList.add(temp.left);
            }

            if (temp.right != null) {
                linkedList.add(temp.right);
            }
        }
    }

    /**
     * 找出每层最大的节点
     */
    private static void fineTreeLevelMax(Tree tree) {
        if (tree == null) {
            return;
        }

        LinkedList<Tree> linkedList = new LinkedList<>();

        linkedList.add(tree);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            int max = linkedList.get(0).value;

            for (int i = 0; i < size; i++) {
                Tree temp = linkedList.removeFirst();
                System.out.print(temp.value+"  ");
                if (temp.value > max) {
                    max = temp.value;
                }

                if (temp.left != null) {
                    linkedList.add(temp.left);
                }

                if (temp.right != null) {
                    linkedList.add(temp.right);
                }
            }

            System.out.println();
            //System.out.println(max);
        }
    }

    /**
     * 获取树的深度
     */
    private static int getTreeDepth(Tree tree) {
        if (tree == null) {
            return 0;
        }
        int leftDepth = getTreeDepth(tree.left);
        int rightDepth = getTreeDepth(tree.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 交换树的左右节点
     */
    private static void exchangeTree(Tree tree) {
        if (tree == null) {
            return;
        }

        Tree temp = tree.left;
        tree.left = tree.right;
        tree.right = temp;

        exchangeTree(tree.left);
        exchangeTree(tree.right);
    }

    private static class Tree {
        int value;
        Tree left;
        Tree right;

        public Tree(int value) {
            this.value = value;
        }

    }

}
