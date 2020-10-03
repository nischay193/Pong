package sample;


import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Random;

public class Ball {

    private Rectangle square;
    private Point2D movement;
    private Boolean turn;
    private double speed;
    private  double mul;

    public Ball(Boolean turn, double speed,double mul) {
        this.mul=mul; // frenzy multiplier
        this.speed = speed; // speed with which ball moves
        Random random = new Random();
        this.turn = turn;
        this.square = new Rectangle(0, 0, 10, 10);
        int r = random.nextInt(2);
        r = (int) Math.pow(-1, r);
        // position of x depends on turn
        if (this.turn) {
            this.movement = new Point2D(-this.speed, r * this.speed);
            this.square.setTranslateX((double)Main.WIDTH / 2 - this.square.getWidth());
        } else {
            this.movement = new Point2D(this.speed, r * this.speed);
            this.square.setTranslateX((double)Main.WIDTH / 2);
        }
        this.square.setFill(Color.WHITE);
        int n = random.nextInt(Main.HEIGHT - (int) this.square.getHeight() + 1);
        this.square.setTranslateY(n); // position of y is random each time
    }

    public Rectangle getCharacter() {
        return this.square;
    }

    void setMovement(double x, double y) {
        this.movement = new Point2D(x, y);
    }

    Point2D getMovement() {
        return this.movement;
    }

    // moves the ball
    public void move() {
        if (this.square.getTranslateY() + this.square.getHeight() +this.movement.getY() > Main.HEIGHT || this.square.getTranslateY() +this.movement.getY() < 0) {
            setMovement((int) this.movement.getX(), (int) -this.movement.getY());
        }
        this.square.setTranslateY(this.square.getTranslateY() + this.movement.getY());
        this.square.setTranslateX(this.square.getTranslateX() + this.movement.getX());
    }
    //checking if ball is in bounds of the window
    public int outOfBounds() {
        if (this.square.getTranslateX() < -this.square.getWidth()) {
            return 1;
        }else if(this.square.getTranslateX() > Main.WIDTH + this.square.getWidth()){
            return -1;
        }
        return 0;
    }

    public boolean collide(Player player) {
        Shape collisionArea = Shape.intersect(this.square, player.getCharacter());
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }

    // reset postition of ball, changing the turn
    public void reset() {
        this.turn = !this.turn;
        Random random = new Random();
        int r = random.nextInt(2);
        r = (int) Math.pow(-1, r);

        // position of x depends on turn
        if (this.turn) {
            this.movement = new Point2D(-this.speed, r * this.speed);
            this.square.setTranslateX((double)Main.WIDTH / 2 - this.square.getWidth());
        } else {
            this.movement = new Point2D(this.speed, r * this.speed);
            this.square.setTranslateX((double)Main.WIDTH / 2);
        }
        int n = random.nextInt(Main.HEIGHT - (int) this.square.getHeight() + 1);
        this.square.setTranslateY(n);
    }
    public void incSpeed(){
        this.setMovement(this.movement.getX()*this.mul,this.movement.getY()*this.mul);
    }
}
