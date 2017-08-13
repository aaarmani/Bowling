package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

abstract class Frame {
    static final int MAX_PINS = 10;

    abstract public void setTurnPins(int turnPins);
    abstract public int getScore();
    abstract public FrameStatus getStatus();
}
