package sample;
import javafx.scene.canvas.GraphicsContext;


public class Slice extends MyArc{
    private double x, y ,radius,startAngle;
    private float angle;
    private MyColor color;
    public String key;
    public String value;

    public Slice(double x, double y, double radius, double startAngle, float angle, MyColor color){
        super(x, y, radius,startAngle, angle, color);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.startAngle = startAngle;
        this.angle = angle;
        this.color = color;
    }


    public double getRadius(){
        return radius;
    }
    public double getStartAngle() {
        return startAngle;
    }
    public float getAngle(){
        return angle;
    }
    public MyColor getColor() {
        return color;
    }
    public double area(){
        return 0.5 * angle * Math.pow(radius,2);
    }


    public String toString(){
        return "Slice:" + "\nX:" + this.x + ", Y:" + this.y + "\nRadius:" + radius +
                "\nStarting Angle, Angle: (" + startAngle  + "," + angle +")," + color.getColor();
    }

    public void draw(GraphicsContext gc) {
        MyArc a1 = new MyArc(this.x, this.y, this.radius, this.startAngle, this.centralAngle, this.color);
        a1.draw(gc);
    }
}
