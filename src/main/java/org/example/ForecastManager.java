package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ForecastManager {
    private static final String URL = "jdbc:mysql://localhost:3306/financial_db";
    private static final String USER = "kyle1234";
    private static final String PASSWORD = "123456";

    public double calculateMonthlyAverage(int userId, int months) {
        String sql = "SELECT AVG(amount) AS avgExpense" +
                " FROM Expenses WHERE user_id = ? AND DATE >= DATE_SUB(NOW(), INTERVAL ? MONTH)";
        double avgExpense = 0.0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, months);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                avgExpense = rs.getDouble("avgExpense");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgExpense;
    }

    public double forecastNextMonthExpense(int userId){
        double monthlyAverage = calculateMonthlyAverage(userId, 3);
        return monthlyAverage;
    }
}
