import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MainMenu extends Menu {
    private Button levels, tutorial, highScores, quit;

    public MainMenu(Game game, String title){
        super(game, title);
        super.title.setFill(Color.BLUE);
        super.title.setStrokeWidth(2);
        super.title.setStroke(Color.PURPLE);
        Reflection reflection = new Reflection();
        reflection.setFraction(0.7);
        Glow glow = new Glow(0.8);
        glow.setInput(reflection);
        super.title.setEffect(glow);
        levels = new Button("Levels");
        levels.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        tutorial = new Button("Tutorial");
        tutorial.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        quit = new Button("Quit");
        quit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        getChildren().addAll(levels, tutorial, quit);
        levels.setOnMouseClicked(event -> getGameController().getGameRootNode().setCenter(getGameController().getLevelSelectionMenuRootNode()));
        tutorial.setOnMouseClicked(event -> {
            getGameController().getGameRootNode().setCenter(getGameController().getTutorialRootNode());
            getGameController().getTutorialRootNode().start();
            getGameController().getTutorialRootNode().requestFocus();
        });
        quit.setOnMouseClicked(event -> { getGameController().exit(); });
    }
}
