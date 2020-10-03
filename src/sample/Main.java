package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Game;

import static javafx.application.Application.launch;

public class Main {

    static int WIDTH = 1200, HEIGHT = 600;

    public static void main(String[] args) {
        Application.launch(Game.class);
    }
}
