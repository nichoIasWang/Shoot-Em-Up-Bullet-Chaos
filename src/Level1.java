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

public class Level1 extends World implements Resettable {
    private Ship ship;
    private Game gameController;
    private Score score;
    private Text laserCountText;
    private int enemiesDestroyed = 0;
    private int currentWave = 0;
    private boolean wave1Finished, wave2Finished, wave3Finished = false;
    private GameOverScreen gameOverScreenRootNode;
    private WinScreen winScreenRootNode;
    private boolean resetted = false;
    private boolean alreadyStopped1, alreadyStopped2, alreadyStopped3, alreadyStopped4, alreadyStopped5, alreadyStopped6, alreadyStopped7, alreadyStopped8 = false;
    private BasicEnemy enemy1, enemy2, enemy3, enemy4, enemy5;
    private MovementTimer1 movementTimer1;
    private MovementTimer2 movementTimer2;
    private MovementTimer3 movementTimer3;
    private MovementTimer4 movementTimer4;
    private MovementTimer5 movementTimer5;
    private MovementTimer6 movementTimer6;
    private MovementTimer7 movementTimer7;
    private HomingEnemy homingEnemy1, homingEnemy2, homingEnemy3, homingEnemy4, homingEnemy5, homingEnemy6, homingEnemy7, homingEnemy8, homingEnemy9, homingEnemy10, homingEnemy11, homingEnemy12, homingEnemy13, homingEnemy14, homingEnemy15, homingEnemy16, homingEnemy17, homingEnemy18, homingEnemy19, homingEnemy20;
    private LaserEnemy laserEnemy1, laserEnemy2;
    private MediaPlayer levelMusic;

    public Level1(Game gameController) throws URISyntaxException {
        super();
        this.gameController = gameController;
        setWidth(800);
        setHeight(1000);
        score = new Score();
        score.setX(getWidth()/5);
        score.setY(getHeight() * (double)5/6);
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
        laserCountText.setY(getHeight() * 4.5/6);
        ship = new Ship((long)3e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight() * (double)5/6);
        getChildren().addAll(ship, laserCountText, score);
        Image image = new Image(getClass().getClassLoader().getResource("resources/level1background.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
        levelMusic = new MediaPlayer(new Media(getClass().getClassLoader().getResource("resources/level1music.mp3").toURI().toString()));
        levelMusic.setCycleCount(Integer.MAX_VALUE);
    }

    public MediaPlayer getLevelMusic() {
        return levelMusic;
    }

    public void wave1(){
        enemiesDestroyed = 0;
        currentWave = 1;
        enemy1 = new BasicEnemy(100, 100, (long)1e9, 0, 5);
        enemy1.setX(getWidth()/4);
        enemy1.setY(0);
        enemy2 = new BasicEnemy(100, 100, (long)1e9, 0, 5);
        enemy2.setX(getWidth()/2);
        enemy2.setY(0);
        enemy3 = new BasicEnemy(100, 100, (long)1e9, 0, 5);
        enemy3.setX(3 * getWidth()/4);
        enemy3.setY(0);
        getChildren().addAll(enemy1, enemy2, enemy3);
        movementTimer1 = new MovementTimer1();
        movementTimer1.start();
        movementTimer2 = new MovementTimer2();
        enemy1.startBulletTimer();
        enemy2.startBulletTimer();
        enemy3.startBulletTimer();
        movementTimer2.start();
    }

    public void wave2(){
        enemiesDestroyed = 0;
        currentWave = 2;
        homingEnemy1 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy1.startBulletTimer();
        homingEnemy2 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy2.startBulletTimer();
        homingEnemy3 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy3.startBulletTimer();
        homingEnemy4 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy4.startBulletTimer();
        homingEnemy5 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy5.startBulletTimer();
        homingEnemy6 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy6.startBulletTimer();
        homingEnemy7 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy7.startBulletTimer();
        homingEnemy8 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy8.startBulletTimer();
        homingEnemy9 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy9.startBulletTimer();
        homingEnemy10 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy10.startBulletTimer();
        homingEnemy1.setX(1);
        homingEnemy2.setX(1);
        homingEnemy3.setX(1);
        homingEnemy4.setX(1);
        homingEnemy5.setX(1);
        homingEnemy6.setX(getWidth());
        homingEnemy7.setX(getWidth());
        homingEnemy8.setX(getWidth());
        homingEnemy9.setX(getWidth());
        homingEnemy10.setX(getWidth());
        homingEnemy1.setY(-10);
        homingEnemy2.setY(-10);
        homingEnemy3.setY(-10);
        homingEnemy4.setY(-10);
        homingEnemy5.setY(-10);
        homingEnemy6.setY(-10);
        homingEnemy7.setY(-10);
        homingEnemy8.setY(-10);
        homingEnemy9.setY(-10);
        homingEnemy10.setY(-10);
        movementTimer3 = new MovementTimer3();
        movementTimer3.start();
        enemy4 = new BasicEnemy(100, 100, (long)1e9, 0, 5);
        enemy4.setX(getWidth()/3);
        enemy4.setY(-100);
        enemy5 = new BasicEnemy(100, 100, (long)1e9, 0, 5);
        enemy5.setX(2 * getWidth()/3);
        enemy5.setY(-100);
        getChildren().addAll(enemy4, enemy5);
        movementTimer4 = new MovementTimer4();
        movementTimer4.start();
        movementTimer5 = new MovementTimer5();
        enemy4.startBulletTimer();
        enemy5.startBulletTimer();
        movementTimer5.start();
    }

    public void wave3(){
        enemiesDestroyed = 0;
        currentWave = 3;
        homingEnemy11 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy12 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy13 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy14 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy15 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy16 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy17 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy18 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy19 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy20 = new HomingEnemy(50, 50, (long)1e7, 0, 0);
        homingEnemy11.setX(1);
        homingEnemy13.setX(1);
        homingEnemy15.setX(1);
        homingEnemy17.setX(1);
        homingEnemy19.setX(1);
        homingEnemy12.setX(getWidth());
        homingEnemy14.setX(getWidth());
        homingEnemy16.setX(getWidth());
        homingEnemy18.setX(getWidth());
        homingEnemy20.setX(getWidth());
        homingEnemy11.setY(-10);
        homingEnemy12.setY(-10);
        homingEnemy13.setY(-10);
        homingEnemy14.setY(-10);
        homingEnemy15.setY(-10);
        homingEnemy16.setY(-10);
        homingEnemy17.setY(-10);
        homingEnemy18.setY(-10);
        homingEnemy19.setY(-10);
        homingEnemy20.setY(-10);
        movementTimer6 = new MovementTimer6();
        movementTimer6.start();
        laserEnemy1 = new LaserEnemy(150, 150, (long)2e9, 0, 8);
        laserEnemy1.setX(getWidth()/2 + 150);
        laserEnemy1.setY(-150);
        laserEnemy2 = new LaserEnemy(150, 150, (long)2e9, 0, 8);
        laserEnemy2.setX(getWidth()/2 - 150);
        laserEnemy2.setY(-150);
        getChildren().addAll(laserEnemy1, laserEnemy2);
        movementTimer7 = new MovementTimer7();
        movementTimer7.start();
        laserEnemy1.startBulletTimer();
        laserEnemy2.startBulletTimer();
    }

    public Score getScore() {
        return score;
    }

    public void addEnemiesDestroyed(){
        enemiesDestroyed++;
    }

    @Override
    public void act(long now) {
        laserCountText.setText("Lasers left: " + String.valueOf(ship.getLaserCount()));
        for(Enemy e : getObjects(Enemy.class)){
            e.setShipXCoor(ship.getX());
            e.setShipYCoor(ship.getY());
        }
        if(currentWave == 1){
            if(enemiesDestroyed == 3 && !wave1Finished) {
                wave1Finished = true;
                wave2();
            }
        }
        else if(currentWave == 2){
            if(enemiesDestroyed == 12 && !wave2Finished){
                wave2Finished = true;
                wave3();
            }
        } else if(currentWave == 3){
            if(enemiesDestroyed == 12 && !wave3Finished){
                wave3Finished = true;
                for(Enemy e : getObjects(Enemy.class)){
                    if(e instanceof BasicEnemy){
                        e.stopBulletTimer();
                    } else if(e instanceof HomingEnemy)
                        e.stopBulletTimer();
                    else if(e instanceof LaserEnemy)
                        e.stopBulletTimer();
                }
                winScreenRootNode = new WinScreen(gameController, "You Win!", score.getValue());
                winScreenRootNode.setForLevel1(true);
                reset();
                winScreenRootNode.requestFocus();
                gameController.getGameRootNode().setCenter(winScreenRootNode);
                resetted = true;
            }
        }
        if(ship.isDead()){
            for(Enemy e : getObjects(Enemy.class)){
                if(e instanceof BasicEnemy){
                    e.stopBulletTimer();
                } else if(e instanceof HomingEnemy)
                    e.stopBulletTimer();
                else if(e instanceof LaserEnemy)
                    e.stopBulletTimer();
            }
            gameOverScreenRootNode = new GameOverScreen(gameController, "Game Over", score.getValue());
            gameOverScreenRootNode.setForLevel1(true);
            reset();
            gameController.getGameRootNode().setCenter(gameOverScreenRootNode);
            gameOverScreenRootNode.requestFocus();
            resetted = true;
        }
        setOnKeyPressed(event -> {
            addKeyDown(event.getCode());
            if(event.getCode() == KeyCode.SPACE){
                if(isPaused()) {
                    // do a little timer before resuming
                    unpause();
                    for(Enemy e : getObjects(Enemy.class)){
                        if(e instanceof BasicEnemy){
                            e.startBulletTimer();
                        } else if(e instanceof HomingEnemy)
                            e.startBulletTimer();
                        else if(e instanceof LaserEnemy)
                            e.startBulletTimer();
                    }
                } else {
                    // display pause menu screen
                    for(Enemy e : getObjects(Enemy.class)){
                        if(e instanceof BasicEnemy){
                            e.stopBulletTimer();
                        } else if(e instanceof HomingEnemy)
                            e.stopBulletTimer();
                        else if(e instanceof LaserEnemy)
                            e.stopBulletTimer();
                    }
                    pause();
                    gameController.getGameRootNode().setCenter(gameController.getPauseScreenRootNode());
                    gameController.getPauseScreenRootNode().setForLevel1(true);
                    gameController.getPauseScreenRootNode().setForLevel2(false);
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

    @Override
    public void reset() {
        while(!getChildren().isEmpty()) {
            getChildren().remove(0);
        }
        ship = new Ship((long)3e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight() * (double)5/6);
        score = new Score();
        score.setX(getWidth()/5);
        score.setY(getHeight() * (double)5/6);
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
        Image image = new Image(getClass().getClassLoader().getResource("resources/level1background.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
        wave1Finished = false;
        wave2Finished = false;
        wave3Finished = false;
        currentWave = 1;
        resetted = true;
        alreadyStopped1 = false;
        alreadyStopped2 = false;
        alreadyStopped3 = false;
        alreadyStopped4 = false;
        alreadyStopped5 = false;
        alreadyStopped6 = false;
        alreadyStopped7 = false;
        alreadyStopped8 = false;
        enemiesDestroyed = 0;
        try{
            movementTimer1.stop();
            movementTimer2.stop();
            movementTimer3.stop();
            movementTimer4.stop();
            movementTimer5.stop();
            movementTimer6.stop();
            movementTimer7.stop();
        } catch(NullPointerException e){
            e.printStackTrace();
        }
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
//                if(resetted && !alreadyStopped1) {
//                    stop();
//                    alreadyStopped1 = true;
//                }
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 40 && !isPaused()){
                previous = now;
                enemy1.moveDown();
                enemy2.moveDown();
                enemy3.moveDown();
                count++;
            }
            if(count >= 40)
                stop();
        }
    }

    private class MovementTimer2 extends AnimationTimer{
        private long previous = 0;
        private boolean first = true;
        private int count = 0;
        private long delay = (long)5e9;
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
//                if(resetted && !alreadyStopped2) {
//                    stop();
//                    alreadyStopped2 = true;
//                }
            if(isPaused())
                previous = now;
            if(now - previous >= delay && !first && !isPaused()){
                previous = now;
                delay = (long)1e7;
                enemy1.moveUp();
                enemy2.moveUp();
                enemy3.moveUp();
                count++;
            } else if(first){
                previous = now;
                first = false;
            }
            if(count >= 50) {
                enemy1.stopBulletTimer();
                enemy2.stopBulletTimer();
                enemy3.stopBulletTimer();
                stop();
                if(!wave1Finished) {
                    remove(enemy1);
                    remove(enemy2);
                    remove(enemy3);
                    wave2();
                    wave1Finished = true;
                }
            }
        }
    }

    private class MovementTimer3 extends AnimationTimer{
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
                    previous = now - (long)2e8;
                else if(third && !fourth)
                    previous = now - (long)4e8;
                else if(fourth)
                    previous = now - (long)6e8;
            }
            if(!first){
                getChildren().addAll(homingEnemy1, homingEnemy6);
                first = true;
                previous = now;
            } else if(now - previous >= 2e8 && now - previous < 4e8 && !second){
                getChildren().addAll(homingEnemy2, homingEnemy7);
                second = true;
            } else if(now - previous >= 4e8 && now - previous < 6e8 && !third){
                getChildren().addAll(homingEnemy3, homingEnemy8);
                third = true;
            } else if(now - previous >= 6e8 && now - previous < 8e8 && !fourth){
                getChildren().addAll(homingEnemy4, homingEnemy9);
                fourth = true;
            } else if(now - previous >= 8e8) {
                getChildren().addAll(homingEnemy5, homingEnemy10);
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
//                if(resetted && !alreadyStopped4) {
//                    stop();
//                    alreadyStopped4 = true;
//                }
            if(isPaused())
                previous = now;
            if(now - previous >= (long)1e7 && count < 60 && !isPaused()){
                previous = now;
                enemy4.moveDown();
                enemy5.moveDown();
                count++;
            }
            if(count >= 60)
                stop();
        }
    }

    private class MovementTimer5 extends AnimationTimer{
        private long previous = 0;
        private boolean first = true;
        private int count = 0;
        private long delay = (long)5e9;
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
//                if(resetted && !alreadyStopped5) {
//                    stop();
//                    alreadyStopped5 = true;
//                }
            if(isPaused())
                previous = now;
            if(now - previous >= delay && !first && !isPaused()){
                previous = now;
                delay = (long)1e7;
                enemy4.moveUp();
                enemy5.moveUp();
                count++;
            } else if(first){
                previous = now;
                first = false;
            }
            if(count >= 60) {
                enemy1.stopBulletTimer();
                enemy2.stopBulletTimer();
                stop();
                if(!wave2Finished) {
                    remove(enemy4);
                    remove(enemy5);
                    wave2Finished = true;
                    wave3();
                }
            }
        }
    }

    private class MovementTimer6 extends AnimationTimer{
        private boolean first, second, third, fourth, fifth, sixth, seventh, eighth, ninth = false;
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
//                if(resetted && !alreadyStopped6) {
//                    stop();
//                    alreadyStopped6 = true;
//                }
            if(isPaused()) {
                if(first && !second)
                    previous = now;
                else if(second && !third)
                    previous = now - (long)5e8;
                else if(third && !fourth)
                    previous = now - (long)1e9;
                else if(fourth && !fifth)
                    previous = now - (long)1.5e9;
                else if(fifth && !sixth)
                    previous = now - (long)2e9;
                else if(sixth && !seventh)
                    previous = now - (long)2.5e9;
                else if(seventh && !eighth)
                    previous = now - (long)3e9;
                else if(eighth && !ninth)
                    previous = now - (long)3.5e9;
                else if(ninth)
                    previous = now - (long)4e9;
            }
            if(!first){
                getChildren().addAll(homingEnemy11);
                homingEnemy11.startBulletTimer();
                first = true;
                previous = now;
            } else if(now - previous >= 5e8 && now - previous < 1e9 && !second){
                getChildren().addAll(homingEnemy12);
                homingEnemy12.startBulletTimer();
                second = true;
            } else if(now - previous >= 1e9 && now - previous < 1.5e9 && !third){
                getChildren().addAll(homingEnemy13);
                homingEnemy13.startBulletTimer();
                third = true;
            } else if(now - previous >= 1.5e9 && now - previous < 2e9 && !fourth){
                getChildren().addAll(homingEnemy14);
                homingEnemy14.startBulletTimer();
                fourth = true;
            } else if(now - previous >= 2e9 && now - previous < 2.5e9 && !fifth) {
                getChildren().addAll(homingEnemy15);
                homingEnemy15.startBulletTimer();
                fifth = true;
            } else if(now - previous >= 2.5e9 && now - previous < 3e9 && !sixth) {
                getChildren().addAll(homingEnemy16);
                homingEnemy16.startBulletTimer();
                sixth = true;
            } else if(now - previous >= 3e9 && now - previous < 3.5e9 && !seventh) {
                getChildren().addAll(homingEnemy17);
                homingEnemy17.startBulletTimer();
                seventh = true;
            } else if(now - previous >= 3.5e9 && now - previous < 4e9 && !eighth) {
                getChildren().addAll(homingEnemy18);
                homingEnemy18.startBulletTimer();
                eighth = true;
            } else if(now - previous >= 4e9 && now - previous < 4.5e9 && !ninth) {
                getChildren().addAll(homingEnemy19);
                homingEnemy19.startBulletTimer();
                ninth = true;
            } else if(now - previous >= 4.5e9) {
                getChildren().addAll(homingEnemy20);
                homingEnemy20.startBulletTimer();
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
            if(now - previous >= (long)1e7 && count < 25 && !isPaused()){
                previous = now;
                laserEnemy1.moveDown();
                laserEnemy2.moveDown();
                count++;
            }
            if(count >= 25)
                stop();
        }
    }
}
