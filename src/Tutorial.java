import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;

public class Tutorial extends World implements Resettable{
    private Ship ship;
    private boolean started = false;
    private Button exit;
    private Game gameController;

    public Tutorial(Game gameController){
        super();
        this.gameController = gameController;
        setWidth(800);
        setHeight(1000);
        ship = new Ship((long)3e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight()/2);
        exit = new Button("Quit Tutorial");
        exit.setLayoutX(getWidth()/4);
        exit.setLayoutY(getHeight()* (double)5/6);
        getChildren().addAll(ship, exit);
        pause();
        section1();
        Image image = new Image(getClass().getClassLoader().getResource("resources/tutorialbackground.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
    }

    @Override
    public void act(long now) {
        exit.setOnMouseClicked(event -> {
            gameController.getGameRootNode().setCenter(gameController.getMainMenuRootNode());
            reset();
        });
        setOnKeyPressed(event -> {
            addKeyDown(event.getCode());
            if(event.getCode() == KeyCode.SPACE){
                if(isPaused() && started)
                    unpause();
                else
                    pause();
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

    public void section1(){
        Label textBlock1 = new Label("This is your ship");
        textBlock1.setTextFill(Color.WHITE);
        textBlock1.setLayoutX(getWidth()/2);
        textBlock1.setLayoutY(getHeight()/2 - getHeight()/25);
        Label textBlock2 = new Label("This ship follows where your mouse cursor is at. You can tap or hold\n" +
                "'Z' to fire bullets from it. You can press 'X' to fire a powerful laser.\n" +
                "The goal of this game is the survive waves of different enemies that attack you by firing their\n" +
                "own bullets, lasers, or home in on you. Try to avoid getting hit by projectiles or enemies while\n" +
                "also trying to destroy as many enemies possible. Firing a laser will remove all bullets touching it\n" +
                "as well as deal massive damage to enemies. You only have limited amount of lasers per level so use\n" +
                "them wisely. If you get hit and you still have lasers left, you will automatically fire one laser,\n" +
                "and will use up one of the lasers you can fire. Pressing the space bar will pause or resume the level.\n" +
                "After clicking the resume button on the pause screen, you will need to press the space bar to\n" +
                "continue the level.");
        textBlock2.setTextFill(Color.WHITE);
        textBlock2.setLayoutX(getWidth()/4);
        textBlock2.setLayoutY(-50 + getHeight() * 2/3);
        Button test = new Button("Test Controls");
        test.setLayoutX(getWidth()/2);
        test.setLayoutY(getHeight() * (double)5/6);
        test.setOnMouseClicked(event -> {
            started = true;
            unpause();
            requestFocus();
        });
        getChildren().addAll(textBlock1, textBlock2, test);
    }

    @Override
    public void reset() {
        while(!getChildren().isEmpty()) {
            getChildren().remove(0);
        }
        ship = new Ship((long)3e8);
        ship.setX(getWidth()/2);
        ship.setY(getHeight()/2);
        exit = new Button("Quit Tutorial");
        exit.setLayoutX(getWidth()/4);
        exit.setLayoutY(getHeight()* (double)5/6);
        getChildren().addAll(ship, exit);
        started = false;
        pause();
        section1();
    }
}
