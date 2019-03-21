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
        //assertEquals(5, game.score());
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
       /* game.add(3);
        game.add(7);
        game.add(3);
        assertEquals(13,game.scoreForFrame(1));
        assertEquals(2, game.getCurrentFrame());*/
        game.add(1);
        game.add(4); //5
        game.add(4);
        game.add(5); //5+9 = 14
        game.add(6);
        game.add(5); //11+14 = 25
        game.add(6);
        game.add(6); //12+25 = 37
        game.add(10); //10+0+1+37 = 48
        game.add(0);
        game.add(1); //1+48 = 49
        game.add(7);
        game.add(3); //7+3+6+48 = 64
        game.add(6);
        game.add(4); //6+4+10+64 = 84
        game.add(10); //10+2+8+84 = 104
        game.add(2);
        game.add(8); //8+2+6+104 = 121
        game.add(6);
        assertEquals(121,game.score());
    }

    @Test
    public void testSimpleFrameAfterSpare(){
        game.add(3);
        game.add(7);
        game.add(3);
        game.add(2);
        assertEquals(13,game.scoreForFrame(1));
        assertEquals(18,game.scoreForFrame(2));
        assertEquals(18,game.score());
        assertEquals(3, game.getCurrentFrame());
    }

    @Test
    public void test(){
        game.add(10);
        game.add(3);
        game.add(6);
        assertEquals(19, game.scoreForFrame(1));
        assertEquals(28,game.score());
        assertEquals(3,game.getCurrentFrame());
    }

    @Test
    public void testPerfectGame(){
        for (int i=0;i<12;i++){
            game.add(10);
        }
        assertEquals(300,game.score());
        assertEquals(11,game.getCurrentFrame());
    }

    @Test
    public void testEndOfArry(){
        for (int i=0;i<9;i++){
            game.add(0);
            game.add(0);
        }
        game.add(2);
        game.add(8);
        game.add(10);
        assertEquals(20,game.score());
        //assertEquals(11,game.getCurrentFrame());
    }

    @Test
    public void testHeartBreak(){
        for (int i=0;i<11;i++){
            game.add(10);
        }
        game.add(9);
        assertEquals(299,game.score());
    }

    @Test
    public void testTenthFrameSpare(){
        for (int i=0;i<9;i++){
            game.add(10);
        }
        game.add(9);
        game.add(1);
        game.add(1);
        assertEquals(270,game.score());
    }
}
