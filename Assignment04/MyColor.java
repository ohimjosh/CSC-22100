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
    HOTPINK(255,105,180),
    CHOCOLATE(210, 105, 30),
    DARKSLATEGRAY(47,79,79),
    MIDNIGHTBLUE(25,25,112),
    INDIGO(75,0,130),
    AQUAMARINE(127,255,212),
    SPRINGGREEN(0,255,127),
    DARKOLIVEGREEN(85,107,47),
    ORANGERED(255,69,0),
    MAROON(128,0,0),
    ORANGE(255,165,0),
    OLIVE(128,128,0),
    DARKSEAGREEN(143,188,143);

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
