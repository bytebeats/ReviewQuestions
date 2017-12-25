package me.danielpan.review.question;

import me.danielpan.review.model.ChessNode;

import java.io.File;
import java.util.Stack;

/**
 * Created by Daniel Pan on 26/09/2017 : 14:46.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class Algorithm {
    /*在console中打印目录的树状结构*/
    public static void printFilesInTree(File file) {
        printFilesInTree(file, 0);
    }

    /*在console中打印目录的树状结构*/
    public static void printFilesInTree(File file, int level) {
        if (file == null || !file.exists()) {
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        System.out.println(file.getName());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                printFilesInTree(subFile, level++);
            }
        }
    }

    public static int reverse(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;
        i = (i << 24) | ((i & 0xff00) << 8) |
                ((i >>> 8) & 0xff00) | (i >>> 24);
        return i;
    }

    public static int reverse1(int i) {
        i = (i & 0x55555555) << 1 | (i >>> 1) & 0x55555555;//0101,0101
        i = (i & 0x33333333) << 2 | (i >>> 2) & 0x33333333;//0011,0011
        i = (i & 0x0f0f0f0f) << 4 | (i >>> 4) & 0x0f0f0f0f;//0000,1111
        i = (i & 0x00ff00ff) << 8 | (i >>> 8) & 0x00ff00ff;//1111,1111
        i = (i & 0x0000ffff) << 16 | (i >>> 16) & 0x0000ffff;//11111111,11111111
        return i;
    }

    public static int reverse2(int i) {
        int s = 32;
        int mask = ~0;
        while ((s >>= 1) >= 1) {
            mask ^= (mask << s);
//            i = (i << s) & ~mask | (i >> s) & mask;//(i << s) & ~mask is equal to (i & mask) << s
            i = (i & mask) << s | (i >> s) & mask;
        }
        return i;
    }

    /*棋盘上两点马步距离*/
    public static int horseStep(ChessNode start, ChessNode end) {//贪心算法和局部搜索
        if (start != null && end != null) {
            int steps = 0;
            int distanceX = Math.abs(start.x - end.x);
            int distanceY = Math.abs(start.y - end.y);
            while (distanceX > 10 || distanceY > 10) {//A*
                if (distanceX > distanceY) {
                    distanceX -= 2;
                    distanceY -= 1;
                } else {
                    distanceX -= 1;
                    distanceY -= 2;
                }
                steps++;
            }
            System.out.println("distance X: " + distanceX + ", Y: " + distanceY + ", steps: " + steps);
            Stack<ChessNode> stack = new Stack<>();
            stack.push(new ChessNode(distanceX, distanceY));
            int[][] distance = new int[50][50];
            distance[distanceX][distanceY] = 0;
            ChessNode.HorseStep[] horseSteps = ChessNode.allHorseSteps();
            while (!stack.isEmpty()) {//bfs
                ChessNode pop = stack.pop();
                for (int i = 0; i < horseSteps.length; i++) {
                    ChessNode.HorseStep horseStep = horseSteps[i];
                    int x = pop.x + horseStep.x;
                    int y = pop.y + horseStep.y;
                    if (x > 10 || y > 10) {
                        continue;
                    }
                    if (x < 0 || y < 0) {
                        continue;
                    }
                    if (distance[x][y] != 0) {
                        continue;
                    }
                    distance[x][y] = distance[pop.x][pop.y] + 1;
                    stack.push(new ChessNode(x, y));
                }
            }
            for (int i = 0; i < distance.length; i++) {
                for (int j = 0; j < distance[i].length; j++) {
                    System.out.print(distance[i][j] + ",");
                }
                System.out.println();
            }
            steps += distance[distanceX][distanceY];
            return steps;
        }
        return -1;
    }

    /*欧几里德算法, 求两个数的最大公约数*/
    public static int gcd(int p, int q) {
        return q == 0 ? p : gcd(q, p % q);
    }

    public static int gcd1(int p, int q) {
        while (q > 0) {
            int tmp = q;
            q = p % q;
            p = tmp;
        }
        return p;
    }
}
