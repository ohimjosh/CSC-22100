package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;

public class MyLine extends MyShape {
    double x1, y1, x2, y2;
    private MyColor color;

    public MyLine(double xx1, double yy1, double xx2, double yy2, MyColor color) {
        super(xx1, yy1, color);
        this.x1 = xx1;
        this.y1 = yy1;
        this.x2 = xx2;
        this.y2 = yy2;
        this.color = color;
    }

    //get
    public double getX1() {
        return this.x1;
    }
    public double getY1() {
        return this.y1;
    }
    public double getX2() {
        return this.x2;
    }
    public double getY2() {
        return this.y2;
    }
    //set
    public void setX1(double x) {
        this.x1 = x;
    }
    public void setY1(double y) {
        this.y1 = y;
    }
    public void setX2(double x) {
        this.x2 = x;
    }
    public void setY2(double y) {
        this.y2 = y;
    }
    public double length() {
        return (Math.sqrt(Math.pow((this.x1 - super.getX()), 2) + Math.pow((this.y1 - super.getY()), 2)));
    }
    public double angleX() {
        return Math.toDegrees(Math.atan(((this.y1 - super.getY()) / (this.x1 - super.getX()))));
    }

    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1, this.color);
    }

    @Override
    public double getMyArea() {
        return 0;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        return false;
    }


    @Override
    public String toString() {
        return "Start:" + super.toString() + "\nEnd:(" + this.x1 + "," + this.y2 + ")." + "\nLength:" + length() + "\nAngle:" + angleX();
    }
    @Override
    public void draw (GraphicsContext gc){
        super.draw(gc);
        gc.strokeLine(this.x1, this.y1, this.x2, this.y2);
        gc.strokeRect(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
    }
}
