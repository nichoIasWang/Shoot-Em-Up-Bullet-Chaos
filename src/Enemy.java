import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class Enemy extends Actor implements Movable{
    protected long bulletDelay;
    protected Image image;
    private double shipXCoor, shipYCoor;
    protected int health;
    protected boolean firstShot = true;
    protected double dx, dy;

    public Enemy(double width, double height, long bulletDelay, double dx, double dy){
        setFitHeight(width);
        setFitWidth(height);
        this.bulletDelay = bulletDelay;
        this.dx = dx;
        this.dy = dy;
    }

    public abstract void startBulletTimer();

    public abstract void stopBulletTimer();

    public double getShipXCoor() {
        return shipXCoor;
    }

    public void setShipXCoor(double shipXCoor) {
        this.shipXCoor = shipXCoor;
    }

    public double getShipYCoor() {
        return shipYCoor;
    }

    public void setShipYCoor(double shipYCoor) {
        this.shipYCoor = shipYCoor;
    }

    public void removeHealth(){
        health -= 150;
    }

    @Override
    public void moveLeft(){
        setX(getX() - dx);
    }

    @Override
    public void moveRight(){
        setX(getX() + dx);
    }

    @Override
    public void moveDown(){
        setY(getY() + dy);
    }

    @Override
    public void moveUp(){
        setY(getY() - dy);
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public abstract void remove();
}
