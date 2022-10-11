import javafx.scene.image.Image;

public abstract class Bullet extends Actor implements Movable {
    private double dx, dy, height, width;
    private boolean enemyBullet;

    public Bullet(double xCoor, double yCoor, double dx, double dy, Image image, double height, double width, boolean enemyBullet){
        setX(xCoor);
        setY(yCoor);
        this.dx = dx;
        this.dy = dy;
        this.height = height;
        this.width = width;
        setImage(image);
        setFitHeight(height);
        setFitWidth(width);
        this.enemyBullet = enemyBullet;
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


    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public boolean isEnemyBullet() {
        return enemyBullet;
    }
}
