import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Game extends Application {
    private MainMenu mainMenuRootNode;
    private LevelSelectionMenu levelSelectionMenuRootNode;
    private BorderPane GameRootNode;
    private Tutorial tutorialRootNode;
    private Level1 level1RootNode;
    private PauseScreen pauseScreenRootNode;
    private Level2 level2RootNode;
    private MediaPlayer menuMusic;

    @Override
    public void start(Stage stage) throws Exception{
        stage.setHeight(1000);
        stage.setWidth(800);
        stage.setTitle("Shoot 'Em Up: Bullet Chaos");
        mainMenuRootNode = new MainMenu(this, "Shoot 'Em Up: Bullet Chaos");
        GameRootNode = new BorderPane(mainMenuRootNode);
        GameRootNode.setCenter(mainMenuRootNode);
        Scene GameScene = new Scene(GameRootNode);
        levelSelectionMenuRootNode = new LevelSelectionMenu(this, "Select Level");
        tutorialRootNode = new Tutorial(this);
        level1RootNode = new Level1(this);
        pauseScreenRootNode = new PauseScreen(this, "Paused");
        level2RootNode = new Level2(this);
        menuMusic = new MediaPlayer(new Media(getClass().getClassLoader().getResource("resources/menumusic.mp3").toURI().toString()));
        menuMusic.setCycleCount(Integer.MAX_VALUE);
        menuMusic.play();
        stage.setScene(GameScene);
        stage.setResizable(false);
        stage.show();
    }

    public BorderPane getGameRootNode(){
        return GameRootNode;
    }

    public LevelSelectionMenu getLevelSelectionMenuRootNode(){
        return levelSelectionMenuRootNode;
    }

    public MainMenu getMainMenuRootNode() {
        return mainMenuRootNode;
    }

    public Tutorial getTutorialRootNode() {
        return tutorialRootNode;
    }

    public Level1 getLevel1RootNode() {
        return level1RootNode;
    }

    public PauseScreen getPauseScreenRootNode() {
        return pauseScreenRootNode;
    }

    public Level2 getLevel2RootNode() {
        return level2RootNode;
    }

    public MediaPlayer getMenuMusic() {
        return menuMusic;
    }

    public void exit() {
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
