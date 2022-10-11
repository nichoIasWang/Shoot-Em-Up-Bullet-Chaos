import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class CircleEnemy extends Enemy {
    private BulletTimer bulletDelayTimer;
    private double startXCoor, startYCoor;
    private int time = 0;
    private boolean startLeft, goingLeft, goingRight;

    public CircleEnemy(double width, double height, long bulletDelay, double dx, double dy, double xCoor, double yCoor, boolean startLeft) {
        super(width, height, bulletDelay, dx, dy);
        image = new Image(getClass().getClassLoader().getResource("resources/circle.png").toString());
        setImage(image);
        bulletDelayTimer = new BulletTimer();
        health = 12000;
        startXCoor = xCoor;
        startYCoor = yCoor;
        this.startLeft = startLeft;
        if(startLeft){
            time = -195;
            goingLeft = true;
            goingRight = false;
        } else{
            goingRight = true;
            goingLeft = false;
            time = 195;
        }
        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(3));
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

    public double funcYPos(double x){
        return Math.sqrt(Math.pow(200, 2) - Math.pow(x, 2));
    }

    public double funcYNeg(double x){
        return -1 * Math.sqrt(Math.pow(200, 2) - Math.pow(x, 2));
    }

    @Override
    public void remove() {
        if(getWorld() instanceof Level2) {
            ((Level2) getWorld()).getScore().setValue(((Level2) getWorld()).getScore().getValue() + 500);
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
            if(startLeft){
                if(time >= 200){
                    goingLeft = false;
                    goingRight = true;
                }
                if(time <= -200){
                    goingLeft = true;
                    goingRight = false;
                }
                if(goingLeft){
                    setX(startXCoor + time);
                    setY(startYCoor - funcYPos(time));
                    time+= 5;
                } else if(goingRight){
                    setX(startXCoor + time);
                    setY(startYCoor + funcYPos(time));
                    time-= 5;
                }
                if(time == -145 && goingLeft){
                    BasicEnemyBullet basicEnemyBullet1 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet2 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, 0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet3 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet4 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, 1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet5 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet6 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet7 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet8 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet9 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet10 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet11 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet12 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet13 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet14 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet15 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet16 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet17 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet18 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, -1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet19 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, -2, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet20 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.0485, -1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet21 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet22 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet23 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet24 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    getWorld().getChildren().addAll(basicEnemyBullet1, basicEnemyBullet2, basicEnemyBullet3, basicEnemyBullet4, basicEnemyBullet5, basicEnemyBullet6, basicEnemyBullet7, basicEnemyBullet8, basicEnemyBullet9, basicEnemyBullet10, basicEnemyBullet11, basicEnemyBullet12, basicEnemyBullet13, basicEnemyBullet14, basicEnemyBullet15, basicEnemyBullet16, basicEnemyBullet17, basicEnemyBullet18, basicEnemyBullet19, basicEnemyBullet20, basicEnemyBullet21, basicEnemyBullet22, basicEnemyBullet23, basicEnemyBullet24);
                }
            } else{
                if(time >= 200){
                    goingLeft = false;
                    goingRight = true;
                }
                if(time <= -200){
                    goingLeft = true;
                    goingRight = false;
                }
                if(goingLeft){
                    setX(startXCoor + time);
                    setY(startYCoor - funcYPos(time));
                    time+= 5;
                } else if(goingRight){
                    setX(startXCoor + time);
                    setY(startYCoor + funcYPos(time));
                    time-= 5;
                }
                if(time == 145 && goingRight){
                    BasicEnemyBullet basicEnemyBullet1 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -2, 0, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet2 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, 0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet3 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet4 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, 1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet5 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet6 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet7 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, 2, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet8 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, 1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet9 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, 1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet10 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet11 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet12 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet13 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 2, 0, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet14 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet15 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.789, 0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet16 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet17 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet18 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0.485, -1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet19 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, 0, -2, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet20 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.0485, -1.94, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet21 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -0.894, -1.789, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet22 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.414, -1.414, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet23 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.789, -0.894, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    BasicEnemyBullet basicEnemyBullet24 = new BasicEnemyBullet(getX() + getFitWidth()/2, getY() + getFitHeight() + getFitHeight()/10, -1.94, -0.485, new Image(getClass().getClassLoader().getResource("resources/green bullet.png").toString()), getFitHeight()/8, getFitWidth()/8, true);
                    getWorld().getChildren().addAll(basicEnemyBullet1, basicEnemyBullet2, basicEnemyBullet3, basicEnemyBullet4, basicEnemyBullet5, basicEnemyBullet6, basicEnemyBullet7, basicEnemyBullet8, basicEnemyBullet9, basicEnemyBullet10, basicEnemyBullet11, basicEnemyBullet12, basicEnemyBullet13, basicEnemyBullet14, basicEnemyBullet15, basicEnemyBullet16, basicEnemyBullet17, basicEnemyBullet18, basicEnemyBullet19, basicEnemyBullet20, basicEnemyBullet21, basicEnemyBullet22, basicEnemyBullet23, basicEnemyBullet24);
                }
            }
        }
    }
}
