public class MediumAlgorithm {

    public static void main(String[] args) {
        System.out.println(minPathSum(new int[][]{{1, 2, 5}, {3, 2, 1}}));
    }

    /**
     * 64:https://leetcode.com/problems/minimum-path-sum/description/
     * <p>
     * 给定一个二维数组，每个数组中都有一个非负整数代表走出该位置需要的步数，求从左上角到右下角所需步数最小的和(每步只能往右或者往下走)
     * </p>
     * Input:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * Output: 7。路径为1->3->1->1->1
     */
    public static int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        //创建一个同等大小的二维数组，每个元素代表到达该位置时需要的步数之和。
        int[][] sumGrid = new int[height][width];
        sumGrid[height - 1][width - 1] = grid[height - 1][width - 1];   //给右下角的元素赋值

        //在最右边的那一列，每个元素的值等于原数组中的值加上它下面的值，代表当到达该位置时需要的步数之和。0代表还没赋值。
        //[0,0,3]
        //[0,0,2]
        //[0,0,1]
        for (int i = height - 2; i >= 0; i--) {
            sumGrid[i][width - 1] = sumGrid[i + 1][width - 1] + grid[i][width - 1];
        }

        //在最下面的那一行，每个元素的值等于原数组中的值加上它右边的值
        //[0,0,3]
        //[0,0,2]
        //[7,3,1]
        for (int i = width - 2; i >= 0; i--) {
            sumGrid[height - 1][i] = sumGrid[height - 1][i + 1] + grid[height - 1][i];
        }

        //求出还没赋值位置的最优解
        //[0,0,3]
        //[0,7,2]
        //[7,3,1]
        //因为7所在的位置可以由2往左或者3往上到达，但是2才是最优的，所以到达7所在位置需要的步数是2+5
        for (int i = height - 2; i >= 0; i--) {
            for (int j = width - 2; j >= 0; j--) {
                sumGrid[i][j] = Math.min(sumGrid[i + 1][j], sumGrid[i][j + 1]) + grid[i][j];
            }
        }

        //这样左上角的值就是最优解
        return sumGrid[0][0];
    }

    /**
     * 自顶向下的解法
     */
    public static int minPathSumTopToDown(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;

        //创建一个同等大小的二维数组，每个元素代表到达该位置时需要的步数之和。
        int[][] sumGrid = new int[height][width];
        sumGrid[0][0] = grid[0][0];   //给第一个元素赋值

        //在最左边的那一列，每个元素的值等于原数组中的值加上它上面的值，代表当到达该位置时需要的步数之和。0代表还没赋值。
        //[1,0,0]
        //[2,0,0]
        //[6,0,0]
        for (int i = 1; i < height; i++) {
            sumGrid[i][0] = sumGrid[i - 1][0] + grid[i][0];
        }

        //在最上面的那一行，每个元素的值等于原数组中的值加上它左边的值
        //[1,4,5]
        //[2,0,0]
        //[6,3,0]
        for (int i = 1; i < width; i++) {
            sumGrid[0][i] = sumGrid[0][i - 1] + grid[0][i];
        }

        //求出还没赋值位置的最优解
        //[1,4,5]
        //[2,7,0]
        //[6,0,0]
        //因为[1][1]所在的位置可以由[0][1]往下或者[1][0]往右到达，但是[1][0]的步数才是最小，所以到达[1][1]所在位置需要的步数是2+5
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                sumGrid[i][j] = Math.min(sumGrid[i - 1][j], sumGrid[i][j - 1]) + grid[i][j];
            }
        }

        //这样右下角角的值就是最优解
        return sumGrid[height - 1][width - 1];
    }

}
