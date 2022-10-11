import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WinScreen extends Menu {
    private Button exit, quit;
    private Score score;
    private boolean forLevel1, forLevel2 = false;

    public WinScreen(Game game, String titleName, int scoreValue) {
        super(game, titleName);
        super.title.setFill(Color.YELLOW);
        super.title.setStrokeWidth(2);
        super.title.setStroke(Color.LIGHTGOLDENRODYELLOW);
        Light.Distant light = new Light.Distant();
        light.setAzimuth(-135.0);
        light.setColor(Color.LIGHTYELLOW);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);
        Glow glow = new Glow(0.8);
        glow.setInput(lighting);
        super.title.setEffect(glow);
        Text scoreText = new Text("Final Score:");
        scoreText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
        scoreText.setFill(Color.WHITE);
        scoreText.setStrokeWidth(2);
        scoreText.setStroke(Color.BLACK);
        score = new Score();
        score.setValue(scoreValue);
        exit = new Button("Main Menu");
        exit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        quit = new Button("Quit");
        quit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        getChildren().addAll(scoreText, score, exit, quit);
        Image image = new Image(getClass().getClassLoader().getResource("resources/winbackground.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
        exit.setOnMouseClicked(event -> {
            if(forLevel1){
                getGameController().getGameRootNode().setCenter(getGameController().getMainMenuRootNode());
                getGameController().getLevel1RootNode().unpause();
                getGameController().getLevel1RootNode().reset();
                getGameController().getLevel1RootNode().getLevelMusic().stop();
                getGameController().getMenuMusic().play();
            } else if(forLevel2){
                getGameController().getGameRootNode().setCenter(getGameController().getMainMenuRootNode());
                getGameController().getLevel2RootNode().unpause();
                getGameController().getLevel2RootNode().reset();
                getGameController().getLevel2RootNode().getLevelMusic().stop();
                getGameController().getMenuMusic().play();
            }
        });
        quit.setOnMouseClicked(event -> getGameController().exit());
    }

    public void setForLevel1(boolean forLevel1) {
        this.forLevel1 = forLevel1;
    }

    public void setForLevel2(boolean forLevel2) {
        this.forLevel2 = forLevel2;
    }
}
