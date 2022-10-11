import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

public class SmallSpiralEnemy extends Enemy {
    private BulletTimer bulletDelayTimer;

    public SmallSpiralEnemy(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/ship enemy.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 1500;
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
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 125);
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
            if(now - previous >= bulletDelay){
                previous = now;
                CosineBullet cosineBullet = new CosineBullet(getX() + getFitWidth()/2, getY() + getFitHeight(), 0, 0, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/4, getFitWidth()/4, true);
                SineBullet sineBullet = new SineBullet(getX() + getFitWidth()/2, getY() + getFitHeight(), 0, 0, new Image(getClass().getClassLoader().getResource("resources/basic enemy bullet.png").toString()), getFitHeight()/4, getFitWidth()/4, true);
                getWorld().getChildren().addAll(cosineBullet, sineBullet);
            }
        }
    }
}