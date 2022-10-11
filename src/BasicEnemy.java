import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class BasicEnemy extends Enemy{
    private BulletTimer bulletDelayTimer;
    private boolean canKill = true;
    private Image altBulletImage = new Image(getClass().getClassLoader().getResource("resources/red bullet.png").toString());
    private boolean useAltImage = false;

    public BasicEnemy(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/basic enemy.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 600;
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(2));
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setNode(this);
        rt.play();
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setUseAltImage(boolean b){
        useAltImage = b;
    }

    @Override
    public void startBulletTimer() {
        bulletDelayTimer.start();
    }

    @Override
    public void stopBulletTimer() {
        bulletDelayTimer.stop();
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
                if(firstShot){
                    firstShot = false;
                    previous = now;
                    return;
                }
                previous = now;
                if(useAltImage){
                    BasicEnemyBullet basicEnemyBullet = new BasicEnemyBullet(getX() + getFitWidth()/4, getY() + getFitHeight() + getFitHeight()/10, 3 * (getShipXCoor() - getX())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))), 3 * (getShipYCoor() - getY())/(Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))), altBulletImage, getFitHeight(), getFitWidth()/2, true);
                    getWorld().getChildren().add(basicEnemyBullet);
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
                            angle = -1 * angle;
                        }
                        else if(getShipYCoor() - getY() > 0 && getShipXCoor() - getX() > 0)
                            angle = -1 * angle;
                    }
                    rt.setByAngle(angle);
                    rt.setInterpolator(Interpolator.LINEAR);
                    rt.setCycleCount(1);
                    rt.setNode(basicEnemyBullet);
                    rt.play();
                }
                else {
                    BasicEnemyBullet basicEnemyBullet = new BasicEnemyBullet(getX() + getFitWidth() / 4, getY() + getFitHeight() + getFitHeight() / 10, 3 * (getShipXCoor() - getX()) / (Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))), 3 * (getShipYCoor() - getY()) / (Math.sqrt(Math.pow(getX() - getShipXCoor(), 2) + Math.pow(getY() - getShipYCoor(), 2))), new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight() / 4, getFitWidth() / 4, true);
                    getWorld().getChildren().add(basicEnemyBullet);
                }
            }
        }
    }

    public void remove(){
        if(getWorld() instanceof Level1) {
            ((Level1) getWorld()).getScore().setValue(((Level1) getWorld()).getScore().getValue() + 50);
            ((Level1) getWorld()).addEnemiesDestroyed();
        } else if(getWorld() instanceof Level2) {
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 50);
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
        if(health <= 0 && canKill) {
            remove();
        }
    }

    public void setCanKill(boolean canKill) {
        this.canKill = canKill;
    }
}
