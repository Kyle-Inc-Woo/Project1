package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/financial_db";
    private static final String USER = "kyle1234";
    private static final String PASSWORD = "123456";

    //1. Add expense record
    public void addExpense(int userId, String category, double amount, String date){
        String sql = "INSERT INTO expenses (user_id, category, amount, date) VAlUES(?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, userId);
            pstmt.setString(2, category);
            pstmt.setDouble(3, amount);
            pstmt.setString(4, date);

            int rowsInserted = pstmt.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("Expense has been added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2. View all expenses for a specific user
    public List<String> viewExpenses(int userId){
        String sql = "SELECT * FROM expenses WHERE user_id = ?";
        List<String> exepenseList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()){
                String expenseRecord = "Date: "+rs.getString("date")+
                                       ", Category: "+rs.getString("category")+
                                       ", Amount:  $"+rs.getDouble("amount");
                exepenseList.add(expenseRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exepenseList;
    }

    //3. Get total expenses within a specific date range
    public double getTotalExpenses(int userId, String startDate, String endDate){
        String sql = "SELECT SUM(amount) AS total FROM Expenses WHERE user_id = ? AND start_date BETWEEN ? AND ?";
        double total = 0.0;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, userId);
            pstmt.setString(2, startDate);
            pstmt.setString(3, endDate);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                total = rs.getDouble("total");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

}
