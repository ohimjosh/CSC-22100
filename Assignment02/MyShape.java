package sample;
import javafx.scene.canvas.GraphicsContext;

abstract class MyShape implements MyShapeInterface{
    private MyPoint point;
    private MyColor color;

    public MyShape(double x, double y, MyColor color){
        this.point = new MyPoint(x,y);
        this.color = color;
    }

    //get
    public double getX(){
        return this.point.getXpoint();
    }
    public double getY(){
        return this.point.getYpoint();
    }

    // set
    public void setX(double x){
        this.point.setXpoint(x);
    }
    public void setY(double y){
        this.point.setYpoint(y);
    }

    public String toString(){
        return "X:" + getX() + "\nY:" + getY();
    }

    public void draw (GraphicsContext gc){
        gc.setStroke(this.color.getColor());
        gc.setFill(this.color.getColor());
    }
}

