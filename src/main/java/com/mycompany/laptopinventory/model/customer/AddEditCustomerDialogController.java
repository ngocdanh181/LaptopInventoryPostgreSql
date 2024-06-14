package com.mycompany.laptopinventory.model.customer;

import com.mycompany.laptopinventory.model.product.Product;
import com.mycompany.laptopinventory.model.product.ProductDAO;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class AddEditCustomerDialogController  {
    
     @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private ChoiceBox<String> customerTypeChoiceBox;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField addressField;
  

    private Stage dialogStage;
    private Customer customer;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        
        customerTypeChoiceBox.getItems().addAll(
            "Individual",
            "Company"
        );
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            customerIdField.setText(customer.getCustomerId());
            customerNameField.setText(customer.getCustomerName());
            customerTypeChoiceBox.setValue(customer.getCustomerType());
            phoneNumberField.setText(customer.getPhoneNumber());
            addressField.setText(customer.getAddress());
            
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            customer.setCustomerId(customerIdField.getText());
            customer.setCustomerName(customerNameField.getText());
            customer.setPhoneNumber(phoneNumberField.getText());
            customer.setAddress(addressField.getText());
            String selectedOption = customerTypeChoiceBox.getValue();
            if (selectedOption != null) {
            switch (selectedOption) {
                case "Individual" -> customer.setCustomerType(selectedOption);
                case "Company" -> customer.setCustomerType(selectedOption);
                }
            }
            System.out.println("Da lay");

            saveClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    
}
