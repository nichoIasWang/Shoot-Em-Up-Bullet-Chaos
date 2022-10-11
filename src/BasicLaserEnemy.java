import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class BasicLaserEnemy extends Enemy {
    private BulletTimer bulletDelayTimer;

    public BasicLaserEnemy(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/laser enemy.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 1800;
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
    }

    private class BulletTimer extends AnimationTimer{
        private long previous = 0;
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
            if(now - previous >= bulletDelay) {
                previous = now;
                BasicEnemyLaser basicEnemyLaser1 = new BasicEnemyLaser(getX() + getFitWidth()/2, getY() + getFitHeight(), 0, 3, new Image(getClass().getClassLoader().getResource("resources/basic enemy laser.png").toString()), getFitHeight()/2, getFitWidth()/8, true);
                BasicEnemyLaser basicEnemyLaser2 = new BasicEnemyLaser(getX() + getFitWidth()/2, getY() + getFitHeight(), -3, 3, new Image(getClass().getClassLoader().getResource("resources/basic enemy laser.png").toString()), getFitHeight()/2, getFitWidth()/8, true);
                BasicEnemyLaser basicEnemyLaser3 = new BasicEnemyLaser(getX() + getFitWidth()/2, getY() + getFitHeight(), 3, 3, new Image(getClass().getClassLoader().getResource("resources/basic enemy laser.png").toString()), getFitHeight()/2, getFitWidth()/8, true);
                BasicEnemyBullet basicEnemyBullet1 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet2 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.857, 0.743, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet3 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.4, 1.4, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet4 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.743, 1.857, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet5 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet6 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.743, 1.857, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet7 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.4, 1.4, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet8 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.857, 0.743, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet9 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet10 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.857, -0.743, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet11 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.4, -1.4, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet12 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.743, -1.857, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet13 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, -2, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet14 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.743, -1.857, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet15 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.4, -1.4, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                BasicEnemyBullet basicEnemyBullet16 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.857, -0.743, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/6, getFitWidth()/6, true);
                getWorld().getChildren().addAll(basicEnemyBullet1, basicEnemyBullet2, basicEnemyBullet3, basicEnemyBullet4, basicEnemyBullet5, basicEnemyBullet6, basicEnemyBullet7, basicEnemyBullet8, basicEnemyBullet9, basicEnemyBullet10, basicEnemyBullet11, basicEnemyBullet12, basicEnemyBullet13, basicEnemyBullet14, basicEnemyBullet15, basicEnemyBullet16);
                getWorld().getChildren().addAll(basicEnemyLaser1, basicEnemyLaser2, basicEnemyLaser3);
            }
        }
    }
}
