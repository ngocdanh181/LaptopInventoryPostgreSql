package com.mycompany.laptopinventory.model.employee;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditEmployeeDialogController  {

@FXML
private TextField employeeIdField;
@FXML
private TextField employeeNameField;
@FXML
private TextField addressField;
@FXML
private TextField phoneNumberField;

private Stage dialogStage;
private Employee employee;
private boolean saveClicked = false;

@FXML
private void initialize() {
}

public void setDialogStage(Stage dialogStage) {
    this.dialogStage = dialogStage;
}

public void setEmployee(Employee employee) {
    this.employee = employee;

    if (employee != null) {
        employeeIdField.setText(employee.getEmployeeId());
        employeeNameField.setText(employee.getEmployeeName());
        addressField.setText(employee.getAddress());
        phoneNumberField.setText(employee.getPhoneNumber());
    }
}

public boolean isSaveClicked() {
    return saveClicked;
}

@FXML
private void handleSave() {
    if (isInputValid()) {
        employee.setEmployeeId(employeeIdField.getText());
        employee.setEmployeeName(employeeNameField.getText());
        employee.setAddress(addressField.getText());
        employee.setPhoneNumber(phoneNumberField.getText());

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
