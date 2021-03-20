package sample;
import javafx.scene.canvas.GraphicsContext;

public class MyShape{
    private double x, y;
    private MyColor color;

    public MyShape(double x, double y, MyColor color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    //get
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    // set
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    //overridden methods
    public int area(){
        return 0;
    }
    public int perimeter(){
        return 0;
    }

    public String toString(){
        return "X:" + x + "\nY:" + y;
    }

    public void draw (GraphicsContext gc){
        gc.setStroke(this.color.getColor());
        gc.setFill(this.color.getColor());
    }
}
