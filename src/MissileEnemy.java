import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MissileEnemy extends Enemy {
    private BulletTimer bulletDelayTimer;

    public MissileEnemy(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/homing ship.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 50;
    }

    @Override
    public void startBulletTimer() {
        bulletDelayTimer.start();
    }

    @Override
    public void stopBulletTimer() {
        bulletDelayTimer.stop();
    }

    public void turn(){
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(0.1));
        double angle;
        if(getShipXCoor() == getX()){
            angle = 0;
        } else{
            angle = (180/Math.PI) * Math.atan((((getShipXCoor() - getX())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2)))))/((getShipYCoor() - getY())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2)))));
            if(getShipYCoor() - getY() < 0 && getShipXCoor() - getX() < 0)
                angle = -1 * angle + 180;
            else if(getShipYCoor() - getY() < 0 && getShipXCoor() - getX() > 0)
                angle = -1 * angle - 180;
            else if(getShipYCoor() - getY() > 0 && getShipXCoor() - getX() < 0) {
                System.out.println(angle);
                angle = -1 * angle;
            }
            else if(getShipYCoor() - getY() > 0 && getShipXCoor() - getX() > 0)
                angle = -1 * angle;
        }
        rt.setByAngle(angle);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(1);
        rt.setNode(this);
        rt.play();
    }

    @Override
    public void remove() {
        if(getWorld() instanceof Level2) {
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

    private class BulletTimer extends AnimationTimer {
        private long previous = 0;
        private boolean first = true;
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
                if(first){
                    first = false;
                    previous = now;
                    setDx(7 * (getShipXCoor() - getX())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))));
                    setDy(7 * (getShipYCoor() - getY())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))));
                    turn();
                    bulletDelay = (long)1e7;
                    return;
                }
                previous = now;
                moveRight();
                moveDown();
            }
        }
    }
}