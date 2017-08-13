package andersonarmani.bowling.model;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */

public class PlayerImpl implements Player{
    private String name;

    public PlayerImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScore(int round) {
        return 0;
    }

    @Override
    public int getTotalScore() {
        return 0;
    }

    @Override
    public FrameStatus newShot(int round, int numberOfPins) {
        return null;
    }

    @Override
    public Frame getFrame(int round) {
        return null;
    }
}
