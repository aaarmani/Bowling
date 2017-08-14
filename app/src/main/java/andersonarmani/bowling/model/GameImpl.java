package andersonarmani.bowling.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Armani andersonaramni@gmail.com on 14/08/2017.
 */

public class GameImpl implements Game {
    private int MAX_ROUNDS = 10;
    private List<Player> mListPlayers;
    private int currentPlayerIndex = 0;
    private int currentGameRound = 0;
    private boolean isPlaying = false;

    @Override
    public void createPlayers(List<String> playersName) {
        if(playersName == null || playersName.isEmpty()) {
            throw new IllegalArgumentException("List of players couldn't be NULL or Zero");
        }

        mListPlayers = new ArrayList<>();

        for(int i = 0; i < playersName.size(); i++) {
            mListPlayers.add(new PlayerImpl(playersName.get(i)));
        }
    }

    @Override
    public List<Player> getPlayers() {
        return mListPlayers;
    }

    @Override
    public void start() {
        if(mListPlayers == null) {
            throw new IllegalStateException("Needed add some Players");
        }

        isPlaying = true;
    }

    @Override
    public void stop() {
        isPlaying = false;
    }

    @Override
    public void newShot(int numberOfPins) {
        mListPlayers.get(currentPlayerIndex).newShot(currentGameRound, numberOfPins);
        Frame frame = mListPlayers.get(currentPlayerIndex).getFrame(currentGameRound);

        if(!frame.getStatus().equals(FrameStatus.EMPTY)
                && !frame.getStatus().equals(FrameStatus.PLAYING) && (currentGameRound < (MAX_ROUNDS -1))) {

            currentPlayerIndex = (++currentPlayerIndex >= mListPlayers.size())
                    ? currentPlayerIndex = 0 : currentPlayerIndex;

            if(currentPlayerIndex == 0) {
                currentGameRound++;
            }
        }
        else if((MAX_ROUNDS-1) == currentGameRound && frame.getStatus().equals(FrameStatus.COMPLETED)) {
            currentPlayerIndex = (++currentPlayerIndex >= mListPlayers.size())
                    ? currentPlayerIndex = 0 : currentPlayerIndex;

            if(currentPlayerIndex == 0) {
                currentGameRound++;
            }

            if(currentGameRound >= MAX_ROUNDS) {
                stop();
            }
        }
    }

    @Override
    public boolean isFinish() {
        return !isPlaying;
    }

    @Override
    public int getPlayerIndex() {
        return currentPlayerIndex;
    }

    @Override
    public int getRoundIndex() {
        return currentGameRound;
    }

    @Override
    public Frame getPlayerFrame(int playerIndex, int frameIndex) {
        return mListPlayers.get(playerIndex).getFrame(frameIndex);
    }
}
