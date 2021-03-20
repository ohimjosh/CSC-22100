package sample;
import javafx.scene.canvas.GraphicsContext;

public class MyOval extends MyShape{
    private double height, width, x, y;
    private MyColor color;

    public MyOval (double xx, double yy, double w, double h, MyColor color) {
        super(xx, yy, color);
        this.x = xx;
        this.y = yy;
        this.width = w;
        this.height = h;
    }

    //get
    public double getHeight(){
        return this.height;
    }
    public double getWidth(){
        return this.width;
    }
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    //set
    public void setHeight(double h){
        this.height = h;
    }
    public void setWidth(double w){
        this.width = w;
    }
    public void setX(double xx){
        this.x = xx;
    }
    public void setY(double yy){
        this.y = yy;
    }


    @Override
    public String toString(){
        return "Height:" + this.height + "\nWidth:" + this.width + "\nCenter: (" + this.x + "," + this.y + ").";
    }
    @Override
    public void draw(GraphicsContext gc){
        double xx,yy;
        super.draw(gc);
        xx = this.getX() - (this.getWidth()/2);
        yy = this.getY() - (this.getHeight()/2);
        gc.strokeOval(xx,yy,this.getWidth(),this.getHeight());
        gc.fillOval(xx,yy,this.getWidth(),this.getHeight());
    }
}
