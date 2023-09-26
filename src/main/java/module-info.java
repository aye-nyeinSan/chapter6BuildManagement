module se233.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;


    opens se233.snakegame to javafx.fxml;
    exports se233.snakegame;
}