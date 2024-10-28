package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BudgetManager {
    private static final String URL = "jdbc:mysql://localhost:3306/finance_db";
    private static final String USER = "kyle1234";
    private static final String PASSWORD = "123456";

    // Budget data Inserting Method
    public void insertBudget(int userId, String category, double amount, String startDate, String endDate) {
        String sql = "INSERT INTO Budget (user_id, category, amount, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, category);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, startDate);
            pstmt.setString(5, endDate);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Budget has been set successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}