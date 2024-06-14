package com.mycompany.laptopinventory.model.invoice;

import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static List<OrderDetail> getOrderDetailsByInvoiceId(String invoiceId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = """
                    SELECT od.order_detail_id, od.invoice_id, 
                    CASE WHEN od.product_id IS NOT NULL THEN p.product_name
                    WHEN od.component_id IS NOT NULL THEN c.component_name
                    ELSE NULL 
                    END AS item_name,  od.quantity_sold, od.unit_price, od.discount 
                                             
                    FROM order_details od
                    LEFT JOIN products p ON od.product_id = p.product_id
                    LEFT JOIN components c ON od.component_id = c.component_id
                     WHERE od.invoice_id = ?;""";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderDetail orderDetail = new OrderDetail(
                            rs.getInt("order_detail_id"),
                            rs.getString("invoice_id"),
                            rs.getString("item_name"),
                            rs.getInt("quantity_sold"),
                            rs.getDouble("unit_price"),
                            rs.getDouble("discount")
                    );
                    orderDetails.add(orderDetail);
                }
            }
        } catch (SQLException e) {

        }

        return orderDetails;
    }

    public static String getCustomerNameByInvoiceId(String invoiceId) {
        String customerName = "";
        String sql = "SELECT c.customer_name "
                + "FROM invoices i "
                + "JOIN customers c ON i.customer_id = c.customer_id "
                + "WHERE i.invoice_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    customerName = rs.getString("customer_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerName;
    }

    public static String getEmployeeNameByInvoiceId(String invoiceId) {
        String employeeName = "";
        String sql = "SELECT e.employee_name "
                   + "FROM invoices i "
                   + "JOIN employees e ON i.employee_id = e.employee_id "
                   + "WHERE i.invoice_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, invoiceId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    employeeName = rs.getString("employee_name");
                }
            }
        } catch (SQLException e) {
        }

        return employeeName;
    }

  
    /*public static void addOrderDetail(OrderDetail detail) {
        String sql = "INSERT INTO order_details (order_detail_id, invoice_id, product_id, quantity_sold, unit_price, discount) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, detail.getOrderDetailId());
            pstmt.setString(2, detail.getInvoiceId());
            pstmt.setString(3, detail.getProductName());
            pstmt.setInt(4, detail.getQuantity());
            pstmt.setDouble(5, detail.getPrice());
            pstmt.setDouble(6, detail.getDiscount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }*/
}
