package me.danielpan.review;

import me.danielpan.review.model.ChessNode;
import me.danielpan.review.question.Algorithm;
import me.danielpan.review.question.ArrayQuestions;
import me.danielpan.review.question.StringQuestions;

import java.util.List;

/**
 * Created by Daniel Pan on 14/09/2017 : 15:32.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class Test {
    public static void main(String[] args) {
        String src = "hello, world";
        String target = "dlrow";
        System.out.println(StringQuestions.containsReverseString(src, target));
        int[] pre = new int[]{6, 5, 2, 4, 3, 7, 9, 8, 10};
        int[] in = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayQuestions.printPost(pre, in);
        System.out.println();
        int[] a = new int[]{2, 4, 6, 8, 10, 11};
        int[] b = new int[]{1, 3, 5, 7, 9};
        int[] mergerArray = ArrayQuestions.merge(a, b);
        System.out.println("Merge asd array: ");
        for (int i : mergerArray) {
            System.out.print(i + ", ");
        }

        int[] ints = new int[]{27, 5, 13, 25, 7, 12, 17, 30, 16, 14, 26, 8};
        int[] quickSort = ArrayQuestions.quickSort(ints);
        System.out.println("Quick sort array: ");
        for (int i : quickSort) {
            System.out.println(i + ", ");
        }
        /*9234+864=10098
         * 9234+764=9998
         * */
//        int[] x = new int[]{4, 3, 2, 9};
        int[] x = new int[]{4, 3, 2, 8};
//        int[] y = new int[]{4, 6, 8};
//        int[] y = new int[]{4, 6, 7};
        int[] y = new int[]{4, 6, 7, 1};
        int[] result = ArrayQuestions.addBigNumber(x, y);
        for (int i : result) {
            System.out.print(i + ", ");
        }

        int[] arr = new int[]{-5, -5, -3, -3, -3, -1, 0, 3, 6};
        int count = ArrayQuestions.absDistinct(arr);
        System.out.println("Abs counts: " + count);
        int[] unsorted = new int[]{-5, 9, -3, 3, 0, -1, 3, -4, 6};
        System.out.println("Length of longest increasing subsequence: " + ArrayQuestions.lengthOfLIS(unsorted));
//        System.out.println("Count all increasing subsequence: " + ArrayQuestions.countIncreasingSubsequence(unsorted));
        System.out.println(ArrayQuestions.lengthOfLISByLoop(unsorted));
        int i = 247345499;
//        int i = -247345499;
        System.out.println("String: " + Integer.toOctalString(i));
        System.out.println("Binary String: " + Integer.toBinaryString(i));
        System.out.println("Reversed String: " + Integer.toOctalString(Integer.reverse(i)));
        System.out.println("Reversed Binary String: " + Integer.toBinaryString(Integer.reverse(i)));
        System.out.println("Algorithm.reverse");
        System.out.println("Reversed String: " + Integer.toOctalString(Algorithm.reverse(i)));
        System.out.println("Reversed Binary String: " + Integer.toBinaryString(Algorithm.reverse(i)));
        System.out.println("Algorithm.reverse1");
        System.out.println("Reversed String: " + Integer.toOctalString(Algorithm.reverse1(i)));
        System.out.println("Reversed Binary String: " + Integer.toBinaryString(Algorithm.reverse1(i)));
        System.out.println("Algorithm.reverse2");
        System.out.println("Reversed String: " + Integer.toOctalString(Algorithm.reverse2(i)));
        System.out.println("Reversed Binary String: " + Integer.toBinaryString(Algorithm.reverse2(i)));
        int[][] binaryArray = {{1, 3, 5}, {2, 4, 6}, {7, 8, 9}};
        System.out.println(ArrayQuestions.findFromBinaryArray(binaryArray, 7));
        System.out.println(ArrayQuestions.findFromBinaryArray1(binaryArray, 7));
        System.out.println(ArrayQuestions.findFromBinaryArray2(binaryArray, 7));

        String origon = "BBC ABCDAB ABCDABCDABDE";
        String sub = "ABCDABD";
        int[] next = StringQuestions.next(sub);
        for (int value : next) {
            System.out.print(value + ", ");
        }
        StringQuestions.pattern(origon, sub, next);
//        int[] lisSrc = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
//        int[] lisSrc = new int[]{2, 1, 4};
        int[] lisSrc = new int[]{9, 5, 6, 7, 5, 6, 5, 3, 1};
        System.out.println("Longest Increasing Subsequence");
        System.out.println(ArrayQuestions.lis(lisSrc));
        System.out.println("Longest Decreasing Subsequence");
        System.out.println(ArrayQuestions.lds(lisSrc));
        System.out.println("Longest Increasing & Decreasing Subsequence");
        System.out.println(ArrayQuestions.findLongestIncreasingDecreasingSubsequence(lisSrc));
        int[] digits = new int[]{0, 1, 3, 8};
        int k = 21;

        int srcDigits = 12352874;
        System.out.println("src: " + srcDigits);
        srcDigits = ArrayQuestions.findCeil(srcDigits);
        System.out.println("findCeil 1: " + srcDigits);
        srcDigits = ArrayQuestions.findCeil(srcDigits);
        System.out.println("findCeil 2: " + srcDigits);
        srcDigits = ArrayQuestions.findCeil(srcDigits);
        System.out.println("findCeil 3: " + srcDigits);
        srcDigits = ArrayQuestions.findCeil(srcDigits);
        System.out.println("findCeil 4: " + srcDigits);
        srcDigits = ArrayQuestions.findCeil(srcDigits);
        System.out.println("findCeil 5: " + srcDigits);
        for (int j = 0; j < 5; j++) {
            System.out.println("Recursive Catalan " + j + ": " + ArrayQuestions.catalanByRecursive(j));
            System.out.println("Loop Catalan " + j + ": " + ArrayQuestions.catalanByLoop(j));
        }
        System.out.println("棋盘上两点间的马步距离");
        ChessNode start = new ChessNode(1, 2);
        ChessNode end = new ChessNode(7, 9);
        System.out.println(Algorithm.horseStep(start, end));
        int[] ar = new int[]{1, 2, 3};
        List<List<Integer>> subsequences = ArrayQuestions.generateAllSubsequence(ar);
        System.out.println(subsequences);
        List<List<Integer>> subsequences1 = ArrayQuestions.generateAllSubsequence1(ar);
        System.out.println(subsequences1);

        int[][] spiralArray = new int[7][7];
        ArrayQuestions.print2DArrayInSpiral(spiralArray, 7, 7);
        System.out.println("");
        int[][] matrix = ArrayQuestions.createSpiralMatrix(7);
        print2DMatrix(matrix);
        System.out.println("");
        int[][] matrix1 = ArrayQuestions.create2DMatrix(5, 7);
        print2DMatrix(matrix1);
    }

    //打印二维数组。
    private static void print2DMatrix(int[][] matrix) {
        for (int vx = 0; vx < matrix.length; vx++) {
            for (int vy = 0; vy < matrix[vx].length; vy++) {
                if (matrix[vx][vy] < 10) {
                    System.out.print("0" + matrix[vx][vy] + ",");
                } else {
                    System.out.print(matrix[vx][vy] + ",");
                }
            }
            System.out.println();
        }
    }
}

