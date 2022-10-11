import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static java.lang.Math.cos;

public class LeftMovingEnemy extends Enemy{
    private BulletTimer bulletDelayTimer;
    private double startXCoor, startYCoor;
    private int time = 0;

    public LeftMovingEnemy(double width, double height, long bulletDelay, double dx, double dy, double xCoor, double yCoor) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/laser enemy.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 900;
        setX(xCoor);
        setY(yCoor);
        startXCoor = xCoor;
        startYCoor = yCoor;
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(2));
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setNode(this);
        rt.play();
    }

    @Override
    public void startBulletTimer() {
        bulletDelayTimer.start();
    }

    @Override
    public void stopBulletTimer() {
        bulletDelayTimer.stop();
    }

    @Override
    public void remove() {
        if(getWorld() instanceof Level2) {
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 100);
            ((Level2) getWorld()).addEnemiesDestroyed();
        }
        getWorld().remove(this);
        stopBulletTimer();
    }

    public double funcY(double x){
        return 100 * cos(x/50);
    }

    @Override
    public void act(long now) {
        for(Bullet b : getIntersectingObjects(Bullet.class)){
            if(!b.isEnemyBullet()){
                if(b instanceof PlayerBullet)
                    health -= 50;
                if(!(b instanceof PlayerLaser))
                    getWorld().remove(b);
            }
        }
        if(health <= 0) {
            remove();
        }
        setX(startXCoor - time);
        setY(funcY(time) + startYCoor);
        time += Math.PI;
        if(getX() < 0) {
            if(getWorld() instanceof Level2) {
                ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() - 100);
                ((Level2) getWorld()).removeEnemiesDestroyed();
            }
            remove();
        }
    }

    private class BulletTimer extends AnimationTimer {

        /**
         * This method needs to be overridden by extending classes. It is going to
         * be called in every frame while the {@code AnimationTimer} is active.
         *
         * @param now The timestamp of the current frame given in nanoseconds. This
         *            value will be the same for all {@code AnimationTimers} called
         *            during one frame.
         */
        @Override
        public void handle(long now) {
            if(funcY(time) > 99.9 || funcY(time) < -99.9){
                BasicEnemyBullet basicEnemyBullet1 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet2 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.857, 0.743, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet3 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.4, 1.4, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet4 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.743, 1.857, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet5 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet6 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.743, 1.857, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet7 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.4, 1.4, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet8 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.857, 0.743, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet9 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet10 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.857, -0.743, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet11 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.4, -1.4, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet12 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.743, -1.857, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet13 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, -2, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet14 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.743, -1.857, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet15 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.4, -1.4, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet16 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.857, -0.743, new Image(getClass().getClassLoader().getResource("resources/pink bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                getWorld().getChildren().addAll(basicEnemyBullet1, basicEnemyBullet2, basicEnemyBullet3, basicEnemyBullet4, basicEnemyBullet5, basicEnemyBullet6, basicEnemyBullet7, basicEnemyBullet8, basicEnemyBullet9, basicEnemyBullet10, basicEnemyBullet11, basicEnemyBullet12, basicEnemyBullet13, basicEnemyBullet14, basicEnemyBullet15, basicEnemyBullet16);
            }
        }
    }
}
