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
    @Override
    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(Borderdim.width,Borderdim.height);
        Scene scene = new Scene(root, Borderdim.width,Borderdim.height, MyColor.WHITE.getColor());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //shapes
        MyRectangle rec1 = new MyRectangle(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/2,Borderdim.height/2, MyColor.MAGENTA);
        rec1.draw(gc);

        MyOval o1 = new MyOval(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/2,Borderdim.height/2,MyColor.YELLOW);
        o1.draw(gc);

        MyRectangle rec2 = new MyRectangle(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/2.83,Borderdim.height/2.83, MyColor.BLACK);
        rec2.draw(gc);

        MyOval o2 = new MyOval(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/2.83,Borderdim.height/2.83,MyColor.GREEN);
        o2.draw(gc);

        MyRectangle rec3 = new MyRectangle(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/4.1,Borderdim.height/4.1, MyColor.WHITE);
        rec3.draw(gc);

        MyOval o3 = new MyOval(Borderdim.width/2 ,Borderdim.height/2,Borderdim.width/4.1,Borderdim.height/4.1,MyColor.BLUE);
        o3.draw(gc);

        //border
        MyLine top = new MyLine(0, 0, Borderdim.width, 0,MyColor.RED);
        top.draw(gc);

        MyLine bot = new MyLine(0,Borderdim.height,Borderdim.width,Borderdim.height, MyColor.RED);
        bot.draw(gc);

        MyLine left = new MyLine(0,0,0,Borderdim.height, MyColor.RED);
        left.draw(gc);

        MyLine right = new MyLine(Borderdim.width,Borderdim.height,Borderdim.width,0, MyColor.RED);
        right.draw(gc);

        MyLine dia = new MyLine(0,0,Borderdim.width,Borderdim.height, MyColor.RED);
        dia.draw(gc);

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
