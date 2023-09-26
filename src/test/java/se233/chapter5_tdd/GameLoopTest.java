package se233.chapter5_tdd;

import se233.snakegame.controller.GameLoop;
import se233.snakegame.model.Food;
import se233.snakegame.model.Snake;
import se233.snakegame.view.Platform;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static junit.framework.TestCase.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;
    private Method update;
    private Method collision;
    private Method redraw;
    @Before
    public void init() throws NoSuchMethodException{
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food(new Point2D(0, 0)),new Food(new Point2D(1, 1)));
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw= GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);

    }
    @Test
    public void clockTickHelper() throws InvocationTargetException,IllegalAccessException {
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);
    }
    @Test
    public void testClockTick() throws InvocationTargetException,IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food(new Point2D(0, 0)),new Food(new Point2D(1, 1)));
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
    }
    @Test
    public void testNoBack() throws InvocationTargetException,IllegalAccessException{
        gameLoopUnderTest = new GameLoop(new Platform(),new Snake(new Point2D(0,0)),new Food(new Point2D(0, 0)),new Food(new Point2D(1, 1)));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,1));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,2));
        gameLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(),new Point2D(0,3));
    }


}
