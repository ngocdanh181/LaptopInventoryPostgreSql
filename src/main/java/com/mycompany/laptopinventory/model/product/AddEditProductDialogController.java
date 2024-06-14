package com.mycompany.laptopinventory.model.product;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEditProductDialogController {

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField ramField;
    @FXML
    private TextField mainboardField;
    @FXML
    private TextField chipField;
    @FXML
    private TextField ssdField;
    @FXML
    private TextField vgaField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField hddField;

    private Stage dialogStage;
    private Product product;
    private boolean saveClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;

        if (product != null) {
            productIdField.setText(product.getProductId());
            productNameField.setText(product.getProductName());
            ramField.setText(product.getRam());
            mainboardField.setText(product.getMain());
            chipField.setText(product.getChip());
            ssdField.setText(product.getSsd());
            vgaField.setText(product.getVga());
            priceField.setText(String.valueOf(product.getSellPrice()));
            hddField.setText(product.getHdd());
        }
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    @FXML
    private void handleSave() {
        if (isInputValid()) {
            product.setProductId(productIdField.getText());
            product.setProductName(productNameField.getText());
            product.setRam(ramField.getText());
            product.setMain(mainboardField.getText());
            product.setChip(chipField.getText());
            product.setSsd(ssdField.getText());
            product.setVga(vgaField.getText());
            product.setHdd(hddField.getText());
            product.setSellPrice(Double.parseDouble(priceField.getText()));
            System.out.println("Da lay");

            saveClicked = true;
            dialogStage.close();
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (productIdField.getText() == null || productIdField.getText().length() == 0) {
            errorMessage += "No valid product ID!\n";
        }
        if (productNameField.getText() == null || productNameField.getText().length() == 0) {
            errorMessage += "No valid product name!\n";
        }
        if (ramField.getText() == null || ramField.getText().length() == 0) {
            errorMessage += "No valid RAM!\n";
        }
        if (mainboardField.getText() == null || mainboardField.getText().length() == 0) {
            errorMessage += "No valid mainboard!\n";
        }
        if (chipField.getText() == null || chipField.getText().length() == 0) {
            errorMessage += "No valid chip!\n";
        }
        if (ssdField.getText() == null || ssdField.getText().length() == 0) {
            errorMessage += "No valid SSD!\n";
        }
        if (vgaField.getText() == null || vgaField.getText().length() == 0) {
            errorMessage += "No valid VGA!\n";
        }
        if (hddField.getText() == null || hddField.getText().length() == 0) {
            errorMessage += "No valid HDD!\n";
        }
        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "No valid price!\n";
        } else {
            try {
                Double.valueOf(priceField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid price (must be a number)!\n";
            }
        }

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

