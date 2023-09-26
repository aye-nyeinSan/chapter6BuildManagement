package se233.snakegame;

import se233.snakegame.controller.GameLoop;
import se233.snakegame.model.Food;
import se233.snakegame.model.Snake;
import se233.snakegame.view.Platform;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        Launcher.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(platform.WIDTH / 2, platform.HEIGHT / 2));
        Food food1 = new Food(Color.RED);
        Food food2 = new Food(Color.GREEN);


        GameLoop gameLoop = new GameLoop(platform, snake, food1,food2);
        Scene scene = new Scene(platform, platform.WIDTH * platform.TILE_SIZE, platform.HEIGHT * platform.TILE_SIZE);
        scene.setOnKeyPressed(event -> platform.setKey(event.getCode()));

        scene.setOnKeyReleased(event -> platform.setKey(null));
        this.stage.setTitle("Snake");
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
        (new Thread(gameLoop)).start();
    }


}