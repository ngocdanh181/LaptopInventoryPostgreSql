package com.mycompany.laptopinventory.model.invoice;

import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEditInvoiceDialogController {
    
    @FXML
    private TextField invoiceIdOField;
    
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField employeeIdField;
    @FXML
    private DatePicker creationDatePicker;
    @FXML
    private TableView<OrderDetail> productsAddTable;
    @FXML
    private TableColumn<OrderDetail, String> productIdColumn;
    @FXML
    private TableColumn<OrderDetail, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderDetail, Double> unitPriceColumn;
    @FXML
    private TableColumn<OrderDetail, Double> discountColumn;

    private Stage dialogStage5;
    private Invoice invoice;
    private final ObservableList<OrderDetail> orderDetails = FXCollections.observableArrayList();
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
        
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
        /*productIdColumn.setCellValueFactory((TableColumn.CellDataFeatures<OrderDetail, String> p)
                -> new SimpleStringProperty(p.getValue().getProductName()));
        quantityColumn.setCellValueFactory((TableColumn.CellDataFeatures<OrderDetail, Integer> p) 
                -> new SimpleIntegerProperty(p.getValue().getQuantity()).asObject());
        unitPriceColumn.setCellValueFactory((TableColumn.CellDataFeatures<OrderDetail, Double> p) 
                -> new SimpleDoubleProperty(p.getValue().getPrice()).asObject());
        discountColumn.setCellValueFactory((TableColumn.CellDataFeatures<OrderDetail, Double> p) 
                -> new SimpleDoubleProperty(p.getValue().getDiscount()).asObject());*/

        productsAddTable.setItems(orderDetails);

       
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage5 = dialogStage;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice; 
        if (invoice != null) {
            invoiceIdOField.setText(invoice.getInvoiceId());
            customerIdField.setText(invoice.getCustomerId());
            employeeIdField.setText(invoice.getEmployeeId());
            creationDatePicker.setValue(invoice.getCreationDate());
            if (invoice.getOrderDetails() != null) {
                orderDetails.addAll(invoice.getOrderDetails());
            }
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleAddProduct() {
        OrderDetail tempDetail = new OrderDetail();
        boolean okClicked = showOrderDetailEditDialog(tempDetail);
        if (okClicked) {
            orderDetails.add(tempDetail);
        }
    }

    @FXML
    private void handleRemoveProduct() {
        int selectedIndex = productsAddTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            productsAddTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleSave() {
        System.out.print(invoiceIdOField.getText());
        if(invoiceIdOField.getText()!=null) {
            invoice.setInvoiceId(invoiceIdOField.getText());
            invoice.setCustomerId(customerIdField.getText());
            invoice.setEmployeeId(employeeIdField.getText());
            invoice.setCreationDate(creationDatePicker.getValue());
            invoice.setOrderDetails(orderDetails);

            saveClicked = true;
            dialogStage5.close();
        } else {
        }

        
    }

    @FXML
    private void handleCancel() {
        dialogStage5.close();
    }

    private boolean showOrderDetailEditDialog(OrderDetail orderDetail) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/OrderDetailEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage diaglogOrderStage = new Stage();
            diaglogOrderStage.setTitle("Add Detail Product");
            diaglogOrderStage.initModality(Modality.WINDOW_MODAL);
            diaglogOrderStage.initOwner(this.dialogStage5);
            Scene scene = new Scene(page);
            diaglogOrderStage.setScene(scene);

            OrderDetailEditDialogController controller = loader.getController();
            controller.setDialogStage(diaglogOrderStage);
            controller.setOrderDetail(orderDetail);

            diaglogOrderStage.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            return false;
        }
    }
}

