package me.danielpan.review.question;

import me.danielpan.review.model.TreeNode;

import java.util.*;

/**
 * Created by Daniel Pan on 14/09/2017 : 16:20.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class ArrayQuestions {
    /*由前序和中序遍历, 求后序遍历*/
    public static void printPost(int[] pre, int[] in) {
        if (pre == null || in == null) {
            return;
        }
        if (pre.length != in.length) {
            return;
        }

        if (pre.length == 0) {
            return;
        }
        TreeNode root = createTree(pre, in);
        postTraversal(root);
    }

    private static TreeNode createTree(int[] pre, int[] in) {
        return createTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    private static TreeNode createTree(int[] pre, int pStart, int pEnd, int[] in, int iStart, int iEnd) {
        if (pStart > pEnd || iStart > iEnd) {
            return null;
        }
        int rootValue = pre[pStart];
        TreeNode root = new TreeNode(rootValue);
        int tmpIndex = -1;
        for (int i = iStart; i <= iEnd; i++) {
            if (in[i] == rootValue) {
                tmpIndex = i;
            }
        }
        if (tmpIndex != -1) {
            int leftLength = tmpIndex - iStart;
            int pStartLeft = pStart + 1;
            int pEndLeft = pStartLeft + leftLength - 1;
            int iStartLeft = iStart;
            int iEndLeft = tmpIndex - 1;
            root.left = createTree(pre, pStartLeft, pEndLeft, in, iStartLeft, iEndLeft);


            int rightLength = iEnd - tmpIndex;
            int pStartRight = pEnd - rightLength + 1;
            int pEndRight = pEnd;
            int iStartRight = tmpIndex + 1;
            int iEndRight = iEnd;
            root.right = createTree(pre, pStartRight, pEndRight, in, iStartRight, iEndRight);
        }
        return root;
    }

    private static void postTraversal(TreeNode root) {
        if (root != null) {
            postTraversal(root.left);
            postTraversal(root.right);
            System.out.print(root.value + ", ");
        }
    }

    /*合并两个有序数组, 且结果为有序的O(m+n)*/
    public static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int aIndex = 0;
        int bIndex = 0;
        int rIndex = 0;
        while (aIndex < a.length && bIndex < b.length) {
            if (a[aIndex] <= b[bIndex]) {
                result[rIndex] = a[aIndex];
                rIndex++;
                aIndex++;
            } else {
                result[rIndex] = b[bIndex];
                rIndex++;
                bIndex++;
            }
        }
        while (aIndex < a.length) {
            result[rIndex] = a[aIndex];
            rIndex++;
            aIndex++;
        }
        while (bIndex < b.length) {
            result[rIndex] = b[bIndex];
            rIndex++;
            bIndex++;
        }
        return result;
    }

    /*先递增后递减数组找最大值的下标，需要考虑只有单调递增或递减的情况（无相同数字)O(lgn)
    * follow up: 有相同数字的情况怎么做? O(n)*/
    public static int lookForIndexOfMax1(int[] array) {//O(n)
        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1) {
            return 0;
        }
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /*先递增后递减数组找最大值的下标，需要考虑只有单调递增或递减的情况（无相同数字) */
    public static int lookForIndexOfMax2(int[] array) {//O(lgn)
        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1) {
            return 0;
        }
        if (array[0] > array[1]) {//单调递减
            return 0;
        }
        if (array[array.length - 1] > array[array.length - 1]) {//单调递增
            return array.length - 1;
        }
        int start = 1;
        int end = array.length - 1;
        int mid = (start + end) / 2;
        while (start <= mid && mid <= end) {
            if (array[mid - 1] < array[mid] && array[mid] > array[mid + 1]) {
                return mid;
            } else if (array[mid] < array[mid + 1]) {
                start = mid;
            } else if (array[mid] > array[mid + 1]) {
                end = mid;
            }
            mid = (start + end) / 2;
        }

        return -1;
    }

    public static int binarySearchByLoop(int[] array, int value) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (array[mid] == value) {
                return mid;
            } else if (array[mid] < value) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchByRecursion(int[] array, int value) {
        if (array != null && array.length > 0) {
            return binarySearchByRecursion(array, 0, array.length - 1, value);
        }
        return -1;
    }

    public static int binarySearchByRecursion(int[] array, int start, int end, int value) {
        int mid = (start + end) / 2;
        if (array[mid] == value) {
            return mid;
        } else if (array[mid] > value) {
            end = mid - 1;
        } else {
            start = end + 1;
        }
        return binarySearchByRecursion(array, start, end, value);
    }

    public static int[] quickSort(int[] array) {
        return quickSort(array, 0, array.length - 1);
    }

    private static int adjustArray(int[] array, int start, int end) {
        int i = start, j = end;
        int v = array[start];//Math.max(Math.max(array[start], array[end]), array[(start + end) / 2]);
        while (i < j) {
            while (j > start && v <= array[j]) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i++;
            }
            while (i < end && v > array[i]) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = v;
        return i;
    }

    private static int[] quickSort(int[] array, int start, int end) {
        if (start < end) {
            int index = adjustArray(array, start, end);
            quickSort(array, start, index - 1);
            quickSort(array, index + 1, end);
        }
        return array;
    }

    /*给定一个整型数组（数组中的元素可重复），以及一个指定的值。
    *打印出数组中两数之和为指定值的 所有整数对
    *
    * 给定一个无序数组，给一个K，找出数组中有多少对（两个数算一对）数字的和（或差或乘积）为K
    * 一般给出先排序再找的解法。可以再follow up：不用排序怎么解, 如果回答用hash, 那么hash里边保存什么
    *
    *
    *
     */
    public static int lookForPairs(int[] array, int target) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int i = 0, j = array.length - 1;
        quickSort(array, i, j);
        return lookForPairs(array, i, j, target);
    }

    public static int lookForPairsByHash(int[] nums, int target) {//不允许使用排序
        if (nums == null || nums.length < 1) {
            return 0;
        }
        Set<Integer> compliments = new HashSet<>();
        int count = 0;
        for (int num : nums) {
            if (compliments.contains(num)) {
                count++;
            } else {
                compliments.add(target - num);
            }
        }
        return count;
    }

    private static int lookForPairs(int[] array, int start, int end, int target) {
        int i = start, j = end;
        int count = 0;
        while (i < j) {
            if (array[i] + array[j] == target) {
                System.out.println(i + ", " + j);
                i++;
                j--;
                count++;
            } else if (array[i] + array[j] < target) {
                i++;
            } else if (array[i] + array[j] > target) {
                j--;
            }
        }
        return count;
    }

    /*如果把这个问题中的“两个数字”改成“三个数字”或“任意个数字”时
    * 那么, 任意m个数字的想法
    * */
    public static void lookForPairs1(int[] array, int target) {
        if (array == null || array.length == 0) {
            return;
        }
        int i = 0, j = array.length - 1;
        quickSort(array, i, j);
        for (int k = 0; k < array.length; k++) {
            int subSum = target - array[k];
            lookForPairs(array, i, j, subSum);
        }
    }

    /*一个数组，左边的值减右边的值，求这些差里面的最大值*/
    public static int getMaxMinus(int[] a) {//O(n*n)
        if (a == null || a.length < 2) {
            return Integer.MIN_VALUE;
        }
        int maxMinus = Integer.MIN_VALUE;
        int diff = 0;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length - 1; j++) {
                diff = a[i] - a[j];
                if (diff > maxMinus) {
                    maxMinus = diff;
                }
            }
        }
        return maxMinus;
    }

    public static int getMaxMinusOneLoop(int[] a) {//O(n)
        if (a == null || a.length < 2) {
            return Integer.MIN_VALUE;
        }
        int min = a[a.length - 1];
        int maxMinus = Integer.MIN_VALUE;
        int diff = 0;
        for (int i = a.length - 2; i > -1; i--) {
            diff = a[i] - min;
            if (diff > maxMinus) {
                maxMinus = diff;
            }
            if (a[i] < min) {
                min = a[i];
            }
        }
        return maxMinus;
    }

    /*100->[0, 0, 1]*/
    public static int[] addBigNumber(int[] x, int[] y) {
        if (x == null || y == null || x.length == 0 || y.length == 0) {
            return null;
        }
        int[] longArr = x;
        int[] shortArr = y;
        int diff = longArr.length - shortArr.length;
        if (diff < 0) {
            longArr = y;
            shortArr = x;
        }

        int length = longArr.length;
        int[] result = new int[length + 1];
        int tmp = 0;
        diff = 0;
        for (int i = 0; i < length; i++) {
            if (i < shortArr.length) {
                tmp = longArr[i] + shortArr[i] + diff;
                if (tmp > 9) {
                    result[i] = tmp % 10;
                } else {
                    result[i] = tmp;
                }
                diff = tmp / 10;
            } else {
                tmp = longArr[i] + diff;
                if (tmp > 9) {
                    result[i] = tmp % 10;
                } else {
                    result[i] = tmp;
                }
                diff = tmp / 10;
            }
        }
        result[result.length - 1] = diff;
        return result;
    }

    public static int[] minusBigNumber(int[] x, int[] y) {
        if (x == null || y == null || x.length == 0 || y.length == 0) {
            return null;
        }
        int[] longArr = x;
        int[] shortArr = y;
        int diff = longArr.length - shortArr.length;
        boolean positive = true;
        if (diff < 0) {
            positive = false;
            longArr = y;
            shortArr = x;
        } else if (diff == 0) {
            for (int i = 0; i < longArr.length; i++) {
                if (longArr[i] > shortArr[i]) {
                    positive = true;
                } else if (longArr[i] < shortArr[i]) {
                    positive = false;
                }
            }
        }
        int length = longArr.length;
        int[] result = new int[length + 1];
        diff = 0;
        int tmp = 0;
        for (int i = 0; i < length; i++) {
            if (i < shortArr.length) {
                tmp = diff + longArr[i] - shortArr[i];
            } else {
                tmp = diff + longArr[i];
            }
            if (tmp < 0) {
                result[i] = tmp % 10;
            } else {
                result[i] = tmp;
            }
            diff = tmp % 10;
        }
        result[result.length - 1] = diff;
        return result;
    }

    /*有序数组中绝对值不同的数的个数*/
    public static int absDistinct(int[] data) {
        if (data == null || data.length == 0) {
            return -1;
        }
        int p = 0, q = data.length - 1;
        int count = 0;
        while (p < q) {
            if (p < data.length - 1 && data[p] == data[p + 1]) {
                p++;
                continue;
            }
            if (q > 1 && data[q] == data[q - 1]) {
                q--;
                continue;
            }

            if (data[p] + data[q] > 0) {
                q--;
                count++;
            } else if (data[p] + data[q] == 0) {
                q--;
                p++;
                count++;
            } else if (data[p] + data[q] < 0) {
                p++;
                count++;
            }
        }
        if (p == q) {
            count++;
        }
        return count;
    }

    /*找出有序数组中的绝对值的最大值(升序)*/
    public static int findMaxAbs(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        if (arr[0] >= 0) {
            return arr[arr.length - 1];
        }
        if (arr[arr.length - 1] <= 0) {
            return -arr[0];
        }

        return -arr[0] > arr[arr.length - 1] ? -arr[0] : arr[arr.length - 1];
    }

    /*找出有序数组中绝对值最小的数(升序)*/
    public static int findMinAbs(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int s = 0;
        int e = arr.length - 1;
        if (arr[s] >= 0) {
            return arr[s];
        }
        if (arr[e] <= 0) {
            return -arr[e];
        }
        int mid = 0;
        while (s < e) {
            mid = (s + e) / 2;
            if (arr[mid] * arr[mid + 1] <= 0) {
                return Math.min(Math.abs(arr[mid]), Math.abs(arr[mid + 1]));
            } else if (arr[mid] * arr[mid - 1] <= 0) {
                return Math.min(Math.abs(arr[mid - 1]), Math.abs(arr[mid]));
            } else if (0 <= arr[mid]) {
                s = mid + 1;
            } else if (0 >= arr[mid]) {
                e = mid - 1;
            }
        }
        return -1;
    }

    /*给一个无序不重复数组，输出其升序子列的个数。要求空间复杂度O(N)。如：[2, 1, 4]升序子列为：[2], [1], [4], [1, 4], [2, 4], 所以输出为5*/
//    public static int countIncreasingSubsequence(int[] arr) {
//        if (arr == null || arr.length < 1) {
//            return -1;
//        }
//        int[] counts = new int[arr.length];
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = arr[i] - 1; j >= 0; j--) {
//                counts[arr[i]] += counts[j];
//            }
//            counts[arr[i]]++;
//        }
//        int length = 0;
//        for (int num : counts) {
//            length += num;
//        }
//        return length;
//    }

    public static int lengthOfLIS(int[] a) {//O(n*lgn)
        if (a == null || a.length == 0) {
            return -1;
        }
        List<Integer> lis = new ArrayList<>(a.length);//store end elements of active list, size of list means count of active list
        for (Integer num : a) {
            if (lis.isEmpty() || num > lis.get(lis.size() - 1)) {
                lis.add(num);
            } else {
                int i = 0, j = lis.size() - 1;
                int mid;
                while (i < j) {
                    mid = (i + j) / 2;
                    if (num > lis.get(mid)) {
                        i = mid + 1;
                    } else {
                        j = mid;
                    }
                }
                lis.set(j, num);
            }
        }
        return lis.size();
    }

    public static int lengthOfLISByArray(int[] nums) {//O(n*lgn)
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] result = new int[nums.length];
        int length;
        result[0] = nums[0];
        length = 1;
        for (int i = 0; i < nums.length; i++) {
            if (result[0] < nums[i]) {
                result[0] = nums[i];
            } else if (nums[i] > result[length]) {
                result[i++] = nums[i];
            } else {
                int s = 0, e = length - 1;
                while (s < e) {
                    int mid = (s + e) / 2;
                    if (nums[i] > result[mid]) {
                        s = mid + 1;
                    } else {
                        e = mid;
                    }
                }
                result[e] = nums[i];
            }
        }
        return length;
    }

    public static int lengthOfLISByLoop(int[] nums) {//O(n*n)
        if (nums == null || nums.length == 0)
            return 0;

        int[] max = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            max[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max[i] = Math.max(max[i], max[j] + 1);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < max.length; i++) {
            if (max[i] > result)
                result = max[i];
        }
        return result;
    }

    /*
    *给定一个长度为n的无序数组 int array[]，要求找到其中的第k个元素，
    *满足：在array[k]左边的数都小于等于它，在array[k]右边的数都大于等于它。
    *如给定array[] = {7, 10, 2, 6, 19, 22, 32}，则k＝4
    * */
    public static int findValueAtK(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    stack.pop();
                }
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            return Integer.MIN_VALUE;
        } else {
            return stack.pop();
        }
    }

    /*杨氏矩阵找某个元素*/
    //从左下角开始进行二叉判定树, target若是大于当前值, 则横坐标右移一位, 若小于当前值, 则竖坐标左移一位
    public static boolean findFromBinaryArray(int[][] a, int target) {//O(m+n)
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            return false;
        }
        int row = 0;
        int column = a[0].length - 1;
        while (column > -1 && row < a.length) {
            if (a[row][column] > target) {
                column--;
            } else if (a[row][column] < target) {
                row++;
            } else {
                System.out.println("(" + row + ", " + column + ")");
                return true;
            }
        }
        return false;
    }

    //或者, 从右上角开始二叉判定树, target或是大于当前值, 则竖坐标下移一位, 若是小于当前值, 则横坐标左移一位.
    public static boolean findFromBinaryArray1(int[][] a, int target) {//O(m+n)
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            return false;
        }
        int row = a.length - 1;
        int columns = 0;
        while (row > -1 && columns < a[0].length) {
            if (a[row][columns] > target) {
                row--;
            } else if (a[row][columns] < target) {
                columns++;
            } else {
                System.out.println("(" + row + ", " + columns + ")");
                return true;
            }
        }
        return false;
    }

    //将矩阵分成左下跟右上两个三角, 利用二分查找的方式, 搜索
    public static boolean findFromBinaryArray2(int[][] a, int target) {//O(m*lgn+n*lgm)
        if (a == null || a.length == 0 || a[0] == null || a[0].length == 0) {
            return false;
        }
        int low, mid, high;
        for (int i = 0; i < a.length; i++) {//triangle of top-right
            low = i;
            high = a.length - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid][i] > target) {
                    high = mid - 1;
                } else if (a[mid][i] < target) {
                    low = mid + 1;
                } else {
                    System.out.println("(" + mid + ", " + i + ")");
                    return true;
                }
            }
        }

        for (int i = 0; i < a.length; i++) {//triangle of bottom-left
            low = i;
            high = a[0].length - 1;
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[i][mid] > target) {
                    high = mid - 1;
                } else if (a[i][mid] < target) {
                    low = mid + 1;
                } else {
                    System.out.println("(" + i + ", " + mid + ")");
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Integer> lis(int[] nums) {
        if (nums != null && nums.length != 0) {
            return lis(nums, 0, nums.length - 1);
        }
        return null;
    }

    public static List<Integer> lds(int[] nums) {
        if (nums != null && nums.length != 0) {
            return lds(nums, 0, nums.length - 1);
        }
        return null;
    }

    public static List<Integer> lds(int[] nums, int start, int end) {
        if (nums != null && nums.length != 0 && start > -1 && end < nums.length && start <= end) {
            TreeMap<Integer, List<Integer>> map = new TreeMap<>();
            for (int i = start; i < end + 1; i++) {
                int num = nums[i];
                if (map.isEmpty()) {
                    List<Integer> firstList = new ArrayList<>();
                    firstList.add(num);
                    map.put(1, firstList);
                } else {
                    int lisKey = map.lastKey();
                    List<Integer> curLis = map.get(lisKey);
                    if (num < curLis.get(curLis.size() - 1)) {
                        List<Integer> newLis = new ArrayList<>(curLis);
                        newLis.add(num);
                        map.put(lisKey + 1, newLis);
                    } else {
                        int key = -1;
                        for (Integer nKey : map.descendingKeySet()) {
                            List<Integer> list = map.get(nKey);
                            if (num < list.get(list.size() - 1)) {
                                key = nKey;
                                break;
                            }
                        }
                        if (key == -1) {
                            List<Integer> firstList = new ArrayList<>();
                            firstList.add(num);
                            map.put(1, firstList);
                        } else {
                            List<Integer> list = map.get(key);
                            List<Integer> newList = new ArrayList<>(list);
                            newList.add(num);
                            map.put(key + 1, newList);
                        }
                    }
                }
            }
            return map.lastEntry().getValue();
        }
        return null;
    }

    public static List<Integer> lis(int[] nums, int start, int end) {
        if (nums != null && nums.length != 0 && start > -1 && end < nums.length && start <= end) {
            TreeMap<Integer, List<Integer>> map = new TreeMap<>();
            for (int i = start; i < end + 1; i++) {
                int num = nums[i];
                if (map.isEmpty()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(num);
                    map.put(1, list);
                } else {
                    List<Integer> tmp = map.get(map.lastKey());
                    if (num > tmp.get(tmp.size() - 1)) {
                        List<Integer> list = new ArrayList<>(tmp);
                        list.add(num);
                        map.put(map.lastKey() + 1, list);
                    } else {
                        int lisSize = -1;
                        NavigableMap<Integer, List<Integer>> descendingMap = map.descendingMap();
                        for (Map.Entry<Integer, List<Integer>> entry : descendingMap.entrySet()) {
                            List<Integer> lis = entry.getValue();
                            if (num > lis.get(lis.size() - 1)) {
                                lisSize = entry.getKey();
                                break;
                            }
                        }
                        if (lisSize == -1) {
                            List<Integer> list = new ArrayList<>();
                            list.add(num);
                            map.put(1, list);
                        } else {
                            List<Integer> lis = map.get(lisSize);
                            List<Integer> newLis = new ArrayList<>(lis);
                            newLis.add(num);
                            map.put(lisSize + 1, newLis);
                        }
                    }
                }
            }
            return map.lastEntry().getValue();
        }
        return null;
    }

    /*给定数组删除最少的元素使得数组先递增后递减*/
    public static List<Integer> findLongestIncreasingDecreasingSubsequence(int[] nums) {
        if (nums != null && nums.length != 0) {
            TreeMap<Integer, List<Integer>> map = new TreeMap<>();//size mapping to list
            for (int i = 0; i < nums.length; i++) {
                List<Integer> lis = lis(nums, 0, i);
                List<Integer> lds = lds(nums, i + 1, nums.length - 1);
                List<Integer> increasingDecreasingList = new ArrayList<>();
                if (lis != null) {
                    increasingDecreasingList.addAll(lis);
                }
                if (lds != null) {
                    increasingDecreasingList.addAll(lds);
                }
                map.put(increasingDecreasingList.size(), increasingDecreasingList);//相同长度的先增后减列表, 会保留后出现的
            }
            return map.lastEntry().getValue();
        }
        return null;
    }

    /*
    * 给定一个集合A=[0,1,3,8](该集合中的元素都是在0，9之间的数字，但未必全部包含)，
    * 指定任意一个正整数K，请用A中的元素组成一个大于K的最小正整数。
    * 比如，A=[1,0] K=21 那么输出结构应该为100
    * */
    private static int generate_min_int_containing_duplicate_digit(int[] array, int bit_num) {
        // 找到最小的非零整数
        int data = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > 0) {
                data = array[i];
                break;
            }
        }
        for (int i = 1; i < bit_num; ++i) {
            data = data * 10 + array[0];
        }
        return data;
    }


    /* Google2009华南地区笔试题
    * 给定一个集合A=[0,1,3,8](该集合中的元素都是在0，9之间的数字，但未必全部包含)，
    * 指定任意一个正整数K，请用A中的元素组成一个大于K的最小正整数。
    * 比如，A=[1,0] K=21 那么输出结构应该为100。
    * */
    public static int generate_min_int_greater_than_k(int[] array, int k) {
        Arrays.sort(array);
        // high_digit: k的最高位数字
        // bit_num: k的位数
        int high_digit = k, bit_num = 1;
        while (high_digit / 10 > 0) {
            ++bit_num;
            high_digit /= 10;
        }
        // 查找数组中比k的最高位大的最小的数字
        int i;
        for (i = 0; i < array.length; ++i) {
            if (array[i] >= high_digit)
                break;
        }
        if (i == array.length) // 数组中的数字都比K的最高位小
        {
            return generate_min_int_containing_duplicate_digit(array, bit_num + 1);
        } else if (array[i] == high_digit)// 数组中有一位数字跟K的最高位相等
        {
            int low_data = (int) (k - high_digit * Math.pow(10, bit_num - 1));
            return (int) (array[i] * Math.pow(10, bit_num - 1) + generate_min_int_greater_than_k(array, low_data));
        } else // 数组中有一位数字比k的最高位高
        {
            int data = array[i];
            for (int j = 1; j < bit_num; ++j) {
                data = data * 10 + array[0];
            }
            return data;
        }
    }

    /*给出1个正整数，找到用与这个数字相同的digit组成的整数中比这个数字大的数集中的最小数字。
    比如：12352874 的结果是 12354278*/
    public static int findCeil(int src) {
        char[] chars = String.valueOf(src).toCharArray();
        int index = -1;
        int lastIndex = chars.length - 1;
        char lastChar = chars[lastIndex];
        for (int i = chars.length - 2; i > -1; i--) {
            if (chars[i] < lastChar) {
                index = i;
                break;
            }
        }
        if (index > -1) {
            char ch = chars[index];
            chars[index] = lastChar;
            chars[lastIndex] = ch;
            quickSort(chars, index + 1, lastIndex);
        }
        return Integer.parseInt(String.valueOf(chars));
    }

    private static void quickSort(char[] chars, int start, int end) {
        if (chars != null && start > -1 && end < chars.length && start < end) {
            int i = start, j = end;
            char value = chars[start];
            while (i < j) {
                while (j > start && chars[j] >= value) {
                    j--;
                }
                if (i < j) {
                    chars[i] = chars[j];
                    i++;
                }
                while (i < end && chars[i] < value) {
                    i++;
                }
                if (i < j) {
                    chars[j] = chars[i];
                    j--;
                }
                chars[i] = value;
                quickSort(chars, start, i);
                quickSort(chars, i + 1, end);
            }
        }
    }

    public static int catalanByRecursive(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return catalanByRecursive(n - 1) * 2 * (2 * n - 1) / (n + 1);
    }

    public static int catalanByLoop(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int catalan = 2;
        int i = 3;
        while (i <= n) {
            catalan = catalan * 2 * (2 * n - 1) / (n + 1);
            i++;
        }
        return catalan;
    }

    /*
    * 输入一个整形数组，数组里有正数也有负数。
    * 数组中连续的一个或多个整数组成一个子数组，每个子数组都有一个和。
    * 求所有子数组的和的最大值。要求时间复杂度为O(n)
    */

    public static int maxSum(int[] nums) {
        if (nums != null && nums.length > 0) {
            int sum = Integer.MIN_VALUE;
            int diff = 0;
            int s, e;
            s = e = 0;
//            for (int num : nums) {
//                if (diff < 0) {
//                    diff = num;
//                } else {
//                    diff += num;
//                }
//                if (sum < diff) {
//                    sum = diff;
//                }
//            }
            for (int i = 0; i < nums.length; i++) {
                if (diff < 0) {
                    diff = nums[i];
                    s = i;
                } else {
                    diff += nums[i];
                }
                if (sum < diff) {
                    sum = diff;
                    e = i;
                }
            }
            System.out.println("From " + s + " to " + e);
            return sum;
        }
        return Integer.MIN_VALUE;
    }

    private static void heapAdjust(int[] array, int parent, int length) {
        int temp = array[parent]; // temp保存当前父节点
        int child = 2 * parent + 1; // 先获得左孩子

        while (child < length) {

            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[child])
                break;

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }

    public static void heapSort(int[] list) {
        // 循环建立初始堆
        for (int i = list.length / 2; i >= 0; i--) {
            heapAdjust(list, i, list.length - 1);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            heapAdjust(list, 0, i);
            System.out.format("第 %d 趟: \t", list.length - i);
//            printPart(list, 0, list.length - 1);
        }
    }

    public static int[] mergeSort(int[] src) {
        if (src != null && src.length > 0) {
            int middle = src.length >> 1;
            int[] left = new int[middle];
            int[] right = new int[src.length - middle];
            for (int i = 0; i < src.length; i++) {
                if (i < middle) {
                    left[i] = src[i];
                } else {
                    right[i - middle] = src[i];
                }
            }
            left = mergeSort(left);
            right = mergeSort(right);
            merge(left, right, src);
            return src;
        }
        return null;
    }

    private static void merge(int[] left, int[] right, int[] result) {
        if (left == null || right == null || result == null || left.length == 0 || right.length == 0 || result.length == 0 || left.length + right.length != result.length) {
            return;
        }
        int i, j, k;
        i = j = k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] > right[j]) {
                result[k++] = right[j++];
            } else {
                result[k++] = left[i++];
            }
        }
        while (i < left.length) {
            result[k++] = left[i++];
        }
        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    /*基数排序*/
    /*width: 数组中元素的最大位数*/
    public static void radixSort(int[] nums, int width) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        int divider = 1;
        int[][] bucket = new int[10][length];
        int[] counts = new int[10];
        for (int i = 1; i <= width; i++) {
            for (int num : nums) {
                int digit = (num / divider) % 10;
//                int count = counts[digit];
//                bucket[digit][count + 1] = num;
//                counts[digit] = ++count;
                bucket[digit][counts[digit]++] = num;
            }
            int k = 0;
            for (int j = 0; j < counts.length; j++) {
                int count = counts[j];
                if (count == 0) {
                    continue;
                } else {
                    for (int l = 0; l < count; l++) {
                        nums[k++] = bucket[j][l];
                    }
                }
                counts[j] = 0;
            }
            divider *= 10;
        }
    }

    public static List<List<Integer>> generateAllSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> collection = new ArrayList<>();
        collection.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> newClt = new ArrayList<>();
            for (List<Integer> list : collection) {
                if (!list.isEmpty()) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(nums[i]);
                    newClt.add(newList);
                }
            }
            List<Integer> lst = new ArrayList<>();
            lst.add(nums[i]);
            newClt.add(lst);
            collection.addAll(newClt);
        }
        return collection;
    }

    public static List<List<Integer>> generateAllSubsequence1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        List<List<Integer>> collection = new ArrayList<>();
        for (int i = 0; i < Math.pow(nums.length, 2); i++) {
            List<Integer> list = new ArrayList<>();
            String binaryString = Integer.toBinaryString(i);
            for (int j = 0; j < nums.length; j++) {
                int diff = nums.length - binaryString.length();
                if (j < diff) {
                    continue;
                }
                if (binaryString.charAt(j - diff) == '1') {
                    list.add(nums[j]);
                }
            }
            collection.add(list);
        }
        return collection;
    }

    public static void print2DArrayInSpiral(int[][] arr, int a, int b)    //传值时，保证了a<=b。
    {
        int k = 1;        //定义一个变量，形成数字“线”。

        for (int x = 0; x < a / 2; x++)//定义外层for循环，每次数字“线”走完一圈，即一个循环。
        {
            /*第一部分：上边三角区域的代码：如题，就以7*7螺旋方阵为例，数组排列规则是
            arr[0][0],arr[0][1],arr[0][2],[0][3],[0][4],[0][5],[0][6]
		 	arr[1][1],arr[1][2],arr[1][3],[1][4],[1][5]
		  	arr[2][2],arr[2][3],arr[2][4]
		 	arr[3][3]
		 	可以总结规律为arr[x][x~n-x-1]*/
            for (int y = x; y < b - x; y++) {
                arr[x][y] = k++;
            }

			/*第二部分：右边三角区域代码：
             [1][6] , [2][6] , [3][6] , [4][6] , [5][6]
			 [2][5] , [3][5] , [4][5]
			 [3][4]
			 可以总结规律为arr[x+1~m-x-1][n-x-1]*/
            for (int y = x + 1; y < a - x - 1; y++) {
                arr[y][b - x - 1] = k++;
            }

			/*第三部分：下边三角区域：
            [6][6] , [6][5] , [6][4] , [6][3] , [6][2] , [6][1] , [6][0]
			[5][5] , [5][4] , [5][3] , [5][2] , [5][1]
			[4][4] , [4][3] , [4][2]
			[3][3]
			可以总结规律为arr[m-x-1][n-x-1~0]*/
            for (int y = b - x - 1; y >= x; y--) {
                arr[a - x - 1][y] = k++;
            }

			/*第四部分：左边三角区域：
             [5][0] , [4][0] , [3][0] , [2][0] , [1][0]
			 [4][1] , [3][1] , [2][1]
			 [3][2]
			 可以总结规律为arr[m-x-2~x+1][x]*/
            for (int y = x + 1; y < a - x - 1; y++) {
                arr[a - y - 1][x] = k++;
            }
        }

        //这里用于判断第五部分有没有出现，如果有，就给第五部分赋值，顺序从左往右，继续使用key自增。
        if (a % 2 == 1) {
            for (int x = 0; x <= b - a; x++) {
                arr[a / 2][a / 2 + x] = k++;
            }
        }

        //打印二维数组。
        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[x].length; y++) {
                if (arr[x][y] < 10) {
                    System.out.print("0" + arr[x][y] + ",");
                } else {
                    System.out.print(arr[x][y] + ",");
                }
            }
            System.out.println();
        }
    }

    public static int[][] createSpiralMatrix(int n) {
        if (n < 0) {
            return null;
        }
        int[][] matrix = new int[n][n];
        int k = 1;
        int x;
        int y;
        for (int i = 0; i < n / 2; i++) {
            //top triangle
            for (int j = i; j <= n - i - 1; j++) {
                x = i;
                y = j;
                matrix[x][y] = k++;
            }
            //right triangle
            for (int j = i + 1; j <= n - i - 2; j++) {
                x = j;
                y = n - i - 1;
                matrix[x][y] = k++;
            }
            //bottom triangle
            for (int j = n - i - 1; j >= i; j--) {
                x = n - i - 1;
                y = j;
                matrix[x][y] = k++;
            }
            //left triangle
            for (int j = i + 1; j <= n - i - 2; j++) {
                x = n - j - 1;
                y = i;
                matrix[x][y] = k++;
            }
        }
        if (n % 2 == 1) {
            for (int i = 0; i <= n - n; i++) {
                x = n / 2;
                y = n / 2 + i;
                matrix[x][y] = k++;
            }
        }
        return matrix;
    }

    public static int[][] createMatrix(int size) {
        if (size < 0) {
            return null;
        }
        int[][] matrix = new int[size][size];
        int k = 1;
        int x;
        int y;
        for (int i = 0; i < size / 2; i++) {
//            top triangle
            for (int j = i; j < size - i; j++) {
                x = i;
                y = j;
                matrix[x][y] = k++;
            }
//            right triangle
            for (int j = i + 1; j < size - i - 1; j++) {
                x = j;
                y = size - i - 1;
                matrix[x][y] = k++;
            }
//            bottom triangle
            for (int j = size - i - 1; j >= i; j--) {
                x = size - i - 1;
                y = j;
                matrix[x][y] = k++;
            }
//            left triangle
            for (int j = size - i - 2; j >= i + 1; j--) {
                x = j;
                y = i;
                matrix[x][y] = k++;
            }
        }
        if (size % 2 == 1) {
            matrix[size / 2][size / 2] = k++;
        }
        return matrix;
    }

    public static int[][] create2DMatrix(int m, int n) {
        if (m < 0 || n < 0 || m > n) {
            return null;
        }
        int[][] matrix = new int[m][n];
        int k = 1;
        int x;
        int y;
        for (int i = 0; i < m / 2; i++) {
//            top
            for (int j = i; j < n - i; j++) {
                x = i;
                y = j;
                matrix[x][y] = k++;
            }
//            right
            for (int j = i + 1; j < m - i - 1; j++) {
                x = j;
                y = n - i - 1;
                matrix[x][y] = k++;
            }
//            bottom
            for (int j = n - i - 1; j >= i; j--) {
                x = m - i - 1;
                y = j;
                matrix[x][y] = k++;
            }
//            left
            for (int j = m - i - 2; j >= i + 1; j--) {
                x = j;
                y = i;
                matrix[x][y] = k++;
            }
        }
        if (m % 2 == 1) {
            for (int i = 0; i <= n - m; i++) {
                matrix[m / 2][m / 2 + i] = k++;
            }
        }
        return matrix;
    }

    /*
    * 给一个无序不重复数组，输出其升序子列的个数。
    * 要求空间复杂度O(N)。
    * 如：[2, 1, 4]升序子列为：[2], [1], [4], [1, 4], [2, 4], 所以输出为5。
    * follow up: 时间复杂度为O(NlogN)
    * */
    public static int countAllIncreasingSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int[] result = new int[nums.length + 1];//Space complexity: O(n)
        for (int i = 0; i < nums.length; i++) {//Time complexity: O(n*n)
            result[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    result[i] += result[j];
                }
            }
            result[nums.length] += result[i];
        }
        System.out.println("All counts: " + Arrays.toString(result));
        return result[nums.length];
    }

    /*
        * 给一个无序不重复数组，输出所有的升序子列。
        * 如：[2, 1, 4], 输出升序子列为：[2], [1], [4], [1, 4], [2, 4]。
        * follow up: 时间复杂度为O(NlogN)
        * */
    public static List<List<Integer>> getAllIncreasingSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {//Time complexity: O(n*n)
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);
            List<List<Integer>> lists = new ArrayList<>();
            lists.add(list);
            map.put(i, lists);
            for (int j = 0; j < i; j++) {
                List<List<Integer>> preLists = map.get(j);
                for (List<Integer> e : preLists) {
                    if (nums[i] > e.get(e.size() - 1)) {
                        List<Integer> newE = new ArrayList<>(e);
                        newE.add(nums[i]);
                        lists.add(newE);
                    }
                }
            }
            List<List<Integer>> lastLists = map.get(nums.length);
            if (lastLists == null) {
                lastLists = new ArrayList<>();
                map.put(nums.length, lastLists);
            }
            for (List<Integer> integers : lists) {
                lastLists.add(integers);
            }
        }
        return map.get(nums.length);
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 3, 5, 7, 2};
//        int[] a = new int[]{2, 1, 4};
        System.out.println("Count all increasing subsequence: " + countAllIncreasingSubsequences(a));
        System.out.println("All increasing subsequences: " + getAllIncreasingSubsequences(a));
        int[] b = new int[]{1, 3, 1, -1, 2};
        System.out.println("Max sum is " + maxSum(b));
    }
}
