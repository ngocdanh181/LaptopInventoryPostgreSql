/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laptopinventory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ADMIN
 */
public class DatabaseConnection {
    public static void main(String[] args) {
        // Định nghĩa thông tin kết nối
        String url = "jdbc:postgresql://localhost:5432/LaptopInventory";
        String user = "postgres";
        String password = "123456";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Tạo kết nối
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");

            // Tạo đối tượng Statement
            stmt = conn.createStatement();

            // Thực thi câu lệnh SQL
            String sql = "SELECT * FROM employees";
            rs = stmt.executeQuery(sql);

            // Xử lý kết quả
            while (rs.next()) {
                System.out.println("Employee ID: " + rs.getString("employee_id"));
                System.out.println("Employee Name: " + rs.getString("employee_name"));
                // Lấy các trường khác tương tự
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
               
            }
        }
    }
            
    
}
