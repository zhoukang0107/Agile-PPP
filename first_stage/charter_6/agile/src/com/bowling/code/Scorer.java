package com.bowling.code;

public class Scorer {
    private int ball = 0;
    private int[] itsThrows = new int[21];
    private int itsCurrentThrow = 0;
    public void addThrow(int pins) {

    }

    public int scoreForFrame(int theFrame) {
        int score = 0;
        ball = 0;
        for (int currentFrame = 0;currentFrame<theFrame;currentFrame++){
            if (strike()){  //全中
                score += 10 + nextTwoBallsForStrike();
                ball++;
            } else if (spare()) {
                score += 10 + nextBallForSpare();
                ball += 2;
            } else {
                score += twoBallsInFrame();
                ball += 2;
            }
            //score = firstThrow + secondThrow;
        }
        return score;
    }


    private int nextTwoBallsForStrike() {
        return itsThrows[ball+1]+itsThrows[ball+2];
    }

    private int nextBallForSpare() {
        return itsThrows[ball+2];
    }

    private boolean spare() {
        return itsThrows[ball]+itsThrows[ball+1]==10;
    }

    private boolean strike() {
        return itsThrows[ball]==10;
    }
    private int twoBallsInFrame() {
        return itsThrows[ball]+itsThrows[ball+1];
    }

}
