import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LevelSelectionMenu extends Menu {
    private Button level1, level2, level3, back;

    public LevelSelectionMenu(Game game, String title){
        super(game, title);
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setOffsetX(4);
        innerShadow.setOffsetY(4);
        innerShadow.setColor(Color.LIGHTBLUE);
        super.title.setEffect(innerShadow);
        super.title.setFill(Color.BLUE);
        level1 = new Button("Level 1");
        level1.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        level2 = new Button("Level 2");
        level2.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        back = new Button("Menu");
        back.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 20));
        getChildren().addAll(level1,level2, back);
        level1.setOnMouseClicked(event -> {
            getGameController().getGameRootNode().setCenter(getGameController().getLevel1RootNode());
            getGameController().getLevel1RootNode().start();
            getGameController().getLevel1RootNode().requestFocus();
            getGameController().getLevel1RootNode().wave1();
            getGameController().getMenuMusic().stop();
            getGameController().getLevel1RootNode().getLevelMusic().play();
        });
        level2.setOnMouseClicked(event -> {
            getGameController().getGameRootNode().setCenter(getGameController().getLevel2RootNode());
            getGameController().getLevel2RootNode().start();
            getGameController().getLevel2RootNode().requestFocus();
            getGameController().getLevel2RootNode().wave1();
            getGameController().getMenuMusic().stop();
            getGameController().getLevel2RootNode().getLevelMusic().play();
        });
        back.setOnMouseClicked(event -> getGameController().getGameRootNode().setCenter(getGameController().getMainMenuRootNode()));
    }
}
