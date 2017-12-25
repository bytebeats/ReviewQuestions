package me.danielpan.review.model;

/**
 * Created by Daniel Pan on 12/10/2017 : 16:54.
 * Email : happychinapc@gmail.com;
 * Motto : If you don't fight for what you want, don't you cry for what you lost
 */
public class ChessNode {
    public int x;
    public int y;

    public ChessNode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static class HorseStep {
        public int x;
        public int y;

        public HorseStep(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static HorseStep[] allHorseSteps() {
        int[] dx = {1, 1, -1, -1, 2, 2, -2, -2};
        int[] dy = {2, -2, 2, -2, 1, -1, 1, -1};
        HorseStep[] horseSteps = new HorseStep[dx.length];
        for (int i = 0; i < dx.length; i++) {
            horseSteps[i] = new HorseStep(dx[i], dy[i]);
        }
        return horseSteps;
    }

}
