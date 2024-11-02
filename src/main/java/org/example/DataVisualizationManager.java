package org.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import java.util.Map;
public class DataVisualizationManager extends Application {

    private ExpenseManager expenseManager = new ExpenseManager();

    public void start(Stage stage){
        stage.setTitle("Monthly Expenses");

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Month");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Amount ($)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Monthly Expense");

        Map<String, Double> monthlyExpenses = expenseManager.getMonthlyExpenses(1);

        XYChart.Series<String, Number> expenseSeries = new XYChart.Series<>();
        expenseSeries.setName("Expenses");

        for (Map.Entry<String, Double> entry: monthlyExpenses.entrySet()) {
            String month = entry.getKey();
            Double total = entry.getValue();
            expenseSeries.getData().add(new XYChart.Data<>(month, total));
        }

        barChart.getData().add(expenseSeries);

        Scene scene = new Scene(barChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
