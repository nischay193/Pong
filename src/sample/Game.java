package sample;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Game extends Application {
    private double paddleSpeed = 5, ballSpeed = 5;
    private Boolean frenzy = false;
    private String player1, player2;
    private int firstTo;
    private double frenzyMul;
    private int one=0,two=0;

    public void start(Stage window) {
//----------------------------------------------------------------------------------------------------------------------
        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();
        Label l6 = new Label();

        l1.setFont(new Font("Arial", 20));
        l2.setFont(new Font("Arial", 20));
        l3.setFont(new Font("Arial", 20));
        l4.setFont(new Font("Arial", 20));
        l5.setFont(new Font("Arial", 20));
        l6.setFont(new Font("Arial", 20));

        l1.setText("Enter Player 1 Name");
        l2.setText("Enter Player 2 Name");
        l3.setText("Enter Paddle Speed");
        l4.setText("Enter Ball Speed");
        l5.setText("First to?");
        l6.setText("Frenzy Mode Multiplier");

//----------------------------------------------------------------------------------------------------------------------
        TextField t1 = new TextField("Player 1");
        TextField t2 = new TextField("Player 2");
        TextField t3 = new TextField("10");
        TextField t4 = new TextField("7");
        TextField t5 = new TextField("7");
        TextField t6 = new TextField("1.05");

        t1.setFont(new Font("Arial", 20));
        t2.setFont(new Font("Arial", 20));
        t3.setFont(new Font("Arial", 20));
        t4.setFont(new Font("Arial", 20));
        t5.setFont(new Font("Arial", 20));
        t6.setFont(new Font("Arial", 20));
//----------------------------------------------------------------------------------------------------------------------
        l6.setVisible(false);
        t6.setVisible(false);

        Label errorMessage = new Label();
        errorMessage.setFont(new Font("Arial", 15));
//----------------------------------------------------------------------------------------------------------------------
        HBox h1 = new HBox();
        HBox h2 = new HBox();
        HBox h3 = new HBox();
        HBox h4 = new HBox();
        HBox h5 = new HBox();
        HBox h6 = new HBox();

        h1.getChildren().addAll(l1, t1);
        h2.getChildren().addAll(l2, t2);
        h3.getChildren().addAll(l3, t3);
        h4.getChildren().addAll(l4, t4);
        h5.getChildren().addAll(l5, t5);
        h6.getChildren().addAll(l6, t6);

        h1.setSpacing(10);
        h2.setSpacing(10);
        h3.setSpacing(10);
        h4.setSpacing(10);
        h5.setSpacing(10);
        h6.setSpacing(10);
//----------------------------------------------------------------------------------------------------------------------

        CheckBox checkBox = new CheckBox("Frenzy Mode");
        checkBox.setFont(new Font("Arial", 20));

//----------------------------------------------------------------------------------------------------------------------
        Button start = new Button("Start Game");
        start.setFont(new Font("Arial", 20));
//----------------------------------------------------------------------------------------------------------------------
       //checkbox event handler
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (checkBox.isSelected()) {
                    frenzy = true;
                    l6.setVisible(true);
                    t6.setVisible(true);
                } else {
                    l6.setVisible(false);
                    t6.setVisible(false);
                    frenzy = false;
                }
            }

        };
        checkBox.setOnAction(event);
//----------------------------------------------------------------------------------------------------------------------
        VBox vBox1 = new VBox();
        vBox1.setSpacing(20);
        vBox1.getChildren().addAll(h1, h2, h3, h4, h5, checkBox, h6, errorMessage, start);
        vBox1.setPadding(new Insets(50, 50, 50, 50));
//----------------------------------------------------------------------------------------------------------------------
        VBox vBox2 = new VBox();
        Label winner = new Label();
        winner.setFont(new Font("Arial", 120));
        Label score = new Label();
        score.setFont(new Font("Arial", 120));
        vBox2.getChildren().addAll(winner, score);
        vBox2.setPadding(new Insets(50, 50, 50, 50));
        Scene finalScreen = new Scene(vBox2);
//----------------------------------------------------------------------------------------------------------------------

        //start button event handler
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    player1 = t1.getText();
                    player2 = t2.getText();
                    if (player1.isEmpty() || player2.isEmpty()) {
                        throw new Exception();
                    }
                    errorMessage.setText(""); // if no error, clear text field
                    paddleSpeed = Double.parseDouble(t3.getText());
                    ballSpeed = Double.parseDouble(t4.getText());
                    firstTo = Integer.parseInt(t5.getText());
                    frenzyMul = Double.parseDouble(t6.getText());

                } catch (Exception e) {
                    errorMessage.setText("Enter correct details"); // text when any error occurs
                }
                if (errorMessage.getText().isEmpty()) {



                    FontLoader fontLoader = Toolkit.getToolkit().getFontLoader(); // to get width of font

                    Label score1=new Label("0");
                    Label score2=new Label("0");

                    score1.setFont(new Font("Impact",80));
                    score2.setFont(new Font("Impact",80));

                    //making scores transparent
                    score1.setOpacity(0.5);
                    score2.setOpacity(0.5);

                    score2.setTranslateX(Main.WIDTH-fontLoader.computeStringWidth(score2.getText(), score2.getFont()));

                    //setting text color as white
                    score1.setTextFill(Color.WHITE);
                    score2.setTextFill(Color.WHITE);

                    // 2 players
                    Player p1 = new Player(new Rectangle(0, 0, 20, 100), 0, paddleSpeed, frenzyMul);
                    Player p2 = new Player(new Rectangle(0, 0, 20, 100), Main.WIDTH - 20, paddleSpeed, frenzyMul);

                    // center line
                    Rectangle line = new Rectangle(0, 0, 0, Main.HEIGHT);
                    line.setStrokeWidth(2);
                    line.setTranslateX((double)Main.WIDTH / 2);
                    line.setStroke(Color.WHITE);
                    line.setStrokeWidth(2.0);
                    line.getStrokeDashArray().addAll(5.0, 15.0, 5.0, 15.0);

                    // creating ball
                    Ball ball = new Ball(true, ballSpeed, frenzyMul);

                    //Group which contains game elements
                    Group group = new Group();
                    group.getChildren().addAll(score1,score2,p1.getCharacter(), p2.getCharacter(), line, ball.getCharacter());
                    Scene gameScreen = new Scene(group, Main.WIDTH, Main.HEIGHT, Color.BLACK);

                    // HashMap to see which keys are currently being pressed
                    Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

                    gameScreen.setOnKeyPressed(e -> {
                        pressedKeys.put(e.getCode(), true);
                    });

                    gameScreen.setOnKeyReleased(e -> {
                        pressedKeys.put(e.getCode(), false);
                    });


                    window.setScene(gameScreen);
                    new AnimationTimer() {
                        Boolean flag = true;
                        long cur;

                        public void handle(long now) {

                            //checking if any player has won the match
                            if(one==firstTo){
                                winner.setText("Winner: "+player1);
                                score.setText("Score: "+one+"-"+two);
                                window.setScene(finalScreen);
                                this.stop();
                            }else if(two==firstTo){
                                winner.setText("Winner: "+player2);
                                score.setText("Score: "+one+"-"+two);
                                window.setScene(finalScreen);
                                this.stop();
                            }
                            // to ensure delay after ball goes out of bounds
                            if (!flag) {
                                if (now - cur >= 600000000) {
                                    flag = true;
                                    group.getChildren().add(ball.getCharacter());
                                }
                                return;
                            }
                            // input based on key pressed
                            if (pressedKeys.getOrDefault(KeyCode.UP, false)) {
                                p2.moveUp();
                            }
                            if (pressedKeys.getOrDefault(KeyCode.DOWN, false)) {
                                p2.moveDown();
                            }

                            if (pressedKeys.getOrDefault(KeyCode.W, false)) {
                                p1.moveUp();
                            }
                            if (pressedKeys.getOrDefault(KeyCode.S, false)) {
                                p1.moveDown();
                            }
                            //checking if ball is out of bounds
                            if (ball.outOfBounds()!=0) {
                                if(ball.outOfBounds()==1){
                                    two++;
                                }else if(ball.outOfBounds()==-1){
                                    one++;
                                }
                                score1.setText(String.valueOf(one));
                                score2.setText(String.valueOf(two));
                                cur = now;
                                flag = false;

                                // reset ball and player positions after each round
                                ball.reset();
                                p1.reset();
                                p2.reset();

                                //remove ball from group
                                group.getChildren().remove(ball.getCharacter());
                            }

                            //checking if ball collides with either paddle
                            if (ball.collide(p1) || ball.collide(p2)) {

                                // reverse x direction of ball when it collides with either paddle
                                ball.setMovement(-(int) ball.getMovement().getX(), (int) ball.getMovement().getY());

                                // frenzy mode modifiers
                                if (frenzy) {
                                    ball.incSpeed();
                                    p1.incSpeed();
                                    p2.incSpeed();
                                }
                            }
                            ball.move();
                        }
                    }.start();
                }
            }
        });
//----------------------------------------------------------------------------------------------------------------------
        Scene startScreen = new Scene(vBox1);
        window.setScene(startScreen);
        window.setTitle("Pong");
        window.setResizable(false);
        window.show();


//
    }
}
