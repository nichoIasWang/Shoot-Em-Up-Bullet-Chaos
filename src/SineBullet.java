import javafx.scene.image.Image;
import static java.lang.Math.sin;

public class SineBullet extends Bullet{
    private double startXCoor, startYCoor;
    private int time = 0;

    public SineBullet(double xCoor, double yCoor, double dx, double dy, Image image, double height, double width, boolean enemyBullet) {
        super(xCoor, yCoor, dx, dy, image, height, width, enemyBullet);
        startXCoor = xCoor;
        startYCoor = yCoor;
    }

    public double funcX(double y){
        return 100 * sin(y/40);
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
        setX(funcX(time) + startXCoor);
        setY(time + startYCoor);
        time += 2;
    }
}
