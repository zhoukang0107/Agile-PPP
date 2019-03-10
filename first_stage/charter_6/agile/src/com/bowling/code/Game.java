package com.bowling.code;

public class Game {
    private int itsScore = 0;
    private int[] itsThrows = new int[21];
    private int itsCurrentThrow = 0;
    private int itsCurrentFrame = 1;
    private boolean firstThrow = true;
    public void add(int pins) {
        itsThrows[itsCurrentThrow++] = pins;
        itsScore += pins;
        adjustCurrentFrame();

    }

    private void adjustCurrentFrame() {
        if (firstThrow == true){
            firstThrow = false;
        } else {
            firstThrow = true;
            itsCurrentFrame ++;
        }
    }

    public int score() {
        return itsScore;
    }

   /* public int scoreForFrame(int frame) {
        int score = 0;
        for (int ball = 0;frame>0&&(ball<itsCurrentThrow);ball+=2,frame--){
            score += itsThrows[ball] + itsThrows[ball+1];
        }
        return score;
    }*/

/*    public int scoreForFrame(int theFrame) {
        int score = 0;
        int ball = 0;
        for (int currentFrame = 0;currentFrame<theFrame;currentFrame++){
            //score += itsThrows[ball++] + itsThrows[ball++];
            int firstThrow = itsThrows[ball++];
            int secondThrow = itsThrows[ball++];
            int frameScore = firstThrow + secondThrow;
            if (frameScore == 10){//补中
                score += frameScore + itsThrows[ball++];
            } else {
                score += frameScore;
            }
            //score = firstThrow + secondThrow;
        }
        return score;
    }*/

    public int scoreForFrame(int theFrame) {
        int score = 0;
        int ball = 0;
        for (int currentFrame = 0;currentFrame<theFrame;currentFrame++){
            //score += itsThrows[ball++] + itsThrows[ball++];
            int firstThrow = itsThrows[ball++];
            int secondThrow = itsThrows[ball++];
            int frameScore = firstThrow + secondThrow;
            if (frameScore == 10){//补中
                score += frameScore + itsThrows[ball];
            } else {
                score += frameScore;
            }
            //score = firstThrow + secondThrow;
        }
        return score;
    }

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }
}
