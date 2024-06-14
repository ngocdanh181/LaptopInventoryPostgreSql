package com.mycompany.laptopinventory.model.invoice;

import java.io.IOException;
import java.time.LocalDate;
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

public class InvoicesController {
    @FXML
    private TableView<Invoice> invoicesTable;
    @FXML
    private TableColumn<Invoice, String> sttColumn;
    @FXML
    private TableColumn<Invoice, String> invoiceIdColumn;
    @FXML
    private TableColumn<Invoice, String> customerIdColumn;
    @FXML
    private TableColumn<Invoice, String> employeeIdColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> createDateColumn;
    @FXML
    private TableColumn<Invoice, Double> totalValueColumn;

    private final ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    public InvoicesController() {
        // Load data from the database into the ObservableList
        invoiceList.addAll(InvoiceDao.getAllInvoices());
    }

    @FXML
    private void initialize() {
        // Initialize the invoice table with the columns
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
        invoiceIdColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        totalValueColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        invoicesTable.setItems(invoiceList);
    }

    @FXML
    private void handleAddInvoice() {
        Invoice newInvoice = new Invoice();
        boolean saveClicked = showInvoiceAddDialog(newInvoice);
        if (saveClicked) {
            InvoiceDao.addInvoice(newInvoice);
            invoiceList.add(newInvoice);
            refreshInvoiceTable();
        }
    }
    private boolean showInvoiceAddDialog(Invoice invoice) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/AddEditInvoiceDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Invoice");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(invoicesTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddEditInvoiceDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInvoice(invoice);

            dialogStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            return false;
        }
    }

    @FXML
    private void handleEditInvoice() {
        // Handle editing a selected invoice
        // Open a dialog to edit the selected invoice
        /*Invoice selectedInvoice = invoicesTable.getSelectionModel().getSelectedItem();
        if (selectedInvoice != null) {
            boolean saveClicked = showInvoiceEditDialog(selectedInvoice);
            if (saveClicked) {
                InvoiceDao.updateInvoice(selectedInvoice);
                refreshInvoiceTable();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Invoice Selected");
            alert.setContentText("Please select an invoice in the table.");
            alert.showAndWait();
        }*/
    }

    @FXML
    private void handleDeleteInvoice() {
        /*// Handle deleting a selected invoice
        int selectedIndex = invoicesTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Invoice selectedInvoice = invoicesTable.getItems().get(selectedIndex);
            InvoiceDao.deleteInvoice(selectedInvoice.getInvoiceId());
            invoicesTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Invoice Selected");
            alert.setContentText("Please select an invoice in the table.");
            alert.showAndWait();
        }*/
    }
    
    @FXML
    private void handleShowDetail(){
        Invoice selectedInvoice = invoicesTable.getSelectionModel().getSelectedItem();
        if (selectedInvoice != null) {
            showOrderDetailDialog(selectedInvoice.getInvoiceId());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Customer Selected");
            alert.setContentText("Please select a customer in the table.");
            alert.showAndWait();
        }
    }
    
     private void showOrderDetailDialog(String invoiceId) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/OrderDetail.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Order Detail");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(invoicesTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            OrderDetailController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInvoiceId(invoiceId);

            dialogStage.showAndWait();
        } catch (IOException e) {
        }
    }
    
     
     

    private void refreshInvoiceTable() {
        int selectedIndex = invoicesTable.getSelectionModel().getSelectedIndex();
        invoicesTable.setItems(null);
        invoicesTable.setItems(invoiceList);
        invoicesTable.getSelectionModel().select(selectedIndex);
    }
}

