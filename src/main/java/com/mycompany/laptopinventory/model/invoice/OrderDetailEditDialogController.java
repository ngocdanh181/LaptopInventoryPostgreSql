package com.mycompany.laptopinventory.model.invoice;


import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderDetailEditDialogController {

    @FXML
    private TextField productOdIdField;
    @FXML
    private TextField quantityOdField;
    @FXML
    private TextField unitPriceOdField;
    @FXML
    private TextField discountOdField;

    private Stage dialogOrderStage;
    private OrderDetail orderDetail;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogOrderStage = dialogStage;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
        if (orderDetail != null) {
            productOdIdField.setText(orderDetail.getProductName());
            quantityOdField.setText(String.valueOf(orderDetail.getQuantity()));
            unitPriceOdField.setText(String.valueOf(orderDetail.getPrice()));
            discountOdField.setText(String.valueOf(orderDetail.getDiscount()));
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        orderDetail.setProductName(productOdIdField.getText());
        orderDetail.setQuantity(Integer.parseInt(quantityOdField.getText()));
        orderDetail.setPrice(Double.valueOf(unitPriceOdField.getText()));
        orderDetail.setDiscount(Double.valueOf(discountOdField.getText()));

        saveClicked = true;
        dialogOrderStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogOrderStage.close();
    }
}
