package com.mycompany.laptopinventory.model.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

    public static List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) 
       
       {
            int index=1;
            while (rs.next()) {
                Customer customer = new Customer(
                        
                        index++,
                        rs.getString("customer_id"),
                        rs.getString("customer_type"),
                        rs.getString("customer_name"),
                        rs.getString("phone_number"),
                        rs.getString("address")
                        
                );
                System.out.print(customer);
                customerList.add(customer);
            }
        } catch (SQLException e) {
        }
        
        System.out.print(customerList);

        return customerList;
        
    }

    public static void addCustomer(Customer newCustomer) {
        String sql = "INSERT INTO customers "
                + "(customer_id, customer_name, customer_type, phone_number, address) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newCustomer.getCustomerId());
            pstmt.setString(2, newCustomer.getCustomerName());
            pstmt.setString(3, newCustomer.getCustomerType());
            pstmt.setString(4, newCustomer.getPhoneNumber());
            pstmt.setString(5, newCustomer.getAddress());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail to add");
        }
    }

    public static void updateCustomer(Customer newCustomer) {
        System.out.print(newCustomer.getCustomerId());
        String sql = "UPDATE customers "
                + "SET  customer_name = ? , customer_type = ? , phone_number= ? , address = ?"
                + "WHERE customer_id = ? ";
        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newCustomer.getCustomerName());
            pstmt.setString(2, newCustomer.getCustomerType());
            pstmt.setString(3, newCustomer.getPhoneNumber());
            pstmt.setString(4, newCustomer.getAddress());
            pstmt.setString(5, newCustomer.getCustomerId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail to update");
        }
        
    }

    public static void deleteProduct(String customerId) {
      String sql ="Delete from customers where customer_id = ?";  
      try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);
            stmt.executeUpdate();
            
            
        } catch (SQLException e) {
        }
      
    }
    
    
}
