package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

class Borderdim{
    static double height = 600;
    static double width = 800;
}

public class Main extends Application {

    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(Borderdim.width,Borderdim.height);
        Scene scene = new Scene(root, Borderdim.width,Borderdim.height, MyColor.WHITE.getColor());
        GraphicsContext gc = canvas.getGraphicsContext2D();


        MyPoint p1= new MyPoint(500,600);
        //shapes
        MyPolygon r1 = new MyPolygon(500,300,50, 6,MyColor.BLUE);
        r1.draw(gc);
        r1.pointInMyShape(p1);
        System.out.println("Point in Shape:" + r1.pointInMyShape(p1));

        MyPolygon r2 = new MyPolygon(500,350,50, 6,MyColor.RED);
        r2.draw(gc);

        MyShapeInterface.intersectMyShapes(r1,r2);
        System.out.println(MyShapeInterface.intersectMyShapes(r1,r2));

        MyCircle c1 = new MyCircle(200,100,20,MyColor.BLUE);
        c1.draw(gc);

        MyOval o1 = new MyOval(Borderdim.width/4,Borderdim.height/4, Borderdim.width/4, Borderdim.height/4, MyColor.GREEN);
        o1.draw(gc);

        MyRectangle r = new MyRectangle(Borderdim.width/9 ,Borderdim.height/6,Borderdim.width/9,Borderdim.height/6, MyColor.YELLOW);
        r.draw(gc);


        //border
        MyLine top = new MyLine(0, 0, Borderdim.width, 0,MyColor.RED);
        top.draw(gc);

        MyLine bot = new MyLine(0,Borderdim.height,Borderdim.width,Borderdim.height, MyColor.RED);
        bot.draw(gc);

        MyLine left = new MyLine(0,0,0,Borderdim.height, MyColor.RED);
        left.draw(gc);

        MyLine right = new MyLine(Borderdim.width,Borderdim.height,Borderdim.width,0, MyColor.RED);
        right.draw(gc);

        //MyLine dia = new MyLine(0,0,Borderdim.width,Borderdim.height, MyColor.RED);
        //dia.draw(gc);


        //show shape
        root.getChildren().add(canvas);
        primaryStage.setTitle("MyShape");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
