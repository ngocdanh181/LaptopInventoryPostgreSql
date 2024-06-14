package com.mycompany.laptopinventory.model.customer;


import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class CustomersController  {

    @FXML
    private TableView<Customer> customersTable;
     @FXML
    private TableColumn<Customer, Integer> sttColumn;

    @FXML
    private TableColumn<Customer, String> customerIdColumn;
    @FXML
    private TableColumn<Customer, String> customerNameColumn;
    @FXML
    private TableColumn<Customer, String> customerTypeColumn;
    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;


    private final ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public CustomersController() {
        // Load data from the database into the ObservableList
        customerList.addAll(CustomerDao.getAllCustomers());
    }

    @FXML
    private void initialize() {
        // Initialize the product table with the columns
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        

        customersTable.setItems(customerList);
        System.out.print(customerList);
        
       
    }

    @FXML
    private void handleAddCustomer() {
        // Handle adding a new product
        // Open a dialog to add a new product
        Customer newCustomer = new Customer();
        boolean saveClicked = showCustomerEditDialog(newCustomer);
        if (saveClicked) {
            CustomerDao.addCustomer(newCustomer);
            customerList.clear();
            customerList.addAll(CustomerDao.getAllCustomers());

        }
    }

    @FXML
    private void handleEditCustomer() {
        
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
            boolean saveClicked = showCustomerEditDialog(selectedCustomer);
            if (saveClicked) {
                System.out.print(selectedCustomer);
                CustomerDao.updateCustomer(selectedCustomer);
                customerList.clear();
                customerList.addAll(CustomerDao.getAllCustomers());
                //refreshCustomerTable();
                
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteCustomer() {
       // Handle deleting a selected product
        int selectedIndex = customersTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Customer selectedProduct = customersTable.getItems().get(selectedIndex);
            CustomerDao.deleteProduct(selectedProduct.getCustomerId());
            customersTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }
   
    
  

    private boolean showCustomerEditDialog(Customer customer) {
         try {
             
            FXMLLoader loader = new FXMLLoader();
            
            
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/AddEditCustomerDialog.fxml")); 
           
            AnchorPane page = (AnchorPane)loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Customer");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(customersTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddEditCustomerDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCustomer(customer);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            return false;
        }
    }

    private void refreshCustomerTable() {
        int selectedIndex = customersTable.getSelectionModel().getSelectedIndex();
        customersTable.setItems(null);
        customersTable.setItems(customerList);
        customersTable.getSelectionModel().select(selectedIndex);
    }
    
}
