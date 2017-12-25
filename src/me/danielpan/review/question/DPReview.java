package me.danielpan.review.question;

/**
 * Created by Daniel Pan on 17/11/2017 : 14:41.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class DPReview {

    /*
    * 假设有几种硬币(coin[0, ..., n-1])，如1、3、5，并且数量无限。请找出能够组成某个数目(k)的找零所使用最少的硬币数
    * count(k) = 1 + min(count(k-coin[0]), count(k-coin(1)), ..., count(k-coin(n-1)))
    *
    * coin  1   3   5   min
    * 0     0   0   0   0
    * 1     1   0   0   1
    * 2     2   0   0   2
    * 3     3   1   0   3, 1
    * 4     2   2   0   2, 2
    * 5     3   3   1   3, 3, 1
    * 6     2   2   2   2, 2, 2
    * ...
    * 可惜目前我并没有找到硬币找零问题的递归解法...
    * */
    public static int backTrackingCoin(int[] coins, int k) {//回溯法+动态规划
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        int[][] matrix = new int[k + 1][coins.length + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                int preTotal = i - coins[j];
                if (preTotal > -1) {
                    matrix[i][j] = matrix[preTotal][coins.length] + 1;//
                    if (matrix[i][coins.length] == 0 || matrix[i][j] < matrix[i][coins.length]) {
                        matrix[i][coins.length] = matrix[i][j];
                    }
                }
            }
        }
        return matrix[k][coins.length];
    }

    public static int backTrackingCoin1(int[] coins, int k) {//回溯法+动态规划
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        int[] minCount = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                int preTotal = i - coins[j];
                if (preTotal > -1) {
                    int temp = minCount[preTotal] + 1;
                    if (minCount[i] == 0 || minCount[i] > temp) {
                        minCount[i] = temp;
                    }
                }
            }
        }
        return minCount[k];
    }

    static class State {
        int min;
    }

    public static int dpCoin(int[] coins, int k) {//
        if (coins == null || coins.length == 0 || k < 1) {
            return 0;
        }
        State[] states = new State[k + 1];
        for (int i = 0; i < states.length; i++) {
            states[i] = new State();
        }
        for (int i = 1; i <= k; i++) {
            State curState = states[i];
            for (int j = 0; j < coins.length; j++) {
                int preK = i - coins[j];
                if (preK > -1) {
                    State preState = states[preK];
                    int min = preState.min + 1;
                    if (curState.min == 0 || min < curState.min) {
                        curState.min = min;
                    }
                }
            }
        }

        return states[k].min;
    }

    /*
    * 一个矩形区域被划分为M*N个小矩形格子，在格子(i,j)中有A[i][j]个苹果。
    * 现在从左上角的格子(1,1)出发，要求每次只能向右走一步或向下走一步，最后到达(N,M)，每经过一个格子就把其中的苹果全部拿走。
    * 请找出能拿到最多苹果数的路线
    * 从nums[0][0]到nums[i][j]的和最大
    * */

    public static int maxApples(int[][] nums, int x, int y) {
        if (nums == null || nums.length == 0 || nums[0] == null || nums[0].length == 0) {
            return -1;
        }
        if (x < 0 || y < 0 || x > nums.length || y > nums[0].length) {
            return -1;
        }
        if (x == 0 && y == 0) {
            return nums[x][y];
        } else if (x == 0 && y != 0) {
            return nums[x][y] + maxApples(nums, x, y - 1);
        } else if (y == 0 && x != 0) {
            return nums[x][y] + maxApples(nums, x - 1, y);
        } else {
            return nums[x][y] + Math.max(maxApples(nums, x - 1, y), maxApples(nums, x, y - 1));
        }
    }

    public static int lengthOfLCS(String a, String b) {//动态规划
        if (a == null || b == null) {
            return -1;
        }
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[a.length()][b.length()];
    }

    public static void printAllLCS(String a, String b) {//动态规划
        if (a == null || b == null) {
            return;
        }
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        int[][] relationships = new int[a.length() + 1][b.length() + 1];
        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                    relationships[i][j] = 0;//from top-left
                } else if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    matrix[i][j] = matrix[i - 1][j];
                    relationships[i][j] = 1;//from left
                } else if (matrix[i - 1][j] < matrix[i][j - 1]) {
                    matrix[i][j] = matrix[i][j - 1];
                    relationships[i][j] = 2;//from top
                } else {
                    matrix[i][j] = matrix[i][j - 1];
                    relationships[i][j] = 3;//from top or left
                }
            }
        }
        printAllLCS(a, relationships, a.length(), b.length());
    }

    private static void printAllLCS(String a, int[][] relationships, int m, int n) {
        if (relationships[m][n] == 0) {
            printAllLCS(a, relationships, m - 1, n - 1);
            System.out.print(a.charAt(m - 1));
        } else if (relationships[m][n] == 1) {
            printAllLCS(a, relationships, m, n - 1);
        } else if (relationships[m][n] == 2) {
            printAllLCS(a, relationships, m - 1, n);
        } else if (relationships[m][n] == 3) {
            System.out.print("(");
            printAllLCS(a, relationships, m - 1, n);
            System.out.print("+");
            printAllLCS(a, relationships, m, n - 1);
            System.out.print(")");
        }
    }


    public static int LengthOfLCS1(String a, String b) {
        if (a == null || b == null) {
            return -1;
        }
        return LengthOfLCS1(a, a.length() - 1, b, b.length() - 1);
    }

    public static int LengthOfLCS1(String a, int m, String b, int n) {
        if (m < 0 || n < 0) {
            return 0;
        } else if (a.charAt(m) == b.charAt(n)) {
            return 1 + LengthOfLCS1(a, m - 1, b, n - 1);
        } else {
            return Math.max(LengthOfLCS1(a, m - 1, b, n), LengthOfLCS1(a, m, b, n - 1));
        }
    }

    public static int editDistance(String a, String b) {
        if (a == null || b == null) {
            return -1;
        }
        return editDistance(a, a.length() - 1, b, b.length() - 1);
    }

    public static int editDistance(String a, int m, String b, int n) {
        if (m < 0 || n < 0) {
            return 1;
        } else if (a.charAt(m) == b.charAt(n)) {
            return editDistance(a, m - 1, b, n - 1);
        } else {
            return Math.min(Math.min(editDistance(a, m - 1, b, n) + 1, editDistance(a, m, b, n - 1) + 1), editDistance(a, m - 1, b, n - 1) + 1);
        }
    }

    public static int editDistance1(String a, String b) {
        if (a == null || b == null) {
            return -1;
        }
        int[][] matrix = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i < a.length() + 1; i++) {
            for (int j = 0; j < b.length() + 1; j++) {
                if (i == 0) {
                    matrix[i][j] = j;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        matrix[i][j] = matrix[i - 1][j - 1];
                    } else {
                        matrix[i][j] = 1 + Math.min(Math.min(matrix[i - 1][j], matrix[i][j - 1]), matrix[i - 1][j - 1]);
                    }
                }
            }
        }
        return matrix[a.length()][b.length()];
    }

    /*
    * 一个贼在偷窃一家商店时发现了n件物品，其中第i件值vi元，重wi磅。
     * 他希望偷走的东西总和越值钱越好，但是他的背包只能放下W磅。
     * 请求解如何放能偷走最大价值的物品，这里vi、wi、W都是整数
    */

    public static class Goods {
        public int value;
        public int weight;
    }

    public static int maxValueOfPackage(Goods[] goods, int capacity) {
        if (goods == null || capacity < 0) {
            return -1;
        }
        return maxValueOfPackage(goods, 0, capacity);
    }

    private static int maxValueOfPackage(Goods[] goods, int i, int capacity) {
        if (i == goods.length - 1) {
            if (capacity < goods[i].weight) {
                return 0;
            } else {
                return goods[i].value;
            }
        }
        if (capacity < goods[i].weight) {
            return maxValueOfPackage(goods, i + 1, capacity);
        }
        return Math.max(maxValueOfPackage(goods, i + 1, capacity),
                maxValueOfPackage(goods, i + 1, capacity - goods[i].weight) + goods[i].value);
    }

    /*
    * 0-1背包问题:
    * 有个容量为V大小的背包，
    * 有很多不同重量weight[i](i=1..n)不同价值value[i](i=1..n)的物品，
    * 每种物品只有一个，
    * 想计算一下最多能放多少价值的货物
    * */
    public static int packageQ(int[] values, int[] weights, int capacity) {
        if (values == null || weights == null || values.length == 0 || values.length != weights.length || capacity < 0) {
            return -1;
        }
        return packageQ(values, weights, values.length - 1, capacity);
    }

    private static int packageQ(int[] values, int[] weights, int i, int capacity) {
        if (i == 0) {
            if (capacity < weights[i]) {
                return 0;
            } else {
                return values[i];
            }
        }
        if (capacity < weights[i]) {
            return packageQ(values, weights, i - 1, capacity);
        } else {
            return Math.max(packageQ(values, weights, i - 1, capacity), packageQ(values, weights, i - 1, capacity - weights[i]) + values[i]);//不放入vs放入
        }
    }

    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 5, 7};
        int total = 8;
        System.out.println("Value " + total + " is made of at least " + backTrackingCoin(coins, total) + " coins");
        System.out.println("Value " + total + " is made of at least " + backTrackingCoin1(coins, total) + " coins");
        System.out.println("Value " + total + " is made of at least " + dpCoin(coins, total) + " coins");
        int[][] apples = {{1, 2, 1}, {2, 1, 3}, {3, 2, 1}};
        System.out.println("Apples are: " + maxApples(apples, 2, 1));
        System.out.println("Apples are: " + maxApples(apples, 1, 2));
        System.out.println("Apples are: " + maxApples(apples, 2, 2));
        String a = "abdcabf";
        String b = "cbacfb";
        System.out.println("longest common subsequence is: " + lengthOfLCS(a, b));
        System.out.println("longest common subsequence is: " + LengthOfLCS1(a, b));
        System.out.println("shortest edit distance is: " + editDistance(a, b));
        System.out.println("shortest edit distance is: " + editDistance1(a, b));
    }


}
