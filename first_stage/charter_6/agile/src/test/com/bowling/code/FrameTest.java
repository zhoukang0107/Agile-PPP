package test.com.bowling.code;

import com.bowling.code.Frame;
import com.bowling.code.Game;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static junit.framework.TestCase.assertEquals;

/**
 * Frame Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 10, 2019</pre>
 */
public class FrameTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    private Game game;


    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    /**
     * Method: ScoreNoThrows()
     */
    @Test
    public void testScoreNoThrows() throws Exception {
//TODO: Test goes here...
        Frame frame = new Frame();
        //frame.add(5);
        assertEquals(0, frame.getScore());
    }

    @Test
    public void testAddOneThrow() {
        Frame frame = new Frame();
        frame.add(5);
        assertEquals(5, frame.getScore());
    }

    @Test
    public void testOneThrows() {
        Game game = new Game();
        game.add(5);
        assertEquals(5, game.score());
        assertEquals(1, game.getCurrentFrame());

    }

    @Test
    public void testTwoThrowsNoMark() {
        Game game = new Game();
        game.add(4);
        game.add(5);
        assertEquals(9, game.score());
        assertEquals(2, game.getCurrentFrame());
    }

    @Test
    public void testFourThrowsNoMark() {
        Game game = new Game();
        game.add(4);
        game.add(5);
        game.add(7);
        game.add(2);
        assertEquals(18, game.score());
        assertEquals(9, game.scoreForFrame(1));
        assertEquals(18, game.scoreForFrame(2));
        assertEquals(3, game.getCurrentFrame());
    }

    @Test
    public void testSimpleSpare(){
        game.add(3);
        game.add(7);
        game.add(3);
        assertEquals(13,game.scoreForFrame(1));
        assertEquals(2, game.getCurrentFrame());
    }

    @Test
    public void testSimpleFrameAfterSpare(){
        game.add(3);
        game.add(7);
        game.add(3);
        game.add(2);
        assertEquals(13,game.scoreForFrame(1));
        assertEquals(13,game.scoreForFrame(2));
        assertEquals(18,game.score());
        assertEquals(3, game.getCurrentFrame());
    }

}
