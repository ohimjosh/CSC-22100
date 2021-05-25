package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape {
    double x,y,radius, startAngle, centralAngle;
    public MyColor color;

    public MyArc(double x, double y, double radius, double startAngle, double centralAngle, MyColor color){
        super(x, y, color);
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.startAngle = startAngle;
        this.centralAngle = centralAngle;
        this.color = color;
    }

    public double getRadius() {
        return this.radius;
    }
    public double getStartAngle() {
        return this.getStartAngle();
    }
    public double getCentralAngle() {
        return this.getCentralAngle();
    }
    public double getMyArea() {
        return (Math.PI * (Math.pow(radius, 2)) * (centralAngle/360));
    }
    public MyRectangle getMyBoundingRectangle(){
        return new MyRectangle(super.getX(), super.getY(), this.radius, this.radius, this.color);
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        return false;
    }

    public String toString() {
        return "Radius:" + getRadius() + "\n Starting Angle: " + getStartAngle() +
                "\nCentral Angle " + getCentralAngle() + "\nArc Area:" + getMyArea();
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.color.getColor());
        gc.strokeArc(this.x, this.y, this.radius, this.radius,  this.startAngle, this.centralAngle, ArcType.ROUND);
        gc.fillArc(this.x, this.y, this.radius, this.radius, this.startAngle, this.centralAngle, ArcType.ROUND);
    }
}
