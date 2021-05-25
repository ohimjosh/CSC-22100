package sample;
import javafx.scene.canvas.GraphicsContext;


public class MyRectangle extends MyShape {
    public double height, width, maxX, maxY, minX, minY;
    private MyColor color;

    public MyRectangle(double x, double y, double w, double h, MyColor color) {
        super(x, y, color);
        this.width = w;
        this.height = h;
        this.color = color;
        this.maxX = this.getX() + width;
        this.maxY = this.getY() + height;
        this.minX = this.getX();
        this.minY = this.getY() ;
    }

    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
    public double getPerimeter(){
        return (2*width + 2*height);
    }
    public double getArea(){
        return (getWidth() * getHeight());
    }
    public double getMyArea(){
        return getArea();
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        return p.getXpoint() >= getX() && p.getXpoint() <= getX() + width && p.getYpoint() >= getY() && p.getYpoint() <= getY() + height;
    }

    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(this.getX(), this.getY(), this.width, this.height, this.color);
    }

    @Override
    public String toString() {
        return "Height:" + getHeight() + "\nWidth:" + getWidth() + "\nPerimeter:" + getPerimeter() + "\nArea:" + getArea();
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color.getColor());
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());

    }
}



