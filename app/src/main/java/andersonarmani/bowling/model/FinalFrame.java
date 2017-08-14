package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

public class FinalFrame extends Frame {
    private static final int MAX_TRIES = 4;
    private FrameStatus frameStatus;
    private int         score;
    private int         turnPins[];

    public FinalFrame() {
        this.frameStatus = FrameStatus.EMPTY;
        this.turnPins = new int[MAX_TRIES];
    }

    public void setTurnPins(int turnPins) {
        if(turnPins > Frame.MAX_PINS) {
            throw new IllegalArgumentException("Max Pins is " + Frame.MAX_PINS + " tried " + turnPins);
        }

        setStatus(turnPins);
    }

    public int getScore() {
        return score;
    }

    public FrameStatus getStatus() {
        return frameStatus;
    }

    @Override
    int getTurnedPins() {
        return this.turnPins[0] + turnPins[1];
    }

    @Override
    void setExtraBalls(int pins1, int pins2) {
        if(frameStatus.equals(FrameStatus.SPARE) || frameStatus.equals(FrameStatus.STRIKE)) {
            turnPins[2] = pins1;
            turnPins[3] = pins2;
            frameStatus = FrameStatus.COMPLETED;
        }
    }

    /**
     * Set the frame status
     * @param turnPins
     */
    private void setStatus(int turnPins) {
        switch (this.frameStatus) {
            case EMPTY:
                this.turnPins[0] = turnPins;

                if(turnPins == MAX_PINS)
                    this.frameStatus = FrameStatus.STRIKE;
                else
                    this.frameStatus = FrameStatus.PLAYING;
                break;
            case PLAYING:
                this.turnPins[1] = turnPins;

                if((this.turnPins[0] + turnPins) == MAX_PINS)
                    this.frameStatus = FrameStatus.SPARE;
                else {
                    this.frameStatus = FrameStatus.COMPLETED;
                    this.score = this.turnPins[0] + this.turnPins[1];
                }
                break;
            case SPARE:
                this.turnPins[2] = turnPins;
                this.frameStatus = FrameStatus.COMPLETED;
                this.score = MAX_PINS + turnPins;
                break;
            case STRIKE:
                this.turnPins[2] = turnPins;
                this.frameStatus = FrameStatus.STRIKE_EXTRA;
                break;
            case STRIKE_EXTRA:
                this.turnPins[3] = turnPins;
                this.frameStatus = FrameStatus.COMPLETED;
                this.score = MAX_PINS + this.turnPins[2] + this.turnPins[3];
                break;
            case COMPLETED:
                throw new IllegalStateException("Over operation for this frame");
        }
    }
}
