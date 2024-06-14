/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laptopinventory.model.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ComponentDAO {
    
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

   

    public static List<Component> getAllComponents() {
        List<Component> componentList = new ArrayList<>();
        
        String sql = "SELECT * FROM components";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) 
       
       {
            
            while (rs.next()) {
                Component component = new Component(
                        
                    rs.getInt("component_id"),
                    rs.getString("component_name"),
                    rs.getString("component_type"),
                    rs.getDouble("sell_price")
                        
                );
               
                componentList.add(component);
            }
        } catch (SQLException e) {
        }
        return componentList;
    }
    
    public static List<Component> getComponentsSortedBy(String column, boolean ascending) {
        List<Component> componentList = new ArrayList<>();
        String direction = ascending ? "ASC" : "DESC";
        String sql = "SELECT * FROM components ORDER BY " + column + " " + direction;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Component component = new Component(
                        
                    rs.getInt("component_id"),
                    rs.getString("component_name"),
                    rs.getString("component_type"),
                    rs.getDouble("sell_price")
                        
                );
                componentList.add(component);
            }
        } catch (SQLException e) {
            System.out.print("failt to sort option");
        }
        
        return componentList;
    }
    
    public static List<Component> getComponentsTypedBy(String type){
        List<Component> componentList = new ArrayList<>();
        
        String sql = "SELECT * FROM components WHERE component_type = ? " ;
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, type); // Set the component type parameter
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Component component = new Component(
                        rs.getInt("component_id"),
                        rs.getString("component_name"),
                        rs.getString("component_type"),
                        rs.getDouble("sell_price")
                    );
                    componentList.add(component);
                }
            }
        } catch (SQLException e) {
            System.out.println("Failed to get components by type: " + e.getMessage());
        }
        return componentList;
    }
    
    public static List<Component> getComponentsSortedByTypeAndColumn(String type, String column, boolean ascending) {
    List<Component> componentList = new ArrayList<>();
    String direction = ascending ? "ASC" : "DESC";
    String sql = "SELECT * FROM components WHERE component_type = ? ORDER BY " + column + " " + direction;
    
    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, type); // Set the component type parameter
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Component component = new Component(
                    rs.getInt("component_id"),
                    rs.getString("component_name"),
                    rs.getString("component_type"),
                    rs.getDouble("sell_price")
                );
                componentList.add(component);
            }
        }
    } catch (SQLException e) {
        System.out.println("Failed to sort components by type and column: " + e.getMessage());
    }
    return componentList;
}

    
    static void addComponent(Component newComponent) {}

    static void updateComponent(Component selectedComponent) {}

    static void deleteComponent(Integer componentId) {}
    
    
}
