package me.danielpan.review.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Daniel Pan on 14/09/2017 : 15:45.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class StringQuestions {
    /*给出两个字符串s1和s2，如何判断s1是否是一个翻转的s2.  比如 s1 = "yixin", s2 = "ixiny" 或者 s2= "xinyi" 或者 s2="inyix" 的情况，为true。 根据情况可以考察字符串匹配的算法，如KMP算法等*/
    public static boolean containsReverseString(String src, String target) {
        if (src == null || target == null) {
            return false;
        }
        if ("".equals(target)) {
            return true;
        }
        if (src.length() < target.length()) {
            return false;
        }
        for (int i = 0; i <= src.length() - target.length(); i++) {
            if (containsReverseString(src, i, i + target.length() - 1, target)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsReverseString(String src, int start, int end, String target) {
        boolean result = true;
        for (int i = 0; i <= target.length() / 2; i++) {
            result &= (target.charAt(i) == src.charAt(end - i) && target.charAt(target.length() - 1 - i) == src.charAt(start + i));
        }
        return result;
    }

    /*表达式计算，只有加减和乘以及整数, 不需要考虑溢出及额外情况, 如计算"12+3+3*45-6"只允许一遍遍历(one pass) */
    public static int computeExpressionSimple(String expression) {
        List<String> metas = new ArrayList<>();
        int startIndex = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-') {
                metas.add(expression.substring(startIndex, i));
                metas.add(String.valueOf(expression.charAt(i)));
                startIndex = i + 1;
            }
        }

        return 0;
    }

    /*
    *23-(32+2)*3-12
    */
    public static int computeExpression(String expression) {
        String[] metas = splitExpression(expression);
        Stack<String> opStack = new Stack<>();
        List<String> reversePolish = new ArrayList<>();
        for (String meta : metas) {
            if (meta == null || "".equals(meta)) {
                opStack.push(meta);
            } else if ("(".equals(meta)) {
                opStack.push(meta);
            } else if (")".equals(meta)) {
                while (!opStack.peek().equals("(")) {
                    reversePolish.add(opStack.pop());
                }
                opStack.pop();
            } else if ("+".equals(meta) || "-".equals(meta) || "*".equals(meta) || "/".equals(meta)) {
                while (("*".equals(opStack.peek()) || "/".equals(opStack.peek())) && ("+".equals(meta) || "-".equals(meta))) {
                    reversePolish.add(opStack.pop());
                }
            } else {
                reversePolish.add(meta);
            }
        }
        Stack<String> postExpression = new Stack<>();
        for (String meta : reversePolish) {
            if ("+".equals(meta) || "-".equals(meta) || "*".equals(meta) || "/".equals(meta)) {
                int num1 = Integer.parseInt(postExpression.pop());
                int num2 = Integer.parseInt(postExpression.pop());
                int result = 0;
                switch (meta) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num1 / num2;
                        break;
                }
                postExpression.push(String.valueOf(result));
            } else {
                postExpression.push(meta);
            }
        }
        return Integer.parseInt(postExpression.pop());
    }

    private static String[] splitExpression(String expression) {
        return null;
    }

    //KMP中的核心算法，获得记录跳转状态的next数组
    public static int[] next(String sub) {
        int len = sub.length();
        int j = 0;

        int next[] = new int[len + 1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0] = next[1] = 0;

        for (int i = 1; i < len; i++) {//i表示字符串的下标，从0开始
            while (j > 0 && sub.charAt(i) != sub.charAt(j)) {//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
                j = next[j];
            }
            if (sub.charAt(i) == sub.charAt(j)) {
                j++;
            }
            next[i + 1] = j;
        }

        return next;
    }

    public static void pattern(String original, String find, int[] next) {
        int j = 0;
        for (int i = 0; i < original.length(); i++) {
            while (j > 0 && original.charAt(i) != find.charAt(j)) {
                j = next[j];
            }
            if (original.charAt(i) == find.charAt(j)) {
                j++;
            }
            if (j == find.length()) {
                System.out.println("find at position " + (i - j + 1));
                System.out.println(original.subSequence(i - j + 1, i + 1));
                j = next[j];
            }
        }
    }

    public static String reverse(String src) {
        if (src == null) {
            return null;
        }
        char[] chars = src.toCharArray();
        int s = 0, e = chars.length - 1;
        char tmp;
        while (s < e) {
            tmp = chars[s];
            chars[s] = chars[e];
            chars[e] = tmp;
            s++;
            e--;
        }
        return String.valueOf(chars);
    }

    public static void reverse(char[] chars, int start, int end) {
        if (chars == null || chars.length == 0 || start < 0 || end > chars.length - 1 || start > end) {
            return;
        }
        int i = start, j = end;
        char tmp;
        while (i < j) {
            tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
    }

    public static String reverse1(String src) {
        if (src == null) {
            return null;
        }
        char[] chars = src.toCharArray();
        int size = chars.length;
        char tmp;
        for (int i = 0; i < size / 2; i++) {
            tmp = chars[i];
            chars[i] = chars[size - i - 1];
            chars[size - i - 1] = tmp;
        }
        return String.valueOf(chars);
    }

    public static String reverseSentence(String sentence) {
        if (sentence != null) {
            char[] chars = sentence.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == ' ') {
                    reverse(chars, index, i - 1);
                    index = i + 1;
                } else if (i == chars.length - 1) {
                    reverse(chars, index, i);
                } else {
                    continue;
                }
            }
            reverse(chars, 0, chars.length - 1);
            return String.valueOf(chars);
        }
        return null;
    }

    public static void main(String[] args) {
//        String src = "Deja vu";
        String src = "Deja";
        System.out.println(src);
        System.out.println("Reversed is: " + reverseSentence(src));
        System.out.println("finished");
    }
}