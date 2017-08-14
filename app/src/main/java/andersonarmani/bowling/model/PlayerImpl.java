package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

public class PlayerImpl implements Player {
    private String name;
    private Frame frameArray[] = new FinalFrame[FRAME_SIZE];

    public PlayerImpl(String name) {
        this.name = name;
        for(int i = 0; i < frameArray.length; i++) {
            frameArray[i] = new FinalFrame();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore(int round) {
        return frameArray[round].getScore();
    }

    @Override
    public int getTotalScore() {
        int totalScore = 0;
        for (Frame frame: frameArray) {
            totalScore += frame.getScore();
        }

        return totalScore;
    }

    @Override
    public FrameStatus newShot(int round, int numberOfPins) {
        frameArray[round].setTurnPins(numberOfPins);

        FrameStatus frameStatus = frameArray[round].getStatus();
        checkStrikeSparePoints(round, frameStatus, numberOfPins);

        return frameStatus;
    }

    @Override
    public Frame getFrame(int round) {
        return frameArray[round];
    }

    private void checkStrikeSparePoints(int round, FrameStatus frameStatus, int numberOfPins) {

        //Just check for the first new shot and round > 0 or if the Frame is not completed
        if(frameStatus.equals(FrameStatus.EMPTY) || round == 0) {
            return;
        }

        //check spare
        if(frameStatus.equals(FrameStatus.PLAYING) || frameStatus.equals(FrameStatus.STRIKE)) {
            if(frameArray[round -1].getStatus().equals(FrameStatus.SPARE)
                    || frameArray[round -1].getStatus().equals(FrameStatus.STRIKE)) {
                frameArray[round - 1].setTurnPins(numberOfPins);
            }

            //If waiting for the second extra ball
            if(round > 1 && frameArray[round -2].getStatus().equals(FrameStatus.STRIKE_EXTRA)) {
                frameArray[round - 2].setTurnPins(numberOfPins);
            }
        }
        else {
            if(frameArray[round -1].getStatus().equals(FrameStatus.STRIKE_EXTRA)) {
                frameArray[round - 1].setTurnPins(numberOfPins);
            }
            else if(round > 1 && frameArray[round -2].getStatus().equals(FrameStatus.STRIKE_EXTRA)) {
                frameArray[round - 2].setTurnPins(numberOfPins);
            }
        }
    }
}
