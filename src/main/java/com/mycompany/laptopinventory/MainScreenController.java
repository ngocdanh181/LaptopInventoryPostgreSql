package com.mycompany.laptopinventory;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;


public class MainScreenController {

    
    @FXML
    private AnchorPane rootLayout;
    
     @FXML
    private AnchorPane contentArea;

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
private void handleProducts() {
    // Load and set the Products management view into rootLayout
    try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Products.fxml"));
            AnchorPane productsPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(productsPage);
    } catch (IOException e) {
        // In ra lỗi nếu có
        
    }
}
    
    @FXML 
    private void handleComponents(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/Components.fxml"));
            AnchorPane componentsPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(componentsPage);
        } catch (IOException e) {
        // In ra lỗi nếu có
        }
    }

    @FXML
    private void handleInventory() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Inventory.fxml"));
            AnchorPane InventoryPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(InventoryPage);
        } catch (IOException e) {
        // In ra lỗi nếu có
        }
    }

    @FXML
    private void handleInvoices() {
        // Load and set the Invoices management view into mainContent
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Invoices.fxml"));
            AnchorPane InvoicesPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(InvoicesPage);
        } catch (IOException e) {
        // In ra lỗi nếu có
        }
    }

    @FXML
    private void handleOrderDetails() {
        // Load and set the Order Details management view into mainContent
    }

    @FXML
    private void handleReports() {
        // Load and set the Reports management view into mainContent
        // Load and set the Employees management view into mainContent
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Report.fxml"));
            AnchorPane ReportPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(ReportPage);
        } catch (IOException e) {
        // In ra lỗi nếu có
        }
    }

    @FXML
    private void handleEmployees() {
        // Load and set the Employees management view into mainContent
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Employees.fxml"));
            AnchorPane EmployeesPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(EmployeesPage);
        } catch (IOException e) {
        // In ra lỗi nếu có
        }
    }

    @FXML
    private void handleCustomers() {
        // Load and set the Customers management view into mainContent
        // Load and set the Warehouse management view into mainContent
            try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Customers.fxml"));
            AnchorPane customersPage = (AnchorPane) loader.load();

            // Clear the current content and add the new content
            contentArea.getChildren().setAll(customersPage);
            } catch (IOException e) {
            // In ra lỗi nếu có
        
            }
    }

 
}
