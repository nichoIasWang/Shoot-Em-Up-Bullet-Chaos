import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameOverScreen extends Menu{
    private Button restart, exit, quit;
    private Score score;
    private boolean forLevel1, forLevel2 = false;

    public GameOverScreen(Game game, String titleName, int scoreValue) {
        super(game, titleName);
        DropShadow dropShadow = new DropShadow(5, 5, 5, Color.GRAY);
        GaussianBlur blur = new GaussianBlur(10);
        blur.setInput(dropShadow);
        super.title.setEffect(blur);
        super.title.setStrokeWidth(2);
        super.title.setFill(Color.RED);
        super.title.setStroke(Color.DARKRED);
        Text scoreText = new Text("Final Score:");
        scoreText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
        scoreText.setFill(Color.WHITE);
        scoreText.setStrokeWidth(2);
        scoreText.setStroke(Color.BLACK);
        restart = new Button("Try Again");
        restart.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        exit = new Button("Main Menu");
        exit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        quit = new Button("Quit");
        quit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        score = new Score();
        score.setValue(scoreValue);
        getChildren().addAll(scoreText, score, restart, exit, quit);
        restart.setOnMouseClicked(event -> {
            if(forLevel1){
                getGameController().getGameRootNode().setCenter(getGameController().getLevel1RootNode());
                getGameController().getLevel1RootNode().reset();
                getGameController().getLevel1RootNode().requestFocus();
                getGameController().getLevel1RootNode().unpause();
                getGameController().getLevel1RootNode().wave1();
            } else if(forLevel2){
                getGameController().getGameRootNode().setCenter(getGameController().getLevel2RootNode());
                getGameController().getLevel2RootNode().reset();
                getGameController().getLevel2RootNode().requestFocus();
                getGameController().getLevel2RootNode().unpause();
                getGameController().getLevel2RootNode().wave1();
            }
        });
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
