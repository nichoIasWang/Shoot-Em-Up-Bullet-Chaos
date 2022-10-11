import javafx.scene.image.Image;

public class BasicEnemyBullet extends Bullet {
    public BasicEnemyBullet(double xCoor, double yCoor, double dx, double dy, Image image, double height, double width, boolean enemyBullet) {
        super(xCoor, yCoor, dx, dy, image, height, width, enemyBullet);
    }

    @Override
    public void act(long now) {
        try {
            if (getX() < 0 || getX() > getWorld().getWidth() || getY() < 0 || getY() > getWorld().getHeight()) {
                getWorld().remove(this);
            }
        } catch(NullPointerException e){
            e.printStackTrace();
        }
        setX(getX() + getDx());
        setY(getY() + getDy());
    }
}
