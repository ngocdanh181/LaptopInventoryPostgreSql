package com.mycompany.laptopinventory.model.report;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String  PASSWORD = "123456";

    public static List<Report> getRevenueReport(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT DATE_TRUNC('day', i.creation_date) AS report_date, " +
                       "SUM(od.quantity_sold * od.unit_price * (1 - od.discount / 100)) AS total_revenue " +
                       "FROM invoices i " +
                       "JOIN order_details od ON i.invoice_id = od.invoice_id " +
                       "WHERE i.creation_date BETWEEN ? AND ? " +
                       "GROUP BY DATE_TRUNC('day', i.creation_date)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Report report = new Report();
                    report.setReportDate(rs.getDate("report_date").toLocalDate());
                    report.setTotalRevenue(rs.getDouble("total_revenue"));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public static List<Report> getProfitReport(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT DATE_TRUNC('day', i.creation_date) AS report_date, " +
                       "SUM(od.quantity_sold * od.unit_price * (1 - od.discount / 100)- it.entry_price * od.quantity_sold) AS total_revenue " +
                       "FROM invoices i " +
                       "JOIN order_details od ON i.invoice_id = od.invoice_id" +
                       "JOIN inventories it ON it.product_id= od.product_id " +
                       "WHERE i.creation_date BETWEEN ? AND ? " +
                       "GROUP BY DATE_TRUNC('day', i.creation_date)";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Report report = new Report();
                    report.setReportDate(rs.getDate("report_date").toLocalDate());
                    report.setTotalRevenue(rs.getDouble("total_revenue")); // Assuming total_profit represents profit
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public static List<Report> getBestSellingProduct(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT p.product_id, SUM(od.quantity_sold) AS total_quantity " +
                       "FROM order_details od " +
                       "JOIN products p ON od.product_id = p.product_id " +
                       "JOIN invoices i ON od.invoice_id = i.invoice_id " +
                       "WHERE i.creation_date BETWEEN ? AND ? " +
                       "GROUP BY p.product_id " +
                       "ORDER BY total_quantity DESC";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Report report = new Report();
                    report.setBestSellingProductId(rs.getString("product_id"));
                    report.setBestSellingQuantity(rs.getInt("total_quantity"));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public static List<Report> getBestSellingEmployee(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT e.employee_name, SUM(od.quantity_sold) AS total_quantity, SUM(i.total_value) AS total " +
                       "FROM order_details od " + 
                       "JOIN invoices i ON od.invoice_id = i.invoice_id " +
                       "JOIN employees e ON i.employee_id = e.employee_id " +
                       "WHERE i.creation_date BETWEEN ? AND ? " +
                       "GROUP BY e.employee_name " +
                       "ORDER BY total_quantity DESC";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Report report = new Report();
                    report.setSellingEmployee(rs.getString("employee_name"));
                    report.setBestSellingQuantity(rs.getInt("total_quantity"));
                    report.setTotalRevenue(rs.getDouble(""));
                    reports.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
