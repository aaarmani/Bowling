package andersonarmani.bowling.model;

import java.util.List;

/**
 * Created by Armani andersonaramni@gmail.com on 14/08/2017.
 */

public interface Game {
    void createPlayers(List<String> playersName);
    List<Player> getPlayers();
    void start();
    void stop();
    void newShot(int numberOfPins);
    boolean isFinish();
    int getPlayerIndex();
    int getRoundIndex();
    Frame getPlayerFrame(int playerIndex, int frameIndex);
}
