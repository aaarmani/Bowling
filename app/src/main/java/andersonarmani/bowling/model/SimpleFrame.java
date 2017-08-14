package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

public class SimpleFrame extends Frame {
    private static final int MAX_TURNS = 2;
    private FrameStatus frameStatus;
    private int         turnIndex;
    private int         score;
    private int         turnPins[];

    public SimpleFrame() {
        this.turnIndex = 0;
        this.frameStatus = FrameStatus.EMPTY;
        this.turnPins = new int[MAX_TURNS];
    }

    public void setTurnPins(int turnPins) {
        if(turnPins > Frame.MAX_PINS) {
            throw new IllegalArgumentException("Max Pins is " + Frame.MAX_PINS + " tried " + turnPins);
        }

        if(turnIndex > 0) {
            int numPins = this.turnPins[0] + turnPins;
            if(numPins > Frame.MAX_PINS) {
                throw new IllegalArgumentException("Max Pins is " + Frame.MAX_PINS + " tried " + numPins);
            }
        }

        this.turnPins[turnIndex] = turnPins;
        setStatus(turnPins);
    }

    public int getScore() {
        return score;
    }

    @Override
    int getTurnedPins() {
        return this.turnPins[0] + turnPins[1];
    }

    @Override
    void setExtraBalls(int pins1, int pins2) {
        turnPins[3] = pins1;
        turnPins[4] = pins2;
    }

    public FrameStatus getStatus() {
        return frameStatus;
    }

    /**
     * Set the frame status
     * @param turnPins
     */
    private void setStatus(int turnPins) {
        if(turnPins == Frame.MAX_PINS) {
            this.frameStatus = FrameStatus.STRIKE;
        }
        else {
            this.frameStatus = FrameStatus.PLAYING;
        }

        turnIndex++;
        if(turnIndex == MAX_TURNS) {
            if((this.turnPins[0] + this.turnPins[1]) == Frame.MAX_PINS) {
                this.frameStatus = FrameStatus.SPARE;
            }
            else {
                this.frameStatus = FrameStatus.COMPLETED;
            }
        }
    }
}
