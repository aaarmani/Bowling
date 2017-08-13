package andersonarmani.bowling.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Armani andersonaramni@gmail.com on 13/08/2017.
 */
public class SimpleFrameTest {

    @Test
    public void createSimpleFrameTest() {
        Frame frame = new SimpleFrame();

        assertEquals(0, frame.getScore());
        assertEquals(FrameStatus.empty, frame.getStatus());
    }


    @Test
    public void simpleScoreTest() {
        Frame frame = new SimpleFrame();
        int quantityOfPins = Frame.MAX_PINS -1;

        frame.setTurnPins(quantityOfPins);

        assertEquals(FrameStatus.playing, frame.getStatus());
    }

    @Test
    public void simpleStrikeScoreTest() {
        Frame frame = new SimpleFrame();
        int quantityOfPins = Frame.MAX_PINS;

        frame.setTurnPins(quantityOfPins);

        assertEquals(FrameStatus.strike, frame.getStatus());
    }

    @Test
    public void doubleSimpleScoreTest() {
        Frame frame = new SimpleFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 6;
        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        assertEquals(FrameStatus.completed, frame.getStatus());
    }

    @Test
    public void doubleSpareScoreTest() {
        Frame frame = new SimpleFrame();
        int quantityOfPins1 = 1;
        int quantityOfPins2 = 9;
        frame.setTurnPins(quantityOfPins1);
        frame.setTurnPins(quantityOfPins2);

        assertEquals(FrameStatus.spare, frame.getStatus());
    }

    @Test
    public void overSimpleScoreTest() {
        Frame frame = new SimpleFrame();
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
        Frame frame = new SimpleFrame();
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
}