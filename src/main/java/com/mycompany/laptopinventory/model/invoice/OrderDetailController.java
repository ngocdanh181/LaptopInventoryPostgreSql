package com.mycompany.laptopinventory.model.invoice;

import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class OrderDetailController {
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label employeeNameLabel;

    @FXML
    private TableView<OrderDetail> orderDetailTable;
    @FXML
    private TableColumn<OrderDetail, String> invoiceIdColumn;
    @FXML
    private TableColumn<OrderDetail, String> productNameColumn;
    @FXML
    private TableColumn<OrderDetail, Integer> quantityColumn;
    @FXML
    private TableColumn<OrderDetail, Double> priceColumn;
    @FXML
    private TableColumn<OrderDetail, Double> discountColumn;

    private Stage dialogStage;
    private String invoiceId;
    private final ObservableList<OrderDetail> odList = FXCollections.observableArrayList();

    public OrderDetailController() {
        // Constructor
    }

    @FXML
    private void initialize() {
        invoiceIdColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));

        orderDetailTable.setItems(odList);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
        odList.setAll(OrderDetailDao.getOrderDetailsByInvoiceId(invoiceId));

        String customerName = OrderDetailDao.getCustomerNameByInvoiceId(invoiceId);
        customerNameLabel.setText(customerName);

        String employeeName = OrderDetailDao.getEmployeeNameByInvoiceId(invoiceId);
        employeeNameLabel.setText(employeeName);
    }

    @FXML
    private void handleOK() {
        dialogStage.close();
    }
}
