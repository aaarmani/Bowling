package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

public interface Player {
    int FRAME_SIZE = 10;

    String getName();
    int getScore(int round);
    int getTotalScore();
    FrameStatus newShot(int round, int numberOfPins);
    Frame getFrame(int round);
}
