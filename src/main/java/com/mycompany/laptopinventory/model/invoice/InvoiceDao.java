package com.mycompany.laptopinventory.model.invoice;

import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/LaptopInventory";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        String sql = "SELECT * FROM invoices";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            int index = 1;
            while (rs.next()) {
                Invoice invoice = new Invoice(
                        index++,
                        rs.getString("invoice_id"),
                        rs.getString("customer_id"),
                        rs.getString("employee_id"),
                        rs.getDate("creation_date").toLocalDate(),
                        rs.getDouble("total_value")
                );
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public static void addInvoice(Invoice invoice) {
        String insertInvoiceSQL = "INSERT INTO invoices (invoice_id, customer_id, employee_id, creation_date) VALUES (?, ?, ?, ?)";
        String insertOrderDetailSQL = "INSERT INTO order_details (invoice_id, product_id, quantity_sold, unit_price, discount) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement invoiceStmt = conn.prepareStatement(insertInvoiceSQL);
             PreparedStatement orderDetailStmt = conn.prepareStatement(insertOrderDetailSQL, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false);

            invoiceStmt.setString(1, invoice.getInvoiceId());
            invoiceStmt.setString(2, invoice.getCustomerId());
            invoiceStmt.setString(3, invoice.getEmployeeId());
            invoiceStmt.setDate(4, java.sql.Date.valueOf(invoice.getCreationDate()));
            invoiceStmt.executeUpdate();

            for (OrderDetail orderDetail : invoice.getOrderDetails()) {
                  // Let the trigger handle the auto-increment of order_detail_id
                orderDetailStmt.setString(1, invoice.getInvoiceId());
                orderDetailStmt.setString(2, orderDetail.getProductName());
                orderDetailStmt.setInt(3, orderDetail.getQuantity());
                orderDetailStmt.setDouble(4, orderDetail.getPrice());
                orderDetailStmt.setDouble(5, orderDetail.getDiscount());
                orderDetailStmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*public static void updateInvoice(Invoice invoice) {
        String sql = "UPDATE invoices SET customer_id = ?, employee_id = ?, creation_date = ?, total_value = ? WHERE invoice_id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, invoice.getCustomerId());
            pstmt.setString(2, invoice.getEmployeeId());
            pstmt.setDate(3, Date.valueOf(invoice.getCreationDate()));
            pstmt.setDouble(4, invoice.getTotalValue());
            pstmt.setString(5, invoice.getInvoiceId());
            pstmt.executeUpdate();

            OrderDetailDao.deleteOrderDetailsByInvoiceId(invoice.getInvoiceId());
            for (OrderDetail detail : invoice.getOrderDetails()) {
                OrderDetailDao.addOrderDetail(detail);
            }
        } catch (SQLException e) {
        }
    }

    public static void deleteInvoice(String invoiceId) {
        String sql = "DELETE FROM invoices WHERE invoice_id = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, invoiceId);
            pstmt.executeUpdate();

            OrderDetailDao.deleteOrderDetailsByInvoiceId(invoiceId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
}


