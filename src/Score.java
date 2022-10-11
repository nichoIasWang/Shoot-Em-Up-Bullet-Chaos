import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Score extends Text {
    private int value;

    public Score(){
        value = 0;
        setFont(Font.font("impact", FontWeight.BOLD, FontPosture.REGULAR, 50));
        setFill(Color.WHITE);
        setStrokeWidth(2);
        setStroke(Color.BLACK);
        updateDisplay();
    }

    void updateDisplay(){
        setText(String.valueOf(value));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if(value < 0) this.value = 0;
        updateDisplay();
    }
}