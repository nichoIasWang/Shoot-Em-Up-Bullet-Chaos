import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class HomingEnemy extends Enemy {
    private BulletTimer bulletDelayTimer;

    public HomingEnemy(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/basic enemy.png").toString());
        setImage(image);
        health = 50;
        bulletDelayTimer = new BulletTimer();
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(1));
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

    private class BulletTimer extends AnimationTimer {
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
                setDx(5 * (getShipXCoor() - getX())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))));
                setDy(5 * (getShipYCoor() - getY())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))));
                moveRight();
                moveDown();
            }
        }
    }

    public void remove(){
        if(getWorld() instanceof Level1) {
            ((Level1) getWorld()).getScore().setValue(((Level1) getWorld()).getScore().getValue() + 25);
            ((Level1) getWorld()).addEnemiesDestroyed();
        } else if(getWorld() instanceof Level2) {
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 25);
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
}
