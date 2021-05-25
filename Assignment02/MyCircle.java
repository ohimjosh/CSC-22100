package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyCircle extends MyOval{
    public double radius, maxX, maxY, minX, minY;
    private MyColor color;

    public MyCircle (double x, double y, double radius, MyColor color){
        super(x,y, radius, radius, color);
        this.radius = radius;
        this.color = color;
    }

    public double getArea(){
        return (Math.PI*Math.pow(this.radius, 2));
    }
    public double getPerimeter(){
        return (2*Math.pow(this.radius,2));
    }
    public double getRadius(){
        return this.radius;
    }

    @Override
    public String toString() {
        return "Center:" + super.toString() + "\nArea:(" + getArea() + "\nPerimeter:" + getPerimeter() + "\nRadius:" + getRadius();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color.getColor());
        gc.strokeOval(getX(), getY(), getRadius()*2, getRadius()*2);
        gc.fillOval(getX(), getY(), getRadius()*2, getRadius()*2);
        gc.strokeRect(getX(), getY(), getRadius()*2, getRadius()*2);
    }

    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(this.getX(), this.getY(), this.radius * 2, this.radius * 2, this.color);
    }

    @Override
    public double getMyArea() {
        return 0;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
                                                                         //maxX                                          //maxY
        return p.getXpoint() >= getX() && p.getXpoint() <= getX() + radius && p.getYpoint() >= getY() && p.getYpoint() <= getY() + radius;
    }
}
