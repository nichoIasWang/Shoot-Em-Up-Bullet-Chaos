import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class PlayerLaser extends Bullet {
    private RemoveTimer removeTimer;
    private LaserHitDelayTimer laserHitDelayTimer;

    public PlayerLaser(double xCoor, double yCoor, double dx, double dy, Image image, double height, double width, boolean enemyBullet) {
        super(xCoor, yCoor, dx, dy, image, height, width, enemyBullet);
        removeTimer = new RemoveTimer();
        laserHitDelayTimer = new LaserHitDelayTimer();
        laserHitDelayTimer.start();
    }

    @Override
    public void act(long now) {
        for(Bullet b : getIntersectingObjects(Bullet.class)){
            if(b.isEnemyBullet())
                getWorld().remove(b);
        }
    }

    public void startRemoveTimer(){
        removeTimer.start();
    }

    private void removeLaser(){
        laserHitDelayTimer.stop();
        removeTimer.stop();
        getWorld().remove(this);
    }

    private class RemoveTimer extends AnimationTimer {
        private long previousTime = 0;
        private boolean secondTime = false;
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
            if(now - previousTime > (long)(1e9)){
                previousTime = now;
                if(secondTime) {
                    stop();
                    try{
                        removeLaser();
                    } catch(NullPointerException e){
                        // sometimes if a world is reset before the laser can remove itself, the laser timer tries to remove itself even though it's already removed before being stopped
                        // this doesn't interfere or cause an error with how the game works in any way
                        e.printStackTrace();
                    }
                }
                else
                    secondTime = true;
            }
        }
    }

    private class LaserHitDelayTimer extends AnimationTimer{
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
            if(now - previous > (long)(2.5e8)){
                previous = now;
                for(Enemy e : getIntersectingObjects(Enemy.class)){
                    e.removeHealth();
                }
            }
        }
    }
}
