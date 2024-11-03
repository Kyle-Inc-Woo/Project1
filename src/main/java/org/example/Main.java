package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        BudgetManager budgetManager = new BudgetManager();
        ExpenseManager expenseManager = new ExpenseManager();
        ForecastManager forecastManager = new ForecastManager();
        EconomicIndicatorManager economicIndicatorManager = new EconomicIndicatorManager();

        budgetManager.insertBudget(1, "Monthly Budget",
                700.0, "2024-11-01", "2024-11-30");

        expenseManager.addExpense(1, "Rent", 550.0, "2024-11-01");
        expenseManager.addExpense(1, "Food", 15.0, "2024-11-02");

        System.out.println("Expense Records: ");
        expenseManager.viewExpenses(1).forEach(System.out::println);

        double forecast = forecastManager.forecastNextMonthExpense(1);
        System.out.println("Forecasted Expense for next month:  $" + forecast);

        double adjustedBudget = economicIndicatorManager.adjustBudgetWithInflation(700.0);
        System.out.println("Adjusted Budget with Inflation:  $" + adjustedBudget);

        DataVisualizationManager.launch(DataVisualizationManager.class);
    }
}