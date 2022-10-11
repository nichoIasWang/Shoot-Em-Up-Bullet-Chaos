import javafx.animation.AnimationTimer;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URISyntaxException;

public class Level2 extends World implements Resettable {
    private Game gameController;
    private Score score;
    private Ship ship;
    private Text laserCountText;
    private int enemiesDestroyed = 0;
    private int currentWave = 0;
    private boolean wave1Finished, wave2Finished, wave3Finished, wave4Finished, wave5Finished, wave6Finished, wave7Finished = false;
    private GameOverScreen gameOverScreenRootNode;
    private WinScreen winScreenRootNode;
    private BasicLaserEnemy basicLaserEnemy1, basicLaserEnemy2;
    private BasicEnemy basicEnemy1, basicEnemy2, basicEnemy3, basicEnemy4, basicEnemy5, basicEnemy6, basicEnemy7, basicEnemy8, basicEnemy9, basicEnemy10, basicEnemy11, basicEnemy12;
    private MissileEnemy missileEnemy1, missileEnemy2, missileEnemy3, missileEnemy4, missileEnemy5, missileEnemy6, missileEnemy7, missileEnemy8, missileEnemy9, missileEnemy10,
            missileEnemy11, missileEnemy12, missileEnemy13, missileEnemy14, missileEnemy15, missileEnemy16, missileEnemy17, missileEnemy18, missileEnemy19, missileEnemy20, missileEnemy21,
            missileEnemy22, missileEnemy23, missileEnemy24, missileEnemy25, missileEnemy26, missileEnemy27, missileEnemy28, missileEnemy29, missileEnemy30, missileEnemy31, missileEnemy32, missileEnemy33, missileEnemy34, missileEnemy35, missileEnemy36, missileEnemy37, missileEnemy38, missileEnemy39, missileEnemy40;
    private SmallSpiralEnemy smallSpiralEnemy1, smallSpiralEnemy2, smallSpiralEnemy3, smallSpiralEnemy4;
    private RightMovingEnemy rightMovingEnemy1, rightMovingEnemy2;
    private LeftMovingEnemy leftMovingEnemy1, leftMovingEnemy2;
    private CircleEnemy circleEnemy1, circleEnemy2;
    private Boss boss;
    private MovementTimer1 movementTimer1;
    private MovementTimer2 movementTimer2;
    private MovementTimer3 movementTimer3;
    private MovementTimer4 movementTimer4;
    private MovementTimer5 movementTimer5;
    private MovementTimer6 movementTimer6;
    private MovementTimer7 movementTimer7;
    private MovementTimer8 movementTimer8;
    private MovementTimer9 movementTimer9;
    private MovementTimer10 movementTimer10;
    private MovementTimer11 movementTimer11;
    private MovementTimer12 movementTimer12;
    private MovementTimer13 movementTimer13;
    private MovementTimer14 movementTimer14;
    private MovementTimer15 movementTimer15;
    private MovementTimer16 movementTimer16;
    private MediaPlayer levelMusic;
    private MediaPlayer bossMusic;

    public Level2(Game gameController) throws URISyntaxException {
        super();
        this.gameController = gameController;
        setWidth(800);
        setHeight(1000);
        score = new Score();
        score.setX(getWidth()/5);
        score.setY(getHeight() * (double)5/6);
        ship = new Ship((long)1.5e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight() * (double)5/6);
        laserCountText = new Text("Lasers Left: 7");
        laserCountText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 25));
        laserCountText.setFill(Color.GREEN);
        laserCountText.setStrokeWidth(3);
        laserCountText.setStroke(Color.LIGHTBLUE);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.LIGHTGREEN);
        laserCountText.setEffect(innerShadow);
        laserCountText.setX(getWidth()/5);
        laserCountText.setY(getHeight() * 4.5/6);
        getChildren().addAll(ship, laserCountText, score);
        ship.setLaserCount(7);
        Image image = new Image(getClass().getClassLoader().getResource("resources/level2background.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
        levelMusic = new MediaPlayer(new Media(getClass().getClassLoader().getResource("resources/level2music.mp3").toURI().toString()));
        levelMusic.setCycleCount(Integer.MAX_VALUE);
        bossMusic = new MediaPlayer(new Media(getClass().getClassLoader().getResource("resources/bossmusic.mp3").toURI().toString()));
        bossMusic.setCycleCount(Integer.MAX_VALUE);
    }

    public MediaPlayer getLevelMusic() {
        return levelMusic;
    }

    public MediaPlayer getBossMusic() {
        return bossMusic;
    }

    public Score getScore() {
        return score;
    }

    public void addEnemiesDestroyed(){
        enemiesDestroyed++;
    }

    public void removeEnemiesDestroyed() {
        enemiesDestroyed--;
    }

    public void wave1(){
        enemiesDestroyed = 0;
        currentWave = 1;
        basicLaserEnemy1 = new BasicLaserEnemy(150, 150, (long)1e9, 0, 10);
        basicLaserEnemy1.setX(getWidth()/3);
        basicLaserEnemy1.setY(-150);
        basicLaserEnemy2 = new BasicLaserEnemy(150, 150, (long)1e9, 0, 10);
        basicLaserEnemy2.setX(2 * getWidth()/3);
        basicLaserEnemy2.setY(-150);
        getChildren().addAll(basicLaserEnemy1, basicLaserEnemy2);
        movementTimer1 = new MovementTimer1();
        movementTimer1.start();
        movementTimer2 = new MovementTimer2();
        movementTimer2.start();
    }

    private class MovementTimer1 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 25 && !isPaused()){
                previous = now;
                basicLaserEnemy1.moveDown();
                basicLaserEnemy2.moveDown();
                count++;
            }
            if(count >= 25) {
                basicLaserEnemy1.startBulletTimer();
                basicLaserEnemy2.startBulletTimer();
                stop();
            }
        }
    }

    private class MovementTimer2 extends AnimationTimer{
        private long previous = 0;
        private boolean first = true;
        private int count = 0;
        private long delay = (long)8e9;
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
            if(isPaused())
                previous = now;
            if(now - previous >= delay && !first && !isPaused()){
                previous = now;
                delay = (long)1e7;
                basicLaserEnemy1.moveUp();
                basicLaserEnemy2.moveUp();
                count++;
            } else if(first){
                previous = now;
                first = false;
            }
            if(count >= 25) {
                basicLaserEnemy1.stopBulletTimer();
                basicLaserEnemy2.stopBulletTimer();
                stop();
                if(!wave1Finished) {
                    remove(basicLaserEnemy1);
                    remove(basicLaserEnemy2);
                    wave2();
                    wave1Finished = true;
                }
            }
        }
    }

    public void wave2(){
        enemiesDestroyed = 0;
        currentWave = 2;
        basicEnemy1 = new BasicEnemy(50, 50, (long)1e9, 0, 10);
        basicEnemy2 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy3 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy4 = new BasicEnemy(50, 50, (long)1e9, 0, 10);
        basicEnemy5 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy6 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy7 = new BasicEnemy(50, 50, (long)1e9, 0, 10);
        basicEnemy8 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy9 = new BasicEnemy(50, 50, (long)1e9, 0, 5);
        basicEnemy10 = new BasicEnemy(50, 50, (long)1e9, 0, 10);
        basicEnemy1.setX(getWidth()/10);
        basicEnemy2.setX(2 * getWidth()/10);
        basicEnemy3.setX(4 * getWidth()/10);
        basicEnemy4.setX(6 * getWidth()/10);
        basicEnemy5.setX(8 * getWidth()/10);
        basicEnemy6.setX(3 * getWidth()/10);
        basicEnemy7.setX(5 * getWidth()/10);
        basicEnemy8.setX(7 * getWidth()/10);
        basicEnemy9.setX(9 * getWidth()/10);
        basicEnemy10.setX(9.5 * getWidth()/10);
        basicEnemy1.setY(0);
        basicEnemy2.setY(0);
        basicEnemy3.setY(0);
        basicEnemy4.setY(0);
        basicEnemy5.setY(0);
        basicEnemy6.setY(0);
        basicEnemy7.setY(0);
        basicEnemy8.setY(0);
        basicEnemy9.setY(0);
        basicEnemy10.setY(0);
        basicEnemy1.setHealth(300);
        basicEnemy2.setHealth(300);
        basicEnemy3.setHealth(300);
        basicEnemy4.setHealth(300);
        basicEnemy5.setHealth(300);
        basicEnemy6.setHealth(300);
        basicEnemy7.setHealth(300);
        basicEnemy8.setHealth(300);
        basicEnemy9.setHealth(300);
        basicEnemy10.setHealth(300);
        getChildren().addAll(basicEnemy1,basicEnemy2,basicEnemy3,basicEnemy4,basicEnemy5,basicEnemy6,basicEnemy7,basicEnemy8,basicEnemy9,basicEnemy10);
        movementTimer3 = new MovementTimer3();
        movementTimer3.start();
        movementTimer4 = new MovementTimer4();
        movementTimer4.start();
        movementTimer5 = new MovementTimer5();
        movementTimer5.start();
        movementTimer6 = new MovementTimer6();
        movementTimer6.start();
        movementTimer7 = new MovementTimer7();
        movementTimer7.start();
        movementTimer8 = new MovementTimer8();
        movementTimer8.start();
        missileEnemy1 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy2 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy3 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy4 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy5 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy6 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy7 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy8 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy9 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy10 = new MissileEnemy(50, 50, (long)1e9, 0, 15);
        missileEnemy1.setX(getWidth()/4);
        missileEnemy2.setX(getWidth()/4);
        missileEnemy3.setX(getWidth()/4);
        missileEnemy4.setX(getWidth()/4);
        missileEnemy5.setX(getWidth()/4);
        missileEnemy6.setX(3 * getWidth()/4);
        missileEnemy7.setX(3 * getWidth()/4);
        missileEnemy8.setX(3 * getWidth()/4);
        missileEnemy9.setX(3 * getWidth()/4);
        missileEnemy10.setX(3 * getWidth()/4);
        missileEnemy1.setY(0);
        missileEnemy2.setY(0);
        missileEnemy3.setY(0);
        missileEnemy4.setY(0);
        missileEnemy5.setY(0);
        missileEnemy6.setY(0);
        missileEnemy7.setY(0);
        missileEnemy8.setY(0);
        missileEnemy9.setY(0);
        missileEnemy10.setY(0);
        movementTimer9 = new MovementTimer9();
        movementTimer9.start();
    }

    private class MovementTimer3 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 12 && !isPaused()){
                previous = now;
                basicEnemy1.moveDown();
                basicEnemy2.moveDown();
                count++;
            }
            if(count >= 12) {
                basicEnemy1.startBulletTimer();
                basicEnemy2.startBulletTimer();
                stop();
            }
        }
    }

    private class MovementTimer4 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)2e7 && count < 12 && !isPaused()){
                previous = now;
                basicEnemy3.moveDown();
                basicEnemy4.moveDown();
                count++;
            }
            if(count >= 12) {
                basicEnemy3.startBulletTimer();
                basicEnemy4.startBulletTimer();
                stop();
            }
        }
    }
    private class MovementTimer5 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)3e7 && count < 12 && !isPaused()){
                previous = now;
                basicEnemy5.moveDown();
                basicEnemy6.moveDown();
                count++;
            }
            if(count >= 12) {
                basicEnemy5.startBulletTimer();
                basicEnemy6.startBulletTimer();
                stop();
            }
        }
    }
    private class MovementTimer6 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)4e7 && count < 12 && !isPaused()){
                previous = now;
                basicEnemy7.moveDown();
                basicEnemy8.moveDown();
                count++;
            }
            if(count >= 12) {
                basicEnemy7.startBulletTimer();
                basicEnemy8.startBulletTimer();
                stop();
            }
        }
    }
    private class MovementTimer7 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)5e7 && count < 12 && !isPaused()){
                previous = now;
                basicEnemy9.moveDown();
                basicEnemy10.moveDown();
                count++;
            }
            if(count >= 12) {
                basicEnemy9.startBulletTimer();
                basicEnemy10.startBulletTimer();
                stop();
            }
        }
    }

    private class MovementTimer8 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
        private long delay = (long)10e9;
        private boolean first = true;
        /**
         * This method needs to be overridden by extending classes. It is going to
         * be called in every frame while the {@code AnimationTimer} is active.
         *
         * @param now The timestamp of the current frame given in nanoseconds. This
         *            value will be the same for all {@code AnimationTimers} called
         *            during one frame.
         */
        public void handle(long now) {
            if(isPaused())
                previous = now;
            if(now - previous >= delay && !first && !isPaused()){
                previous = now;
                delay = (long)1e7;
                basicEnemy1.moveUp();
                basicEnemy2.moveUp();
                basicEnemy3.moveUp();
                basicEnemy4.moveUp();
                basicEnemy5.moveUp();
                basicEnemy6.moveUp();
                basicEnemy7.moveUp();
                basicEnemy8.moveUp();
                basicEnemy9.moveUp();
                basicEnemy10.moveUp();
                count++;
            } else if(first){
                previous = now;
                first = false;
            }
            if(count >= 12) {
                basicEnemy1.stopBulletTimer();
                basicEnemy2.stopBulletTimer();
                basicEnemy3.stopBulletTimer();
                basicEnemy4.stopBulletTimer();
                basicEnemy5.stopBulletTimer();
                basicEnemy6.stopBulletTimer();
                basicEnemy7.stopBulletTimer();
                basicEnemy8.stopBulletTimer();
                basicEnemy9.stopBulletTimer();
                basicEnemy10.stopBulletTimer();
                stop();
                if(!wave2Finished) {
                    remove(basicEnemy1);
                    remove(basicEnemy2);
                    remove(basicEnemy3);
                    remove(basicEnemy4);
                    remove(basicEnemy5);
                    remove(basicEnemy6);
                    remove(basicEnemy7);
                    remove(basicEnemy8);
                    remove(basicEnemy9);
                    remove(basicEnemy10);
                    wave2Finished = true;
                    wave3();
                }
            }
        }
    }

    private class MovementTimer9 extends AnimationTimer{
        private boolean first, second, third, fourth, fifth = false;
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
            if(isPaused()) {
                if(first && !second)
                    previous = now;
                else if(second && !third)
                    previous = now - (long)5e8;
                else if(third && !fourth)
                    previous = now - (long)1e9;
                else if(fourth && !fifth)
                    previous = now - (long)1.5e9;
                else if(fifth)
                    previous = now - (long)2e9;
            }
            if(!first){
                getChildren().addAll(missileEnemy1, missileEnemy6);
                first = true;
                previous = now;
            } else if(now - previous >= 5e8 && now - previous < 1e9 && !second){
                getChildren().addAll(missileEnemy2, missileEnemy7);
                missileEnemy1.startBulletTimer();
                missileEnemy6.startBulletTimer();
                missileEnemy1.turn();
                missileEnemy1.turn();
                second = true;
            } else if(now - previous >= 1e9 && now - previous < 1.5e9 && !third){
                getChildren().addAll(missileEnemy3, missileEnemy8);
                missileEnemy2.startBulletTimer();
                missileEnemy7.startBulletTimer();
                missileEnemy2.turn();
                missileEnemy7.turn();
                third = true;
            } else if(now - previous >= 1.5e9 && now - previous < 2e9 && !fourth){
                getChildren().addAll(missileEnemy4, missileEnemy9);
                missileEnemy3.startBulletTimer();
                missileEnemy8.startBulletTimer();
                missileEnemy3.turn();
                missileEnemy8.turn();
                fourth = true;
            } else if(now - previous >= 2e9 && now - previous < 2.5e9 && !fifth) {
                getChildren().addAll(missileEnemy5, missileEnemy10);
                missileEnemy4.startBulletTimer();
                missileEnemy9.startBulletTimer();
                missileEnemy4.turn();
                missileEnemy9.turn();
                fifth = true;
            } else if(now - previous >= 2.5e9){
                missileEnemy5.startBulletTimer();
                missileEnemy10.startBulletTimer();
                missileEnemy5.turn();
                missileEnemy10.turn();
                stop();
            }
        }
    }

    public void wave3(){
        currentWave = 3;
        enemiesDestroyed = 0;
        smallSpiralEnemy1 = new SmallSpiralEnemy(100, 100, (long)3e9, 0, 10);
        smallSpiralEnemy1.setX(getWidth()/5);
        smallSpiralEnemy1.setY(-100);
        smallSpiralEnemy2 = new SmallSpiralEnemy(100, 100, (long)3e9, 0, 10);
        smallSpiralEnemy2.setX(2 * getWidth()/5);
        smallSpiralEnemy2.setY(-100);
        smallSpiralEnemy3 = new SmallSpiralEnemy(100, 100, (long)3e9, 0, 10);
        smallSpiralEnemy3.setX(3 * getWidth()/5);
        smallSpiralEnemy3.setY(-100);
        smallSpiralEnemy4 = new SmallSpiralEnemy(100, 100, (long)3e9, 0, 10);
        smallSpiralEnemy4.setX(4 * getWidth()/5);
        smallSpiralEnemy4.setY(-100);
        getChildren().addAll(smallSpiralEnemy1, smallSpiralEnemy2, smallSpiralEnemy3, smallSpiralEnemy4);
        movementTimer10 = new MovementTimer10();
        movementTimer10.start();
        movementTimer11 = new MovementTimer11();
        movementTimer11.start();
    }

    private class MovementTimer10 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 10 && !isPaused()){
                previous = now;
                smallSpiralEnemy1.moveDown();
                smallSpiralEnemy2.moveDown();
                smallSpiralEnemy3.moveDown();
                smallSpiralEnemy4.moveDown();
                count++;
            }
            if(count >= 10) {
                smallSpiralEnemy1.startBulletTimer();
                smallSpiralEnemy2.startBulletTimer();
                smallSpiralEnemy3.startBulletTimer();
                smallSpiralEnemy4.startBulletTimer();
                stop();
            }
        }
    }

    private class MovementTimer11 extends AnimationTimer{
        private long previous = 0;
        private boolean first = true;
        private int count = 0;
        private long delay = (long)10e9;
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
            if(isPaused())
                previous = now;
            if(now - previous >= delay && !first && !isPaused()){
                previous = now;
                delay = (long)1e7;
                smallSpiralEnemy1.moveUp();
                smallSpiralEnemy2.moveUp();
                smallSpiralEnemy3.moveUp();
                smallSpiralEnemy4.moveUp();
                count++;
            } else if(first){
                previous = now;
                first = false;
            }
            if(count >= 50) {
                smallSpiralEnemy1.stopBulletTimer();
                smallSpiralEnemy2.stopBulletTimer();
                smallSpiralEnemy3.stopBulletTimer();
                smallSpiralEnemy4.stopBulletTimer();
                stop();
                if(!wave3Finished) {
                    remove(smallSpiralEnemy1);
                    remove(smallSpiralEnemy2);
                    remove(smallSpiralEnemy3);
                    remove(smallSpiralEnemy4);
                    wave3Finished = true;
                    wave4();
                }
            }
        }
    }

    public void wave4(){
        currentWave = 4;
        enemiesDestroyed = 0;
        rightMovingEnemy1 = new RightMovingEnemy(100, 100, 0, 0, 0, 0, getHeight()/4);
        rightMovingEnemy2 = new RightMovingEnemy(100, 100, 0, 0, 0, 0, getHeight()/3);
        leftMovingEnemy1 = new LeftMovingEnemy(100, 100, 0, 0, 0, getWidth(),  getHeight()/3.5);
        leftMovingEnemy2 = new LeftMovingEnemy(100, 100, 0, 0, 0, getWidth(), getHeight()/4.5);
        movementTimer12 = new MovementTimer12();
        movementTimer12.start();
    }

    private class MovementTimer12 extends AnimationTimer{
        private boolean first, second, third, fourth = false;
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
            if(isPaused()) {
                if(first && !second)
                    previous = now;
                else if(second && !third)
                    previous = now - (long)5e8;
                else if(third && !fourth)
                    previous = now - (long)1e9;
                else if(fourth)
                    previous = now - (long)3e9;
            }
            if(!first){
                getChildren().addAll(rightMovingEnemy1);
                rightMovingEnemy1.startBulletTimer();
                first = true;
                previous = now;
            } else if(now - previous >= 5e8 && now - previous < 1e9 && !second){
                getChildren().add(leftMovingEnemy1);
                leftMovingEnemy1.startBulletTimer();
                second = true;
            } else if(now - previous >= 1e9 && now - previous < 1.5e9 && !third){
                getChildren().addAll(rightMovingEnemy2);
                rightMovingEnemy2.startBulletTimer();
                third = true;
            } else if(now - previous >= 1.5e9 && now - previous < 5e9 && !fourth){
                getChildren().add(leftMovingEnemy2);
                leftMovingEnemy2.startBulletTimer();
                fourth = true;
            } else if(now - previous >= 5e9){
                if(!wave4Finished) {
                    wave4Finished = true;
                    wave5();
                }
                stop();
            }
        }
    }

    public void wave5(){
        currentWave = 5;
        enemiesDestroyed = 0;
        missileEnemy11 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy12 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy13 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy14 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy15 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy16 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy17 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy18 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy19 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy20 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy21 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy22 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy23 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy24 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy25 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy26 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy27 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy28 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy29 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy30 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy31 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy32 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy33 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy34 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy35 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy36 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy37 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy38 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy39 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy40 = new MissileEnemy(50, 50, (long)1.5e9, 0, 15);
        missileEnemy11.setX(0);
        missileEnemy12.setX(0);
        missileEnemy13.setX(0);
        missileEnemy14.setX(0);
        missileEnemy15.setX(0);
        missileEnemy16.setX(getWidth());
        missileEnemy17.setX(getWidth());
        missileEnemy18.setX(getWidth());
        missileEnemy19.setX(getWidth());
        missileEnemy20.setX(getWidth());
        missileEnemy21.setX(0);
        missileEnemy22.setX(0);
        missileEnemy23.setX(0);
        missileEnemy24.setX(0);
        missileEnemy25.setX(0);
        missileEnemy26.setX(getWidth());
        missileEnemy27.setX(getWidth());
        missileEnemy28.setX(getWidth());
        missileEnemy29.setX(getWidth());
        missileEnemy30.setX(getWidth());
        missileEnemy31.setX(0);
        missileEnemy32.setX(0);
        missileEnemy33.setX(0);
        missileEnemy34.setX(0);
        missileEnemy35.setX(0);
        missileEnemy36.setX(getWidth());
        missileEnemy37.setX(getWidth());
        missileEnemy38.setX(getWidth());
        missileEnemy39.setX(getWidth());
        missileEnemy40.setX(getWidth());
        missileEnemy11.setY(getHeight()/5);
        missileEnemy12.setY(getHeight()/5);
        missileEnemy13.setY(getHeight()/5);
        missileEnemy14.setY(getHeight()/5);
        missileEnemy15.setY(getHeight()/5);
        missileEnemy16.setY(getHeight()/5);
        missileEnemy17.setY(getHeight()/5);
        missileEnemy18.setY(getHeight()/5);
        missileEnemy19.setY(getHeight()/5);
        missileEnemy20.setY(getHeight()/5);
        missileEnemy21.setY(getHeight()/4);
        missileEnemy22.setY(getHeight()/4);
        missileEnemy23.setY(getHeight()/4);
        missileEnemy24.setY(getHeight()/4);
        missileEnemy25.setY(getHeight()/4);
        missileEnemy26.setY(getHeight()/4);
        missileEnemy27.setY(getHeight()/4);
        missileEnemy28.setY(getHeight()/4);
        missileEnemy29.setY(getHeight()/4);
        missileEnemy30.setY(getHeight()/4);
        missileEnemy31.setY(getHeight()/3);
        missileEnemy32.setY(getHeight()/3);
        missileEnemy33.setY(getHeight()/3);
        missileEnemy34.setY(getHeight()/3);
        missileEnemy35.setY(getHeight()/3);
        missileEnemy36.setY(getHeight()/3);
        missileEnemy37.setY(getHeight()/3);
        missileEnemy38.setY(getHeight()/3);
        missileEnemy39.setY(getHeight()/3);
        missileEnemy40.setY(getHeight()/3);
        movementTimer13 = new MovementTimer13();
        movementTimer13.start();
        basicEnemy11 = new BasicEnemy(50, 50, (long)4e9, 0, 5);
        basicEnemy12 = new BasicEnemy(50, 50, (long)4e9, 0, 5);
        basicEnemy11.setY(-50);
        basicEnemy12.setY(-50);
        basicEnemy11.setX(0);
        basicEnemy11.setX(getWidth() - 50);
        basicEnemy11.setCanKill(false);
        basicEnemy12.setCanKill(false);
        basicEnemy11.setUseAltImage(true);
        basicEnemy12.setUseAltImage(true);
        getChildren().addAll(basicEnemy11, basicEnemy12);
        movementTimer14 = new MovementTimer14();
        movementTimer14.start();
    }

    private class MovementTimer13 extends AnimationTimer{
        private boolean first, second, third, fourth, fifth, sixth, seventh, eighth, ninth, tenth, eleventh, twelfth, thirteenth, fourteenth, fifteenth = false;
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
            if(isPaused()) {
                if(first && !second)
                    previous = now;
                else if(second && !third)
                    previous = now - (long)2.5e8;
                else if(third && !fourth)
                    previous = now - (long)5e8;
                else if(fourth && !fifth)
                    previous = now - (long)7.5e8;
                else if(fifth && !sixth)
                    previous = now - (long)1e9;
                else if(sixth && !seventh)
                    previous = now - (long)2e9;
                else if(seventh && !eighth)
                    previous = now - (long)2.25e9;
                else if(eighth && !ninth)
                    previous = now - (long)2.5e9;
                else if(ninth && !tenth)
                    previous = now - (long)2.75e9;
                else if(tenth && !eleventh)
                    previous = now - (long)3e9;
                else if(eleventh && !twelfth)
                    previous = now - (long)4e9;
                else if(twelfth && !thirteenth)
                    previous = now - (long)4.25e9;
                else if(thirteenth && !fourteenth)
                    previous = now - (long)4.5e9;
                else if(fourteenth && !fifteenth)
                    previous = now - (long)4.75e9;
                else if(fifteenth)
                    previous = now - (long)5e9;
            }
            if(!first){
                getChildren().addAll(missileEnemy11, missileEnemy16);
                missileEnemy11.startBulletTimer();
                missileEnemy16.startBulletTimer();
                missileEnemy11.setShipXCoor(ship.getX());
                missileEnemy16.setShipYCoor(ship.getY());
                first = true;
                previous = now;
            } else if(now - previous >= 2.5e8 && now - previous < 5e8 && !second){
                getChildren().addAll(missileEnemy12, missileEnemy17);
                missileEnemy12.startBulletTimer();
                missileEnemy17.startBulletTimer();
                missileEnemy12.setShipXCoor(ship.getX());
                missileEnemy17.setShipYCoor(ship.getY());
                second = true;
            } else if(now - previous >= 5e8 && now - previous < 7.5e8 && !third){
                getChildren().addAll(missileEnemy13, missileEnemy18);
                missileEnemy13.startBulletTimer();
                missileEnemy18.startBulletTimer();
                missileEnemy13.setShipXCoor(ship.getX());
                missileEnemy18.setShipYCoor(ship.getY());
                third = true;
            } else if(now - previous >= 7.5e8 && now - previous < 1e9 && !fourth){
                getChildren().addAll(missileEnemy14, missileEnemy19);
                missileEnemy14.startBulletTimer();
                missileEnemy19.startBulletTimer();
                missileEnemy14.setShipXCoor(ship.getX());
                missileEnemy19.setShipYCoor(ship.getY());
                fourth = true;
            } else if(now - previous >= 1e9 && !fifth){
                getChildren().addAll(missileEnemy15, missileEnemy20);
                missileEnemy15.startBulletTimer();
                missileEnemy20.startBulletTimer();
                missileEnemy15.setShipXCoor(ship.getX());
                missileEnemy20.setShipYCoor(ship.getY());
                fifth = true;
            } else if(now - previous >= 2e9 && !sixth){
                getChildren().addAll(missileEnemy21, missileEnemy26);
                missileEnemy21.startBulletTimer();
                missileEnemy26.startBulletTimer();
                missileEnemy21.setShipXCoor(ship.getX());
                missileEnemy26.setShipYCoor(ship.getY());
                sixth = true;
            } else if(now - previous >= 2.25e9 && !seventh){
                getChildren().addAll(missileEnemy22, missileEnemy27);
                missileEnemy22.startBulletTimer();
                missileEnemy27.startBulletTimer();
                missileEnemy22.setShipXCoor(ship.getX());
                missileEnemy27.setShipYCoor(ship.getY());
                seventh = true;
            } else if(now - previous >= 2.5e9 && !eighth){
                getChildren().addAll(missileEnemy23, missileEnemy28);
                missileEnemy23.startBulletTimer();
                missileEnemy28.startBulletTimer();
                missileEnemy23.setShipXCoor(ship.getX());
                missileEnemy28.setShipYCoor(ship.getY());
                eighth = true;
            } else if(now - previous >= 2.75e9 && !ninth){
                getChildren().addAll(missileEnemy24, missileEnemy29);
                missileEnemy24.startBulletTimer();
                missileEnemy29.startBulletTimer();
                missileEnemy24.setShipXCoor(ship.getX());
                missileEnemy29.setShipYCoor(ship.getY());
                ninth = true;
            } else if(now - previous >= 3e9 && !tenth){
                getChildren().addAll(missileEnemy25, missileEnemy30);
                missileEnemy25.startBulletTimer();
                missileEnemy30.startBulletTimer();
                missileEnemy25.setShipXCoor(ship.getX());
                missileEnemy30.setShipYCoor(ship.getY());
                tenth = true;
            } else if(now - previous >= 4e9 && !eleventh){
                getChildren().addAll(missileEnemy31, missileEnemy36);
                missileEnemy31.startBulletTimer();
                missileEnemy36.startBulletTimer();
                missileEnemy31.setShipXCoor(ship.getX());
                missileEnemy36.setShipYCoor(ship.getY());
                eleventh = true;
            } else if(now - previous >= 4.25e9 && !twelfth){
                getChildren().addAll(missileEnemy32, missileEnemy37);
                missileEnemy32.startBulletTimer();
                missileEnemy37.startBulletTimer();
                missileEnemy32.setShipXCoor(ship.getX());
                missileEnemy37.setShipYCoor(ship.getY());
                twelfth = true;
            }  else if(now - previous >= 4.5e9 && !thirteenth){
                getChildren().addAll(missileEnemy33, missileEnemy38);
                missileEnemy33.startBulletTimer();
                missileEnemy38.startBulletTimer();
                missileEnemy33.setShipXCoor(ship.getX());
                missileEnemy38.setShipYCoor(ship.getY());
                thirteenth = true;
            } else if(now - previous >= 4.75e9 && !fourteenth){
                getChildren().addAll(missileEnemy34, missileEnemy39);
                missileEnemy34.startBulletTimer();
                missileEnemy39.startBulletTimer();
                missileEnemy34.setShipXCoor(ship.getX());
                missileEnemy39.setShipYCoor(ship.getY());
                fourteenth = true;
            } else if(now - previous >= 5e9 && !fifteenth){
                getChildren().addAll(missileEnemy35, missileEnemy40);
                missileEnemy35.startBulletTimer();
                missileEnemy40.startBulletTimer();
                missileEnemy35.setShipXCoor(ship.getX());
                missileEnemy40.setShipYCoor(ship.getY());
                fifteenth = true;
            } else if(now - previous >= 7e9){
                stop();
                wave6();
                wave5Finished = true;
            }
        }
    }

    private class MovementTimer14 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 20 && !isPaused()){
                previous = now;
                basicEnemy11.moveDown();
                basicEnemy12.moveDown();
                count++;
            }
            if(count >= 20) {
                basicEnemy11.startBulletTimer();
                basicEnemy12.startBulletTimer();
                stop();
            }
        }
    }

    public void wave6(){
        enemiesDestroyed = 0;
        currentWave = 6;
        boss = new Boss(350, 350, 0, 0, 3);
        boss.setX(getWidth()/2 - (double)250/2);
        boss.setY(-250);
        getChildren().add(boss);
        movementTimer15 = new MovementTimer15();
        movementTimer15.start();
    }

    private class MovementTimer15 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
        private boolean first = true;
        private long delay = (long)3e9;
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
            if(first){
                first = false;
                previous = now;
            }
            if(isPaused())
                previous = now;
            if(now - previous >= delay && count < 80 && !isPaused()){
                delay = (long)1e7;
                previous = now;
                boss.moveDown();
                count++;
            }
            if(count >= 80) {
                boss.startBulletTimer();
                levelMusic.stop();
                bossMusic.play();
                stop();
            }
        }
    }

    public void wave7(){
        enemiesDestroyed = 0;
        currentWave = 7;
        circleEnemy1 = new CircleEnemy(150, 150, 0, 0, 5, 400, 250, true);
        circleEnemy2 = new CircleEnemy(150, 150, 0, 0, 5, 400, 250, false);
        circleEnemy1.setX(200);
        circleEnemy1.setY(-150);
        circleEnemy2.setX(600);
        circleEnemy2.setY(-150);
        getChildren().addAll(circleEnemy1, circleEnemy2);
        movementTimer16 = new MovementTimer16();
        movementTimer16.start();
    }

    private class MovementTimer16 extends AnimationTimer{
        private long previous = 0;
        private int count = 0;
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
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 70 && !isPaused()){
                previous = now;
                circleEnemy1.moveDown();
                circleEnemy2.moveDown();
                count++;
            }
            if(count >= 70) {
                circleEnemy1.startBulletTimer();
                circleEnemy2.startBulletTimer();
                stop();
            }
        }
    }

    @Override
    public void reset() {
        while(!getChildren().isEmpty()) {
            getChildren().remove(0);
        }
        score = new Score();
        score.setX(getWidth()/5);
        score.setY(getHeight() * (double)5/6);
        ship = new Ship((long)1.5e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight() * (double)5/6);
        laserCountText = new Text("Lasers Left: 3");
        laserCountText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 25));
        laserCountText.setFill(Color.GREEN);
        laserCountText.setStrokeWidth(3);
        laserCountText.setStroke(Color.LIGHTBLUE);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.LIGHTGREEN);
        laserCountText.setEffect(innerShadow);
        laserCountText.setX(getWidth()/5);
        laserCountText.setY(getHeight() * (double)4.5/6);
        getChildren().addAll(ship, laserCountText, score);
        ship.setLaserCount(7);
        wave1Finished = false;
        wave2Finished = false;
        wave3Finished = false;
        wave4Finished = false;
        wave5Finished = false;
        wave6Finished = false;
        wave7Finished = false;
        currentWave = 1;
        enemiesDestroyed = 0;
        try{
            movementTimer1.stop();
            movementTimer2.stop();
            movementTimer3.stop();
            movementTimer4.stop();
            movementTimer5.stop();
            movementTimer6.stop();
            movementTimer7.stop();
            movementTimer8.stop();
            movementTimer9.stop();
            movementTimer10.stop();
            movementTimer11.stop();
            movementTimer12.stop();
            movementTimer13.stop();
            movementTimer14.stop();
            movementTimer15.stop();
            movementTimer16.stop();
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void act(long now) {
        laserCountText.setText("Lasers left: " + String.valueOf(ship.getLaserCount()));
        for(Enemy e : getObjects(Enemy.class)){
            e.setShipXCoor(ship.getX());
            e.setShipYCoor(ship.getY());
        }
        if(currentWave == 1){
            if(enemiesDestroyed == 2 && !wave1Finished) {
                wave1Finished = true;
                wave2();
            }
        }
        else if(currentWave == 2) {
            if (enemiesDestroyed == 20 && !wave2Finished) {
                wave2Finished = true;
                wave3();
            }
        } else if(currentWave == 3){
            if(enemiesDestroyed == 5 && !wave3Finished){
                wave3Finished = true;
                wave4();
            }
        } else if(currentWave == 6){
            if(enemiesDestroyed == 1 && !wave6Finished){
                wave7();
                wave6Finished = true;
            }
        } else if(currentWave == 7){
            if(enemiesDestroyed == 2 && !wave7Finished){
                wave7Finished = true;
                for(Enemy e : getObjects(Enemy.class)){
                    e.stopBulletTimer();
                }
                bossMusic.stop();
                levelMusic.play();
                winScreenRootNode = new WinScreen(gameController, "You Win!", score.getValue());
                winScreenRootNode.setForLevel1(false);
                winScreenRootNode.setForLevel2(true);
                reset();
                winScreenRootNode.requestFocus();
                gameController.getGameRootNode().setCenter(winScreenRootNode);
            }
        }
        if(ship.isDead()){
            for(Enemy e : getObjects(Enemy.class)){
                e.stopBulletTimer();
            }
            bossMusic.stop();
            levelMusic.play();
            gameOverScreenRootNode = new GameOverScreen(gameController, "Game Over", score.getValue());
            gameOverScreenRootNode.setForLevel2(true);
            gameOverScreenRootNode.setForLevel1(false);
            reset();
            gameController.getGameRootNode().setCenter(gameOverScreenRootNode);
            gameOverScreenRootNode.requestFocus();
        }
        setOnKeyPressed(event -> {
            addKeyDown(event.getCode());
            if(event.getCode() == KeyCode.SPACE){
                if(isPaused()) {
                    // do a little timer before resuming
                    unpause();
                    for(Enemy e : getObjects(Enemy.class)){
                        e.startBulletTimer();
                    }
                } else {
                    // display pause menu screen
                    for(Enemy e : getObjects(Enemy.class)){
                        e.stopBulletTimer();
                    }
                    pause();
                    gameController.getGameRootNode().setCenter(gameController.getPauseScreenRootNode());
                    gameController.getPauseScreenRootNode().setForLevel1(false);
                    gameController.getPauseScreenRootNode().setForLevel2(true);
                }
            } else if(event.getCode() == KeyCode.Z && !isPaused()){
                ship.startBulletTimer();
            } else if(event.getCode() == KeyCode.X && !isPaused()){
                ship.startLaserTimer();
            }
        });
        setOnKeyReleased(event -> {
            removeKeyDown(event.getCode());
            if(event.getCode() == KeyCode.Z)
                ship.stopBulletTimer();
            else if(event.getCode() == KeyCode.X)
                ship.stopLaserTimer();
        });
        setOnMouseMoved(event -> {
            if(!isPaused()){
                if(event.getSceneX() - ship.getFitWidth()/2 < 0)
                    ship.setX(0);
                else if(event.getSceneX() - ship.getFitWidth()/2 > getWidth())
                    ship.setX(getWidth());
                else
                    ship.setX(event.getSceneX() - ship.getFitWidth() / 2);
                if(event.getSceneY() - ship.getFitHeight()/2 < 0)
                    ship.setY(0);
                else if(event.getSceneY() - ship.getFitHeight()/2 > getHeight())
                    ship.setY(getHeight());
                else
                    ship.setY(event.getSceneY() - ship.getFitHeight() / 2);
            }
        });
    }
}
