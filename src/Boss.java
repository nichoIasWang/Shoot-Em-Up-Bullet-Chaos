import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;

import static java.lang.Math.cos;

public class Boss extends Enemy {
    private int time = 0;
    private BulletTimer bulletDelayTimer;

    public Boss(double width, double height, long bulletDelay, double dx, double dy) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/boss.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 30000;
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
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 1000);
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
            time++;
            BasicEnemyBullet basicEnemyBullet1 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet2 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, 0.485, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet3 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet4 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, 1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet5 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet6 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet7 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet8 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet9 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet10 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet11 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet12 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet13 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet14 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet15 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet16 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet17 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet18 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, -1.94, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet19 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, -2, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet20 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.0485, -1.94, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet21 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet22 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet23 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet24 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet25 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet26 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.985, 0.248, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet27 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.875, 0.695, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet28 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.627, 1.162, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet29 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, 1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet30 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.162, 1.627, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet31 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.695, 1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet32 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.248, 1.985, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet33 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.695, 1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet34 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet35 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.248, 1.985, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet36 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.695, 1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet37 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.162, 1.627, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet38 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, 1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet39 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.627, 1.162, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet40 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.875, 0.695, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet41 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.985, 0.248, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet42 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet43 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.985, -0.248, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet44 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.875, -0.695, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet45 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.627, -1.162, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet46 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet47 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.162, -1.627, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet48 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.695, -1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet49 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.248, -1.985, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet50 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.695, -1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet51 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.248, -1.985, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet52 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.695, -1.875, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet53 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.162, -1.627, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet54 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet55 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.627, -1.162, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet56 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.875, -0.695, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            BasicEnemyBullet basicEnemyBullet57 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.985, -0.248, new Image(getClass().getClassLoader().getResource("resources/boss bullet.png").toString()), getFitHeight()/12, getFitWidth()/12, true);
            if(time > 300)
                time = 0;
            if(time == 1 || time == 6 || time == 11 || time == 16 || time == 21 || time == 26 || time == 31 || time == 36 || time == 41 || time == 46 || time == 51 || time == 56 || time == 61 || time == 66 || time == 71){
                getWorld().getChildren().addAll(basicEnemyBullet1, basicEnemyBullet2, basicEnemyBullet3, basicEnemyBullet4, basicEnemyBullet5, basicEnemyBullet6, basicEnemyBullet7, basicEnemyBullet8, basicEnemyBullet9, basicEnemyBullet10, basicEnemyBullet11, basicEnemyBullet12, basicEnemyBullet13, basicEnemyBullet14, basicEnemyBullet15, basicEnemyBullet16, basicEnemyBullet17, basicEnemyBullet18, basicEnemyBullet19, basicEnemyBullet20, basicEnemyBullet21, basicEnemyBullet22, basicEnemyBullet23, basicEnemyBullet24);
            } else if(time == 150 || time == 155 || time == 160 || time == 165 || time == 170 || time == 175 || time == 180 || time == 185 || time == 190 || time == 195 || time == 200 || time == 205 || time == 210 || time == 215 || time == 220){
                getWorld().getChildren().addAll(basicEnemyBullet25, basicEnemyBullet26, basicEnemyBullet27, basicEnemyBullet28, basicEnemyBullet29, basicEnemyBullet30, basicEnemyBullet31, basicEnemyBullet32, basicEnemyBullet33, basicEnemyBullet34, basicEnemyBullet35, basicEnemyBullet36, basicEnemyBullet37, basicEnemyBullet38, basicEnemyBullet39, basicEnemyBullet40, basicEnemyBullet41, basicEnemyBullet42, basicEnemyBullet43, basicEnemyBullet44, basicEnemyBullet45, basicEnemyBullet46, basicEnemyBullet47, basicEnemyBullet48, basicEnemyBullet49, basicEnemyBullet50, basicEnemyBullet51, basicEnemyBullet52, basicEnemyBullet53, basicEnemyBullet54, basicEnemyBullet55, basicEnemyBullet56, basicEnemyBullet57);
            }
        }
    }
}