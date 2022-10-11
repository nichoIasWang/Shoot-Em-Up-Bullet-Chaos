import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class Ship extends Actor{
    private long bulletDelay;
    private long laserDelay = (long)1e9;
    private BulletTimer bulletDelayBulletTimer;
    private LaserTimer laserDelayTimer;
    private int laserCount = 3;
    private boolean dead = false;

    public Ship(long bulletDelay){
        setImage(new Image(getClass().getClassLoader().getResource("resources/ship.png").toString()));
        setFitHeight(50);
        setFitWidth(50);
        this.bulletDelay = bulletDelay;
        bulletDelayBulletTimer = new BulletTimer();
        laserDelayTimer = new LaserTimer();
    }

    public void startBulletTimer(){
        bulletDelayBulletTimer.start();
    }

    public void stopBulletTimer(){
        bulletDelayBulletTimer.stop();
    }

    public void startLaserTimer(){
        laserDelayTimer.start();
    }

    public void stopLaserTimer(){
        laserDelayTimer.stop();
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
                PlayerBullet bullet1 = new PlayerBullet(getX(), getY() - getFitHeight()/2, 0, 20, new Image(getClass().getClassLoader().getResource("resources/bullet.png").toString()), getFitHeight() * 2, getFitWidth(), false);
                PlayerBullet bullet2 = new PlayerBullet(getX(), getY() - getFitHeight()/2 - 25, 0, 20, new Image(getClass().getClassLoader().getResource("resources/bullet.png").toString()), getFitHeight() * 2, getFitWidth(), false);
                PlayerBullet bullet3 = new PlayerBullet(getX(), getY() - getFitHeight()/2 - 50,0, 20, new Image(getClass().getClassLoader().getResource("resources/bullet.png").toString()), getFitHeight() * 2, getFitWidth(), false);
                getWorld().getChildren().addAll(bullet1, bullet2, bullet3);
            }
        }
    }

    public boolean isDead() {
        return dead;
    }

    public void setLaserCount(int laserCount) {
        this.laserCount = laserCount;
    }

    public int getLaserCount(){
        return laserCount;
    }

    private class LaserTimer extends AnimationTimer{
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
            if(laserCount > 0){
                if(now - previous >= laserDelay){
                    previous = now;
                    laserCount--;
                    PlayerLaser laser = new PlayerLaser(getX() - getFitWidth()/2, getY() + getFitHeight() - getWorld().getHeight() * 2, 0, 0, new Image(getClass().getClassLoader().getResource("resources/laser.png").toString()), getWorld().getHeight() * 2,  getFitWidth() * 2, false);
                    getWorld().getChildren().add(laser);
                    laser.startRemoveTimer();
                }
            }
        }
    }

    @Override
    public void act(long now) {
        if (getOneIntersectingObject(Bullet.class) != null && getOneIntersectingObject(Bullet.class).isEnemyBullet()){
            if (laserCount > 0) {
                PlayerLaser laser = new PlayerLaser(getX(), getY() + getFitHeight() - getWorld().getHeight() * 2, 0, 0, new Image(getClass().getClassLoader().getResource("resources/laser.png").toString()), getWorld().getHeight() * 2, getFitWidth() * 2, false);
                getWorld().getChildren().add(laser);
                laser.startRemoveTimer();
                laserCount--;
            } else {
                dead = true;
            }
            getWorld().remove(getOneIntersectingObject(Bullet.class));
        }
        else if (getOneIntersectingObject(HomingEnemy.class) != null){
            if (laserCount > 0) {
                PlayerLaser laser = new PlayerLaser(getX(), getY() + getFitHeight() - getWorld().getHeight() * 2, 0, 0, new Image(getClass().getClassLoader().getResource("resources/laser.png").toString()), getWorld().getHeight() * 2, getFitWidth() * 2, false);
                getWorld().getChildren().add(laser);
                laser.startRemoveTimer();
                laserCount--;
                getOneIntersectingObject(HomingEnemy.class).remove();
            } else {
                getWorld().remove(getOneIntersectingObject(HomingEnemy.class));
                dead = true;
            }
        }
        else if (getOneIntersectingObject(MissileEnemy.class) != null){
            if (laserCount > 0) {
                PlayerLaser laser = new PlayerLaser(getX(), getY() + getFitHeight() - getWorld().getHeight() * 2, 0, 0, new Image(getClass().getClassLoader().getResource("resources/laser.png").toString()), getWorld().getHeight() * 2, getFitWidth() * 2, false);
                getWorld().getChildren().add(laser);
                laser.startRemoveTimer();
                laserCount--;
                getOneIntersectingObject(MissileEnemy.class).remove();
            } else {
                getWorld().remove(getOneIntersectingObject(MissileEnemy.class));
                dead = true;
            }
        }
    }
}
