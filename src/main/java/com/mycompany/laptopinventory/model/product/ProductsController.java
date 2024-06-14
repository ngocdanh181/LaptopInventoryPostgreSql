package com.mycompany.laptopinventory.model.product;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ProductsController {

    @FXML
    private TableView<Product> productsTable;
     @FXML
    private TableColumn<Product, Integer> sttColumn;

    @FXML
    private TableColumn<Product, String> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, String> ramColumn;
    @FXML
    private TableColumn<Product, String> mainboardColumn;
    @FXML
    private TableColumn<Product, String> chipColumn;
    @FXML
    private TableColumn<Product, String> ssdColumn;
    @FXML
    private TableColumn<Product, String> vgaColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;
    @FXML
    private TableColumn<Product, String> hddColumn;
    @FXML
    private ChoiceBox<String> sortOptions;

    private final ObservableList<Product> productList = FXCollections.observableArrayList();

    public ProductsController() {
        // Load data from the database into the ObservableList
        productList.addAll(ProductDAO.getAllProducts());
    }

    @FXML
    private void initialize() {
        // Initialize the product table with the columns
        sttColumn.setCellValueFactory(new PropertyValueFactory<>("stt"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ramColumn.setCellValueFactory(new PropertyValueFactory<>("ram"));
        mainboardColumn.setCellValueFactory(new PropertyValueFactory<>("main"));
        chipColumn.setCellValueFactory(new PropertyValueFactory<>("chip"));
        ssdColumn.setCellValueFactory(new PropertyValueFactory<>("ssd"));
        hddColumn.setCellValueFactory(new PropertyValueFactory<>("hdd"));
        vgaColumn.setCellValueFactory(new PropertyValueFactory<>("vga"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

        productsTable.setItems(productList);
        
        // Thêm các tùy chọn
        sortOptions.getItems().addAll(
            "Price: Low to High",
            "Price: High to Low",
            "Name: A to Z",
            "Name: Z to A"
        );
    }

    @FXML
    private void handleAddProduct() {
        // Handle adding a new product
        // Open a dialog to add a new product
        Product newProduct = new Product();
        boolean saveClicked = showProductEditDialog(newProduct);
        if (saveClicked) {
            ProductDAO.addProduct(newProduct);
            productList.add(newProduct);
            //refreshProductTable();
            productList.clear();
            productList.addAll(ProductDAO.getAllProducts());

            //ProductDAO.getAllProducts();
        }
    }

    @FXML
    private void handleEditProduct() {
        // Handle editing a selected product
        // Open a dialog to edit the selected product
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
            boolean saveClicked = showProductEditDialog(selectedProduct);
            if (saveClicked) {
                ProductDAO.updateProduct(selectedProduct);
                //refreshProductTable();
                productList.clear();
                productList.addAll(ProductDAO.getAllProducts());
                
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }

    }

    @FXML
    private void handleDeleteProduct() {
        // Handle deleting a selected product
        int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Product selectedProduct = productsTable.getItems().get(selectedIndex);
            ProductDAO.deleteProduct(selectedProduct.getProductId());
            productsTable.getItems().remove(selectedIndex);
            
            productList.clear();
            productList.addAll(ProductDAO.getAllProducts());
        } else {
            // Nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");
            alert.showAndWait();
        }
    }
   
    
     @FXML
    private void handleSort() {
        String selectedOption = sortOptions.getValue();
        if (selectedOption != null) {
            List<Product> sortedProducts;
            sortedProducts = switch (selectedOption) {
                case "Price: Low to High" -> ProductDAO.getProductsSortedBy("sell_price", true);
                case "Price: High to Low" -> ProductDAO.getProductsSortedBy("sell_price", false);
                case "Name: A to Z" -> ProductDAO.getProductsSortedBy("product_name", true);
                case "Name: Z to A" -> ProductDAO.getProductsSortedBy("product_name", false);
                default -> ProductDAO.getAllProducts();
            };
            productList.setAll(sortedProducts);
        }
    }

    private boolean showProductEditDialog(Product product) {
         try {
             
            FXMLLoader loader = new FXMLLoader();
            
            
           
            
            loader.setLocation(getClass().getResource("/com/mycompany/laptopinventory/AddEditProductDialog.fxml")); // Đảm bảo đường dẫn chính xác
            System.out.println("Resource found: " + getClass().getResource("/com/mycompany/laptopinventory/AddEditProductDialog.fxml"));

             AnchorPane page = (AnchorPane)loader.load();
            
            Stage dialogStage1 = new Stage();
            dialogStage1.setTitle("Edit Product");
            dialogStage1.initModality(Modality.WINDOW_MODAL);
            dialogStage1.initOwner(productsTable.getScene().getWindow());
            Scene scene = new Scene(page);
            dialogStage1.setScene(scene);

            AddEditProductDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage1);
            controller.setProduct(product);

            dialogStage1.showAndWait();

            return controller.isSaveClicked();
        } catch (IOException e) {
            return false;
        }
    }

    private void refreshProductTable() {
        int selectedIndex = productsTable.getSelectionModel().getSelectedIndex();
        productsTable.setItems(null);
        productsTable.setItems(productList);
        productsTable.getSelectionModel().select(selectedIndex);
    }
}
