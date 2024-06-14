package com.mycompany.laptopinventory.model.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EmployeeDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

    
    public static List<Employee> getAllEmployees() {
    List<Employee> employeeList = new ArrayList<>();
    String sql = "SELECT * FROM employees";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        int index = 1;
        while (rs.next()) {
            Employee employee = new Employee(
                    index++,
                    rs.getString("employee_id"),
                    rs.getString("employee_name"),
                    rs.getString("address"),
                    rs.getString("phone_number")
            );
            employeeList.add(employee);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return employeeList;
}

public static void addEmployee(Employee newEmployee) {
    String sql = "INSERT INTO employees "
            + "(employee_id, employee_name, phone_number, address) VALUES (?, ?, ?, ?)";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, newEmployee.getEmployeeId());
        pstmt.setString(2, newEmployee.getEmployeeName());
        pstmt.setString(3, newEmployee.getPhoneNumber());
        pstmt.setString(4, newEmployee.getAddress());

        pstmt.executeUpdate();
    } catch (SQLException e) {
    }
}

public static void updateEmployee(Employee updatedEmployee) {
    String sql = "UPDATE employees SET "
            + "employee_name = ?, phone_number = ?, address = ? WHERE employee_id = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, updatedEmployee.getEmployeeName());
        pstmt.setString(2, updatedEmployee.getPhoneNumber());
        pstmt.setString(3, updatedEmployee.getAddress());
        pstmt.setString(4, updatedEmployee.getEmployeeId());

        pstmt.executeUpdate();
    } catch (SQLException e) {
        
    }
}

public static void deleteEmployee(String employeeId) {
    String sql = "DELETE FROM employees WHERE employee_id = ?";
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, employeeId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
}
