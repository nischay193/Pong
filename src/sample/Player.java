package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Player {
    private Rectangle rectangle;
    private Point2D movement;
    private double speed;
private double mul;
    public Player(Rectangle rectangle, int pos,double speed,double mul) {
        this.mul=mul; // frenzy multiplier
        this.speed=speed; // speed with which paddle moves up or down
        this.rectangle = rectangle;
        this.movement = new Point2D(this.speed, this.speed); // speed in x,y directions
        this.rectangle.setTranslateX(pos);
        this.rectangle.setTranslateY((double)Main.HEIGHT / 2 - this.rectangle.getHeight() / 2);
        this.rectangle.setFill(Color.WHITE);
    }

    public Rectangle getCharacter() {
        return this.rectangle;
    }

    //moves paddle up
    public void moveUp() {

        //if paddle is at the top, don't move further upwards
        if (this.rectangle.getTranslateY()<0) {
            return;
        }
        this.rectangle.setTranslateY(this.rectangle.getTranslateY() - this.movement.getY());
    }

    //moves paddle down
    public void moveDown() {

        // if paddle is at the bottom, don't move further downwards
        if (this.rectangle.getTranslateY() + this.rectangle.getHeight()>Main.HEIGHT) {
            return;
        }
        this.rectangle.setTranslateY(this.rectangle.getTranslateY() + this.movement.getY());
    }
    void setMovement(double x, double y) {
        this.movement = new Point2D(x, y);
    }

    //reset position of paddles on the screen
    public void reset(){
        this.rectangle.setTranslateY((double)Main.HEIGHT / 2 - this.rectangle.getHeight() / 2);
        this.movement=new Point2D(this.speed,this.speed);
    }

    public void incSpeed(){
        this.setMovement(this.movement.getX()*this.mul,this.movement.getY()*this.mul);
    }
}
