package sample;
import javafx.scene.canvas.GraphicsContext;
import java.lang.Math;
import java.util.Arrays;



public class MyPolygon extends MyShape {
    public int r, N, maxX, maxY, minX, minY;
    private MyColor color;

    public MyPolygon(double x, double y, int r, int N, MyColor color) {
        super(x, y, color);
        this.r = r;
        this.N = N;
        this.color = color;
    }

    public double getArea() {
        return (int) (this.N * (Math.pow(this.r, 2)) * Math.tan((Math.PI / this.N)));
    }
    public double getPerimeter() {
        return (int) (this.N * getSide());
    }
    public double getAngle() {
        return (this.N - 2) * (180.0 / this.N);
    }
    public double getSide() {
        return (2 * this.r * Math.tan(Math.PI / this.N));
    }

    public MyRectangle getMyBoundingRectangle(){
        return new MyRectangle(this.minX, this.minY, this.maxX - this.minX, this.maxY - this.minY, this.color);
    }

    @Override
    public double getMyArea() {
        return 0;
    }

    @Override
    public boolean pointInMyShape(MyPoint p) {
        return p.getXpoint() >= getX() && p.getXpoint() <= maxX && p.getYpoint() >= getY() && p.getYpoint() <= maxY;
    }

    @Override
    public String toString() {
        return "Center:" + super.toString() + "\nArea:" + getArea() + "\nPerimeter:" + getPerimeter() + "\nAngle:" + getAngle() + "\nSides:" + getSide();
    }

    @Override
    public void draw(GraphicsContext gc) {

        double[] x_cords = new double[this.N];
        double[] y_cords = new double[this.N];

        // find the vertices
        for (int j = 0; j < this.N; j++) {
            x_cords[j] = getX() + this.r * Math.sin(2 * Math.PI * j / this.N);
            y_cords[j] = getY() + this.r * Math.cos(2 * Math.PI * j / this.N);
        }

        //convert double to int
        final int[] ix_cords = new int[x_cords.length];
        for (int i = 0; i<x_cords.length; ++i)
            ix_cords[i] = (int) x_cords[i];

        //find min max x values
        this.minX = Arrays.stream(ix_cords).min().getAsInt();
        this.maxX = Arrays.stream(ix_cords).max().getAsInt();

        //convert double to int
        final int[] iy_cords = new int[y_cords.length];
        for (int i = 0; i<y_cords.length; ++i)
            iy_cords[i] = (int) y_cords[i];

        //find min max y values
        this.minY = Arrays.stream(iy_cords).min().getAsInt();
        this.maxY = Arrays.stream(iy_cords).max().getAsInt();

        gc.setLineWidth(2);
        gc.setFill(color.getColor());
        gc.setStroke(MyColor.BLACK.getColor());
        gc.strokePolygon(x_cords, y_cords, this.N);
        gc.fillPolygon(x_cords, y_cords, this.N);
        gc.strokeRect(this.minX, this.minY, this.maxX - this.minX, this.maxY - this.minY);

    }
}