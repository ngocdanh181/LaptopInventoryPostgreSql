package com.mycompany.laptopinventory.model.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

    public static List<Product> getAllProducts() {
        
        List<Product> productList = new ArrayList<>();
        
        String sql = "SELECT * FROM products";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) 
       
       {
            int index=1;
            while (rs.next()) {
                Product product = new Product(
                        
                        index++,
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getString("ram"),
                        rs.getString("chip"),
                        rs.getString("mainboard"),
                        rs.getString("vga"),
                        rs.getString("ssd"),
                        rs.getString("hdd"),
                        rs.getDouble("sell_price")
                );
               
                productList.add(product);
            }
        } catch (SQLException e) {
        }
        //System.out.print(productList);
        return productList;
    }

    public static void deleteProduct(String productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Add methods for adding and editing products here
    public static void addProduct(Product product) {
        String sql = "INSERT INTO products (product_id, product_name, ram, mainboard, chip, ssd, vga, sell_price,hdd) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getRam());
            pstmt.setString(4, product.getMain());
            pstmt.setString(5, product.getChip());
            pstmt.setString(6, product.getSsd());
            pstmt.setString(7, product.getVga());
            pstmt.setDouble(8, product.getSellPrice());
            pstmt.setString(9, product.getHdd());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failt to add");
        }
    }
    
    public static void updateProduct(Product product) {
        String sql = "UPDATE products "
                + "SET product_name = ?, ram = ?, mainboard = ?, chip = ?, ssd = ?, vga = ?,hdd =?, price = ?"
                + " WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER,PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getRam());
            pstmt.setString(3, product.getMain());
            pstmt.setString(4, product.getChip());
            pstmt.setString(5, product.getSsd());
            pstmt.setString(6, product.getVga());
            pstmt.setString(7, product.getHdd());
            pstmt.setDouble(8, product.getSellPrice());
            pstmt.setString(9, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }
    
    public static List<Product> getProductsSortedBy(String column, boolean ascending) {
        List<Product> productList = new ArrayList<>();
        String direction = ascending ? "ASC" : "DESC";
        String sql = "SELECT * FROM products ORDER BY " + column + " " + direction;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            int index = 1;

            while (rs.next()) {
                Product product = new Product(
                        index++,
                        rs.getString("product_id"),
                        rs.getString("product_name"),
                        rs.getString("ram"),
                        rs.getString("chip"),
                        rs.getString("mainboard"),
                        rs.getString("vga"),
                        rs.getString("ssd"),
                        rs.getString("hdd"),
                        rs.getDouble("sell_price")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
        }
        
        return productList;
    }
}
