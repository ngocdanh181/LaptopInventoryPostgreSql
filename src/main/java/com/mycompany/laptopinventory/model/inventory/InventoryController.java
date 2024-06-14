package com.mycompany.laptopinventory.model.inventory;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class InventoryController {

    @FXML
    private TextField productIdField;
    
    @FXML
    private TextField componentIdField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField entryPriceField;

    @FXML
    private TableView<Inventory> inventoryTable;

    @FXML
    private TableColumn<Inventory, Integer> inventoryIdColumn;

    @FXML
    private TableColumn<Inventory, String> productIdColumn;

    @FXML
    private TableColumn<Inventory, Integer> componentIdColumn;

    @FXML
    private TableColumn<Inventory, Integer> quantityColumn;

    @FXML
    private TableColumn<Inventory, Double> entryPriceColumn;

    @FXML
    public void initialize() {
        inventoryIdColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        componentIdColumn.setCellValueFactory(new PropertyValueFactory<>("componentId"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        entryPriceColumn.setCellValueFactory(new PropertyValueFactory<>("entryPrice"));

        // Load all inventory items
        inventoryTable.setItems(InventoryDAO.getAllInventories());
    }

    @FXML
    private void handleAdd() {
        String productId = productIdField.getText();
        String componentIdText = componentIdField.getText();
        String quantityText = quantityField.getText();
        String entryPriceText = entryPriceField.getText();

        if (productId.isEmpty() || quantityText.isEmpty() || entryPriceText.isEmpty()) {
            showAlert("Please fill all required fields.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double entryPrice = Double.parseDouble(entryPriceText);
            Integer componentId = componentIdText.isEmpty() ? null : Integer.parseInt(componentIdText);

            Inventory inventory = new Inventory(null, productId, quantity, entryPrice, componentId);
            InventoryDAO.addInventory(inventory);
            inventoryTable.setItems(InventoryDAO.getAllInventories());
        } catch (NumberFormatException e) {
            showAlert("Quantity and Entry Price must be numeric.");
        }
    }

    @FXML
    private void handleUpdate() {
        Inventory selected = inventoryTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an item to update.");
            return;
        }

        String productId = productIdField.getText();
        String componentIdText = componentIdField.getText();
        String quantityText = quantityField.getText();
        String entryPriceText = entryPriceField.getText();

        if (productId.isEmpty() || quantityText.isEmpty() || entryPriceText.isEmpty()) {
            showAlert("Please fill all required fields.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double entryPrice = Double.parseDouble(entryPriceText);
            Integer componentId = componentIdText.isEmpty() ? null : Integer.parseInt(componentIdText);

            selected.setProductId(productId);
            selected.setComponentId(componentId);
            selected.setQuantity(quantity);
            selected.setEntryPrice(entryPrice);

            InventoryDAO.updateInventory(selected);
            inventoryTable.refresh();
        } catch (NumberFormatException e) {
            showAlert("Quantity and Entry Price must be numeric.");
        }
    }

    @FXML
    private void handleDelete() {
        Inventory selected = inventoryTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an item to delete.");
            return;
        }

        InventoryDAO.deleteInventory(selected.getInventoryId());
        inventoryTable.setItems(InventoryDAO.getAllInventories());
    }
    
     @FXML
    private void handleSearch() {
        String productId = productIdField.getText();
        String componentIdText = componentIdField.getText();

        if (!productId.isEmpty()) {
            if (!InventoryDAO.getInventoriesByProductId(productId).isEmpty()){
                inventoryTable.setItems(InventoryDAO.getInventoriesByProductId(productId));
                 System.out.print("Have product ID");
            }else{
                inventoryTable.setItems(InventoryDAO.getInventoriesByProductName(productId));
                System.out.print("Have product name");
            }
            
        } else if (!componentIdText.isEmpty()) {
            try {
                int componentId = Integer.parseInt(componentIdText);
                inventoryTable.setItems(InventoryDAO.getInventoriesByComponentId(componentId));
            } catch (NumberFormatException e) {
                showAlert("Component ID must be numeric.");
            }
        } else {
            showAlert("Please enter a Product ID or Component ID to search.");
        }
    }

    @FXML
    private void handleViewAll() {
        inventoryTable.setItems(InventoryDAO.getAllInventories());
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
