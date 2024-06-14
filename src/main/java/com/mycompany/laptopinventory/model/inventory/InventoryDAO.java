package com.mycompany.laptopinventory.model.inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class InventoryDAO {

    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

    public static ObservableList<Inventory> getAllInventories() {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inventories";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventory inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("entry_price"),
                    rs.getInt("component_id")
                );
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryList;
    }

    public static void addInventory(Inventory inventory) {
        String sql = "INSERT INTO inventories (product_id, quantity, entry_price, component_id) "
                + "VALUES (?, ?, ?, ?) RETURNING inventory_id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inventory.getProductId());
            stmt.setInt(2, inventory.getQuantity());
            stmt.setDouble(3, inventory.getEntryPrice());
            stmt.setObject(4, inventory.getComponentId(), java.sql.Types.INTEGER);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                inventory.setInventoryId(rs.getInt("inventory_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateInventory(Inventory inventory) {
        String sql = "UPDATE inventories SET product_id = ?, quantity = ?, entry_price = ?, component_id = ?"
                + " WHERE inventory_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inventory.getProductId());
            stmt.setInt(2, inventory.getQuantity());
            stmt.setDouble(3, inventory.getEntryPrice());
            stmt.setObject(4, inventory.getComponentId(), java.sql.Types.INTEGER);
            stmt.setInt(5, inventory.getInventoryId());

            stmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public static void deleteInventory(int inventoryId) {
        String sql = "DELETE FROM inventories WHERE inventory_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inventoryId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Inventory> getInventoriesByProductId(String productId) {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inventories "
                + "WHERE product_id =?";
        
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inventory inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("entry_price"),
                    rs.getInt("component_id")
                );
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
        }

        return inventoryList;
    }
    
    public static ObservableList<Inventory> getInventoriesByProductName(String productId) {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String sql = "SELECT i.* FROM inventories i "
                + "JOIN products p ON i.product_id = p.product_id "
                + "WHERE LOWER(p.product_name) LIKE ?";
        
        String productName = productId.toLowerCase()+ "%";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inventory inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("entry_price"),
                    rs.getInt("component_id")
                );
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
        }

        return inventoryList;
    }

    public static ObservableList<Inventory> getInventoriesByComponentId(int componentId) {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM inventories "
                + "WHERE component_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, componentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Inventory inventory = new Inventory(
                    rs.getInt("inventory_id"),
                    rs.getString("product_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("entry_price"),
                    rs.getInt("component_id")
                );
                inventoryList.add(inventory);
            }
        } catch (SQLException e) {
        }

        return inventoryList;
    }
}
