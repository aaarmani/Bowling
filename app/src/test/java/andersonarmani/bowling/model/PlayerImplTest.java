package andersonarmani.bowling.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */
public class PlayerImplTest {
    private final static String PLAYER_NAME = "player name";
    private Player player;

    @Before
    public void createUser() {
        player = new PlayerImpl(PLAYER_NAME);
    }

    @Test
    public void createPlayerTest() {
        assertEquals(PLAYER_NAME, player.getName());
        assertNotNull(player.getFrame(Player.FRAME_SIZE -1)); //get last frame
    }

    @Test
    public void getScoreRoundTest() {
        int num1 = 4;
        int num2 = 5;
        player.newShot(0, num1);
        player.newShot(0, num2);
        assertEquals(num1 + num2, player.getScore(0));
    }

    @Test
    public void getFinalScoreWithSpareTest() {
        int TOTAL_VALUE = 50;
        player.newShot(0, 4);
        player.newShot(0, 6);   //SPARE
        player.newShot(1, 3);
        player.newShot(1, 0);
        player.newShot(2, 5);   //TOTAL until here (10 + 3) + 3 + 5 = 21
        player.newShot(2, 0);
        player.newShot(3, 1);   //27
        player.newShot(3, 0);
        player.newShot(4, 2);   //29
        player.newShot(4, 0);
        player.newShot(5, 3);   //32
        player.newShot(5, 0);
        player.newShot(6, 4);   //36
        player.newShot(6, 0);
        player.newShot(7, 5);   //41
        player.newShot(7, 0);
        player.newShot(8, 6);   //47
        player.newShot(8, 0);
        player.newShot(9, 7);   //55
        player.newShot(9, 0);

        assertEquals(TOTAL_VALUE, player.getTotalScore());
    }

    @Test
    public void getFinalScoreWithStrikeTest() {
        int TOTAL_VALUE = 55;
        player.newShot(0, Frame.MAX_PINS);
        player.newShot(1, 3);
        player.newShot(1, 0);
        player.newShot(2, 5);   //TOTAL until here (10 + 3 + 5) + 3 + 5 = 26
        player.newShot(2, 0);
        player.newShot(3, 1);   //27
        player.newShot(3, 0);
        player.newShot(4, 2);   //29
        player.newShot(4, 0);
        player.newShot(5, 3);   //32
        player.newShot(5, 0);
        player.newShot(6, 4);   //36
        player.newShot(6, 0);
        player.newShot(7, 5);   //41
        player.newShot(7, 0);
        player.newShot(8, 6);   //47
        player.newShot(8, 0);
        player.newShot(9, 7);   //55
        player.newShot(9, 0);

        assertEquals(TOTAL_VALUE, player.getTotalScore());
    }

    /**
     * Case test from problem description
     * 5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5
     * Five pins on the first ball of all ten frames.
     * Second ball of each frame hits all five remaining
     * pins, a spare.
     * One bonus ball, hits five pins.
     * Score for each frame == 10 + score for next one
     * ball == 10 + 5 == 15
     * Total score == 10 frames x 15 == 150
     */
    @Test
    public void getFinalScoreWithMultipleSpareTest() {
        int TOTAL_VALUE = 50;
        player.newShot(0, 5);
        player.newShot(0, 5);   //SPARE
        player.newShot(1, 5);
        player.newShot(1, 5);   //SPARE
        player.newShot(2, 5);
        player.newShot(2, 5);   //SPARE
        player.newShot(3, 5);
        player.newShot(3, 5);   //SPARE
        player.newShot(4, 5);
        player.newShot(4, 5);   //SPARE
        player.newShot(5, 5);
        player.newShot(5, 5);   //SPARE
        player.newShot(6, 5);
        player.newShot(6, 5);   //SPARE
        player.newShot(7, 5);
        player.newShot(7, 5);   //SPARE
        player.newShot(8, 5);
        player.newShot(8, 5);   //SPARE
        player.newShot(9, 5);
        player.newShot(9, 5);   //SPARE
        player.newShot(9, 5);   //EXTRA BALL

        assertEquals(TOTAL_VALUE, player.getTotalScore());
    }

    /**
     * Case test from problem description
     * 9-|9-|9-|9-|9-|9-|9-|9-|9-|9-||
     * Nine pins hit on the first ball of all ten frames.
     * Second ball of each frame misses last remaining pin.
     * No bonus balls.
     * Score for each frame == 9
     * Total score == 10 frames x 9 == 90
     */
    @Test
    public void getFinalScoreTest() {
        int TOTAL_VALUE = 90;
        player.newShot(0, 9);
        player.newShot(0, 0);
        player.newShot(1, 9);
        player.newShot(1, 0);
        player.newShot(2, 9);
        player.newShot(2, 0);
        player.newShot(3, 9);
        player.newShot(3, 0);
        player.newShot(4, 9);
        player.newShot(4, 0);
        player.newShot(5, 9);
        player.newShot(5, 0);
        player.newShot(6, 9);
        player.newShot(6, 0);
        player.newShot(7, 9);
        player.newShot(7, 0);
        player.newShot(8, 9);
        player.newShot(8, 0);
        player.newShot(9, 9);
        player.newShot(9, 0);

        assertEquals(TOTAL_VALUE, player.getTotalScore());
    }

    /**
     * Test the maximum value, getting Strike in all rounds
     * Case test from problem description
     * X|X|X|X|X|X|X|X|X|X||XX
     * Ten strikes on the first ball of all ten frames.
     * Two bonus balls, both strikes.
     * Score for each frame == 10 + score for next two
     * balls == 10 + 10 + 10 == 30
     * Total score == 10 frames x 30 == 300
     */
    @Test
    public void getTotalScoreTest() {
        int MAX_VALUE = 300;

        player.newShot(0, Frame.MAX_PINS);
        player.newShot(1, Frame.MAX_PINS);
        player.newShot(2, Frame.MAX_PINS);
        player.newShot(3, Frame.MAX_PINS);
        player.newShot(4, Frame.MAX_PINS);
        player.newShot(5, Frame.MAX_PINS);
        player.newShot(6, Frame.MAX_PINS);
        player.newShot(7, Frame.MAX_PINS);
        player.newShot(8, Frame.MAX_PINS);
        player.newShot(9, Frame.MAX_PINS);
        player.newShot(9, Frame.MAX_PINS);  //EXTRA 1
        player.newShot(9, Frame.MAX_PINS);  //EXTRA 2

        assertEquals(MAX_VALUE, player.getTotalScore());
    }

    /**
     * Case test from problem description
     * X|7/|9-|X|-8|8/|-6|X|X|X||81
     */
    @Test
    public void getFinalScoreComplexRoundsTest() {
        int TOTAL_VALUE = 167;
        player.newShot(0, Frame.MAX_PINS);  //Strike
        player.newShot(1, 7);
        player.newShot(1, 3);   //spare
        player.newShot(2, 9);
        player.newShot(2, 0);
        player.newShot(3, Frame.MAX_PINS);   //Strike
        player.newShot(4, 0);
        player.newShot(4, 8);
        player.newShot(5, 8);
        player.newShot(5, 2);   //spare
        player.newShot(6, 0);
        player.newShot(6, 6);
        player.newShot(7, Frame.MAX_PINS);  //strike
        player.newShot(8, Frame.MAX_PINS);  //strike
        player.newShot(9, Frame.MAX_PINS);  //strike
        player.newShot(9, 8);   //extra1
        player.newShot(9, 1);   //extra2

        assertEquals(TOTAL_VALUE, player.getTotalScore());
    }
}