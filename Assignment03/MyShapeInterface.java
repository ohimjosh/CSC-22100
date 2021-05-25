package sample;

public interface MyShapeInterface {
    MyRectangle getMyBoundingRectangle();
    public double getMyArea();
    abstract boolean pointInMyShape(MyPoint p);

    static public String intersectMyShapes(MyShape one, MyShape two){
        MyRectangle r1 = one.getMyBoundingRectangle();
        MyRectangle r2 = two.getMyBoundingRectangle();

        //x1,y1 = (r1.minX, r1.maxY)
        //x2,y2 = (r1.maxX, r1.minY)
        //x3,y3 = (r2.minX, r2.maxY)
        //x4,y4 = (r2.maxX, r2.minY)
        return "Shapes one and two do not overlap " + ( r2.minX > r1.maxX || r2.maxY < r1.minY || r1.minX > r2.maxX || r1.maxY < r2.minY );
    }
}
