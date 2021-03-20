package sample;
import javafx.scene.paint.Color;

public enum MyColor {
    RED(255,0,0),
    BLUE(0,0,255),
    LIME(0,255,0),
    CYAN(0,255,255),
    GREEN(0,128,0),
    GREY(128,128,128),
    MAGENTA(255,0,255),
    PURPLE(128,0,128),
    VIOLET(148,0,211),
    YELLOW(255,255,0),
    WHITE(255,255,255),
    BLACK(0,0,0),
    HOTPINK(255,105,180);

    int Red;
    int Green;
    int Blue;

    MyColor(int Red, int Green, int Blue){
        this.Red = Red;
        this.Green = Green;
        this.Blue = Blue;
    }
    public Color getColor() {
        return Color.rgb(Red,Green,Blue);
    }
}
