package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyOval extends MyShape{
    public double x, y, height, width, xCenter, yCenter, majorAxis, minorAxis, maxX, maxY, minX, minY;
    private MyColor color;

    public MyOval (double x, double y, double w, double h, MyColor color) {
        super(x, y, color);
        this.width = w;
        this.height = h;
        this.color = color;
        setCenter(width, height);
        setAxes();
        this.maxX = this.getX() + width;
        this.maxY = this.getY() + height;
        this.minX = this.getX();
        this.minY = this.getY();
    }

    public double getArea(){
        return (Math.PI * width/2 * height/2);
    }
    public double getPerimeter(){
        return (2 * Math.PI * Math.sqrt((Math.pow(width/2, 2) + Math.pow(height/2, 2)) / 2));
    }
    public double getXCenter(){
        return xCenter;
    }
    public double getYCenter() {
        return yCenter;
    }
    public double getMyArea() {
        return getArea();
     }

    public void setAxes(){
        this.majorAxis = width;
        this.minorAxis = height;
    }
    private void setCenter(double width, double height) {
        this.xCenter = (width/2) + super.getX();
        this.yCenter = (height/2) + super.getY();
    }

    @Override
    public String toString(){
        return "Perimeter:" + getPerimeter() + "\nArea:" + getArea() + "\nX:" + getXCenter() + ", Y:" + getYCenter() +
                "\nMajor Axis: " + this.majorAxis + "\nMinor Axis: " + this.minorAxis;
    }
    @Override
    public boolean pointInMyShape(MyPoint p) {
                                                                //maxX                                                          //maxY
        return p.getXpoint() >= getX() && p.getXpoint() <= getX() + width && p.getYpoint() >= getY() && p.getYpoint() <= getY() + height;
    }
    @Override
    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(getX(), getY(),this.majorAxis, this.minorAxis,this.color);
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.setFill(this.color.getColor());
        gc.strokeOval(getX(), getY(), width, height);
        gc.fillOval(getX(), getY(), width, height);
        gc.strokeRect(getX(), getY(), width, height);

    }


}
