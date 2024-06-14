package com.mycompany.laptopinventory.model.employee;

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

public class EmployeesController {

    @FXML
    private TableView<Employee> employeesTable;
    @FXML
    private TableColumn<Employee, String> sttColumn;
    @FXML
    private TableColumn<Employee, String> employeeIdColumn;
    @FXML
    private TableColumn<Employee, String> employeeNameColumn;
    @FXML
    private TableColumn<Employee, String> addressColumn;
    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public EmployeesController() {
        // Load data from the database into the ObservableList
        employeeList.addAll(EmployeeDao.getAllEmployees());
    }

    @FXML
    private void initialize() {
        // Initialize the employee table with the columns
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        employeesTable.setItems(employeeList);
    }

    @FXML
    private void handleAddEmployee() {
        // Handle adding a new employee
        // Open a dialog to add a new employee
        Employee newEmployee = new Employee();
        boolean saveClicked = showEmployeeEditDialog(newEmployee);
        if (saveClicked) {
            EmployeeDao.addEmployee(newEmployee);
            refreshEmployeeTable();
        }
    }

    @FXML
    private void handleEditEmployee() {
        // Handle editing a selected employee
        // Open a dialog to edit the selected employee
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            boolean saveClicked = showEmployeeEditDialog(selectedEmployee);
            if (saveClicked) {
                EmployeeDao.updateEmployee(selectedEmployee);
                refreshEmployeeTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Employee Selected");
            alert.setContentText("Please select an employee in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteEmployee() {
        // Handle deleting a selected employee
        int selectedIndex = employeesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Employee selectedEmployee = employeesTable.getItems().get(selectedIndex);
            EmployeeDao.deleteEmployee(selectedEmployee.getEmployeeId());
            employeesTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Employee Selected");
            alert.setContentText("Please select an employee in the table.");
            alert.showAndWait();
        }
    }

    private boolean showEmployeeEditDialog(Employee employee) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/AddEditEmployeeDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Employee");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(employeesTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddEditEmployeeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEmployee(employee);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            return false;
        }
    }

    private void refreshEmployeeTable() {
        int selectedIndex = employeesTable.getSelectionModel().getSelectedIndex();
        employeesTable.setItems(null);
        employeesTable.setItems(employeeList);
        employeesTable.getSelectionModel().select(selectedIndex);
    }

}
