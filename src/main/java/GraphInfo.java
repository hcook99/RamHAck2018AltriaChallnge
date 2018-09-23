import javafx.application.Application;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.stage.Stage;


public class GraphInfo extends Application {
    final static String extremlyPositive = "Extremely Positive";
    final static String positive = "Positive";
    final static String neutral = "Neutral";
    final static String negative = "Negative";
    final static String extremlyNegative = "Extremely Negative";

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Company Sentiment");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis,yAxis);
        bc.setTitle("Company Sentiment");
        xAxis.setLabel("View of Company");
        yAxis.setLabel("Number of People feel this way");
    }
}
