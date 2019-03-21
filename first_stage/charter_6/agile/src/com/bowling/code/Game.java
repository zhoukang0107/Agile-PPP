package com.bowling.code;

public class Game {
    //private int itsScore = 0;
    //private int[] itsThrows = new int[21];
    //private int itsCurrentThrow = 0;
    private int itsCurrentFrame = 1;
    //private int firstThrow = 0;
    //private int secondThrow = 0;
    private boolean firstThrowInFrame = true;
    //private int ball = 0;

    private Scorer itsScorer = new Scorer();
    public void add(int pins) {
        itsScorer.addThrow(pins);
        //itsThrows[itsCurrentThrow++] = pins;
        //itsScore += pins;
        //adjustCurrentFrame(pins);
        adjustCurrentFrame(pins);

    }

    /*private void adjustCurrentFrame() {
        if (firstThrow == true){
            firstThrow = false;
        } else {
            firstThrow = true;
            itsCurrentFrame ++;
        }
    }*/

   /* private void adjustCurrentFrame(int pins) {
        if (firstThrow == true){
            if (pins==10){ //全中
                itsCurrentFrame++;
            }else {
                firstThrow = false;
            }
        } else {
            firstThrow = true;
            itsCurrentFrame ++;
        }
        itsCurrentFrame = Math.min(itsCurrentFrame,11);
    }*/

    private void adjustCurrentFrame(int pins) {
        if ((firstThrowInFrame&&pins==10)
                ||(!firstThrowInFrame)){
            advanceFrame();
        } else {
            firstThrowInFrame = false;
        }

    }

    private boolean adjustFrameForStrike(int pins) {
        if (pins == 10){
            advanceFrame();
            return true;
        }
        return false;
    }

    private void advanceFrame() {
        itsCurrentFrame = Math.min(itsCurrentFrame+1,10);
    }

    public int score() {
        return scoreForFrame(itsCurrentFrame);
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

   /* public int scoreForFrame(int theFrame) {
        int score = 0;
        int ball = 0;
        for (int currentFrame = 0;currentFrame<theFrame;currentFrame++){
            //score += itsThrows[ball++] + itsThrows[ball++];
            int firstThrow = itsThrows[ball++];
            if (firstThrow==10){  //全中
                score += firstThrow + itsThrows[ball]+itsThrows[ball+1];
            } else {
                int secondThrow = itsThrows[ball++];
                int frameScore = firstThrow + secondThrow;
                if (frameScore == 10){//补中
                    score += frameScore + itsThrows[ball];
                } else {
                    score += frameScore;
                }
            }
            //score = firstThrow + secondThrow;
        }
        return score;
    }
*/

   /* public int scoreForFrame(int theFrame) {
        int score = 0;
        ball = 0;
        for (int currentFrame = 0;currentFrame<theFrame;currentFrame++){
            //score += itsThrows[ball++] + itsThrows[ball++];
            firstThrow = itsThrows[ball++];
            if (firstThrow==10){  //全中
                score += firstThrow + itsThrows[ball]+itsThrows[ball+1];
            } else {
                secondThrow = itsThrows[ball++];
                int frameScore = firstThrow + secondThrow;
                if (frameScore == 10){//补中
                    score += frameScore + itsThrows[ball];
                } else {
                    score += frameScore;
                }
            }
            //score = firstThrow + secondThrow;
        }
        return score;
    }*/
  /* public int scoreForFrame(int theFrame) {
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
   }*/

    public int scoreForFrame(int theFrame) {
        return itsScorer.scoreForFrame(theFrame);
    }
/*

    private int nextTwoBallsForStrike() {
        return itsThrows[ball+1]+itsThrows[ball+2];
    }

    private int nextBallForSpare() {
        return itsThrows[ball+2];
    }

    private int nextTwoBalls() {
        return itsThrows[ball]+itsThrows[ball+1];
    }

    private boolean strike() {
        return itsThrows[ball]==10;
    }
*/

   /* private int handleSecondThrow() {
       int score = 0;
       if (spare()){
           ball += 2;
           score += 10 + nextBall();
       } else{
           score += twoBallsInFrame();
           ball += 2;
       }
        return score;
    }*/

   /* private int twoBallsInFrame() {
        return itsThrows[ball]+itsThrows[ball+1];
    }

    private int nextBall() {
        return itsThrows[ball];
    }

    private boolean spare() {
        return itsThrows[ball]+itsThrows[ball+1]==10;
    }*/

    public int getCurrentFrame() {
        return itsCurrentFrame;
    }
}
