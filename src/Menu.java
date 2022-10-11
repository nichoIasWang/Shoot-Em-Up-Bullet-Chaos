import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class Menu extends VBox {
    private Game GameController;
    protected Text title;

    public Menu(Game game, String titleName){
        super(10);
        setPadding(new Insets(100, 0, 100, 0));
        setSpacing(100);
        this.GameController = game;
        setAlignment(Pos.TOP_CENTER);
        title = new Text(titleName);
        title.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
        title.setFill(Color.WHITE);
        title.setStrokeWidth(2);
        title.setStroke(Color.BLACK);
        getChildren().add(title);
        Image image = new Image(getClass().getClassLoader().getResource("resources/menubackground.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        setBackground(new Background(backgroundImage));
    }

    public Game getGameController(){
        return GameController;
    }
}
