package andersonarmani.bowling.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */
public class FinalFrameTest {
    @Test
    public void createFinalFrameTest() {
        Frame frame = new FinalFrame();

        assertEquals(0, frame.getScore());
        assertEquals(FrameStatus.EMPTY, frame.getStatus());
    }

    @Test
    public void simpleScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins = Frame.MAX_PINS -1;

        frame.setTurnPins(quantityOfPins);

        assertEquals(FrameStatus.PLAYING, frame.getStatus());
    }

    @Test
    public void simpleStrikeScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins = Frame.MAX_PINS;

        frame.setTurnPins(quantityOfPins);

        assertEquals(FrameStatus.STRIKE, frame.getStatus());
    }

    @Test
    public void doubleSimpleScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 6;
        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        assertEquals(FrameStatus.COMPLETED, frame.getStatus());
    }

    @Test
    public void doubleSpareScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 9;
        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        assertEquals(FrameStatus.SPARE, frame.getStatus());
    }

    @Test
    public void overSimpleScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins = 11;
        boolean throwResult = false;

        try {
            frame.setTurnPins(quantityOfPins);
        } catch (IllegalArgumentException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }

    @Test
    public void overDoubleScoreTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 11;
        boolean throwResult = false;

        frame.setTurnPins(quantityOfPins1);
        try {
            frame.setTurnPins(quantityOfPins2);
        } catch (IllegalArgumentException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }

    // NEW for final frame
    @Test
    public void withoutSpareTry1TurnTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 2;
        int quantityOfPins3 = 3;
        boolean throwResult = false;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        try {
            frame.setTurnPins(quantityOfPins3);
        } catch(IllegalStateException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }

    @Test
    public void withSpareTry1ExtraTurnTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 9;
        int quantityOfPins3 = 3;
        int totalScore = 13;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);
        frame.setTurnPins(quantityOfPins3);

        assertEquals(FrameStatus.COMPLETED, frame.getStatus());
        assertEquals(totalScore, frame.getScore());
    }

    @Test
    public void withSpareTry2ExtraTurnsTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 9;
        int quantityOfPins3 = 3;
        int quantityOfPins4 = 3;
        boolean throwResult = false;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);
        frame.setTurnPins(quantityOfPins3);
        try {
            frame.setTurnPins(quantityOfPins4);
        } catch (IllegalStateException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }

    @Test
    public void withStrikeTry1ExtraTurnTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = Frame.MAX_PINS;
        int quantityOfPins2 = 3;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        assertEquals(FrameStatus.STRIKE_EXTRA, frame.getStatus());
    }

    @Test
    public void withStrikeTry2ExtraTurnsTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = Frame.MAX_PINS;
        int quantityOfPins2 = 2;
        int quantityOfPins3 = 3;
        int totalScore = Frame.MAX_PINS + quantityOfPins2 + quantityOfPins3;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);
        frame.setTurnPins(quantityOfPins3);

        assertEquals(FrameStatus.COMPLETED, frame.getStatus());
        assertEquals(totalScore, frame.getScore());
    }

    @Test
    public void withStrikeTry3ExtraTurnsTest() {
        Frame frame = new FinalFrame();
        int quantityOfPins1 = Frame.MAX_PINS;
        int quantityOfPins2 = 2;
        int quantityOfPins3 = 3;
        int quantityOfPins4 = 4;
        boolean throwResult = false;

        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);
        frame.setTurnPins(quantityOfPins3);

        try {
            frame.setTurnPins(quantityOfPins4);
        } catch (IllegalStateException e) {
            throwResult = true;
        }

        assertTrue(throwResult);
    }
}