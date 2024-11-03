package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;


public class EconomicIndicatorManager {
    private static final String URL = "jdbc:mysql://localhost:3306/finance_db";
    private static final String USER = "kyle1234";
    private static final String PASSWORD = "123456";

    public void insertEconomicIndicator(String indicatorName, double value, String date){
        String sql = "INSERT INTO EconomicIndicator (indicatorName, value, date) VALUES (?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, indicatorName);
            pstmt.setDouble(2, value);
            pstmt.setString(3, date);

            pstmt.executeQuery();
            System.out.println("Economic Indicator added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getLastestIndicator(String indicatorName){
        String sql = "SELECT value FROM EconomicIndicator WHERE indicatorName = ? ORDER BY date DESC LIMIT 1";
        double value = 0.0;

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, indicatorName);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                value = rs.getDouble("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }

    public double adjustBudgetWithInflation (double budget){
        double inflationRate = getLastestIndicator("Inflation Rate");
        return budget *(1 + inflationRate/ 100);
    }
}
