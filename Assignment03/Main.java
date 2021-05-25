package sample;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javax.swing.*;

class Borderdim{
    static double height = 700;
    static double width = 1000;
}

public class Main extends Application {

    public void start(Stage primaryStage){
        BorderPane root = new BorderPane();
        Canvas canvas = new Canvas(Borderdim.width,Borderdim.height);
        Scene scene = new Scene(root, Borderdim.width,Borderdim.height, MyColor.WHITE.getColor());
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //button
        int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Add Slices"));

        HistogramAlphaBet h1 = new HistogramAlphaBet();
        HistogramAlphaBet.MyPieChart p = new HistogramAlphaBet.MyPieChart(400, 10, 200, 550, n);
        p.draw(gc);

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
