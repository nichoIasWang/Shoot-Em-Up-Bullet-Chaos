import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PauseScreen extends Menu {
    private Button resume, restart, exit, quit;
    private boolean forLevel1, forLevel2 = false;

    public PauseScreen(Game game, String titleName) {
        super(game, titleName);
        resume = new Button("Resume");
        resume.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        restart = new Button("Restart");
        restart.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        exit = new Button("Main Menu");
        exit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        quit = new Button("Quit");
        quit.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        getChildren().addAll(resume, restart, exit, quit);
        resume.setOnMouseClicked(event -> {
            if(forLevel1){
                getGameController().getGameRootNode().setCenter(getGameController().getLevel1RootNode());
                getGameController().getLevel1RootNode().requestFocus();
            } else if(forLevel2){
                getGameController().getGameRootNode().setCenter(getGameController().getLevel2RootNode());
                getGameController().getLevel2RootNode().requestFocus();
            }
        });
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
                getGameController().getLevel2RootNode().getBossMusic().stop();
                getGameController().getLevel2RootNode().getLevelMusic().play();
            }
        });
        exit.setOnMouseClicked(event -> {
            if(forLevel1){
                getGameController().getGameRootNode().setCenter(getGameController().getMainMenuRootNode());
                getGameController().getLevel1RootNode().unpause();
                getGameController().getLevel1RootNode().getLevelMusic().stop();
                getGameController().getLevel1RootNode().reset();
                getGameController().getMenuMusic().play();
            } else if(forLevel2){
                getGameController().getGameRootNode().setCenter(getGameController().getMainMenuRootNode());
                getGameController().getLevel2RootNode().unpause();
                getGameController().getLevel2RootNode().getLevelMusic().stop();
                getGameController().getLevel2RootNode().getBossMusic().stop();
                getGameController().getLevel2RootNode().reset();
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
