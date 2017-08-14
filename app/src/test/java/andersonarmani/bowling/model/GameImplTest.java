package andersonarmani.bowling.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Armani andersonaramni@gmail.com on 14/08/2017.
 */
public class GameImplTest {
    Game mGame;
    @Before
    public void configGame() {
        mGame = new GameImpl();
    }

    @Test
    public void createPlayersNullTest() {
        boolean throwResult = false;
        Game game = new GameImpl();
        try {
            game.createPlayers(null);
        }catch (IllegalArgumentException e) {
            throwResult = true;
        }
        assertTrue(throwResult);
    }

    @Test
    public void createPlayersZeroTest() {
        boolean throwResult = false;
        Game game = new GameImpl();
        try {
            game.createPlayers(new ArrayList<String>());
        }catch (IllegalArgumentException e) {
            throwResult = true;
        }
        assertTrue(throwResult);
    }

    @Test
    public void getPlayersTest() {
        final String PLAYER1 = "player 1";
        final String PLAYER2 = "player 2";

        List<String> listPlayerName = new ArrayList<>();
        listPlayerName.add(PLAYER1);
        listPlayerName.add(PLAYER2);

        Game game = new GameImpl();
        game.createPlayers(listPlayerName);

        List<Player> listPlayers = game.getPlayers();
        assertEquals(listPlayers.get(0).getName(), PLAYER1);
        assertEquals(listPlayers.get(1).getName(), PLAYER2);
    }

    @Test
    public void startWithoutPlayersTest() throws Exception {
        boolean throwResult = false;

        try {
            mGame.start();
        } catch (IllegalStateException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }

    @Test
    public void stopTest() throws Exception {

    }

    @Test
    public void newShotTest() {
        List<String> listUsersName = new ArrayList<>();
        listUsersName.add("Player 1");

        mGame.createPlayers(listUsersName);
        mGame.newShot(Frame.MAX_PINS); //proceed a Strike
        mGame.newShot(5);
        mGame.newShot(0);
        Frame frame = mGame.getPlayerFrame(0, 0);
        assertEquals(10 + 5 + 0, frame.getScore());
    }

    @Test
    public void isFinishTest() throws Exception {

    }

    @Test
    public void getPlayerIndexTest() throws Exception {
        List<String> listUsersName = new ArrayList<>();
        listUsersName.add("Player 1");
        listUsersName.add("Player 2");

        mGame.createPlayers(listUsersName);

        assertEquals(0, mGame.getPlayerIndex());

        mGame.newShot(Frame.MAX_PINS); //proceed a Strike to be the next player (increment index)
        assertEquals(1, mGame.getPlayerIndex());

        mGame.newShot(5); //proceed a Spare to be the first player again
        mGame.newShot(5);
        assertEquals(0, mGame.getPlayerIndex());
    }

    @Test
    public void getPlayerFrameTest() throws Exception {
        List<String> listUsersName = new ArrayList<>();
        listUsersName.add("Player 1");

        mGame.createPlayers(listUsersName);
        //First Frame
        mGame.newShot(5);
        mGame.newShot(2);

        //Second Frame
        mGame.newShot(4);
        mGame.newShot(0);

        //get frame 1
        Frame frame = mGame.getPlayerFrame(0, 0);
        assertEquals(7, frame.getScore());

        //get frame 2
        frame = mGame.getPlayerFrame(0, 1);
        assertEquals(4, frame.getScore());
    }

    @Test
    public void getPlayerFrameOutOfRangeTest() throws Exception {
        boolean throwResult = false;
        List<String> listUsersName = new ArrayList<>();
        listUsersName.add("Player 1");

        mGame.createPlayers(listUsersName);
        //First Frame
        mGame.newShot(5);
        mGame.newShot(2);
        //Second Frame
        mGame.newShot(Frame.MAX_PINS);

        try {
            //get frame 1 user 2
            Frame frame = mGame.getPlayerFrame(1, 0);
        } catch (IndexOutOfBoundsException e) {
            throwResult = true;
        }
        assertTrue(throwResult);
    }

    @Test
    public void twoPlayersGameTest() {
        List<String> listUsersName = new ArrayList<>();
        listUsersName.add("Player 1");

        mGame.createPlayers(listUsersName);
    }
}