package com.mycompany.laptopinventory.model.component;


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

public class ComponentsController {

@FXML
private TableView<Component> componentsTable;

@FXML
private TableColumn<Component, Integer> componentIdColumn;

@FXML
private TableColumn<Component, String> componentNameColumn;

@FXML
private TableColumn<Component, String> componentTypeColumn;

@FXML
private TableColumn<Component, Double> sellPriceColumn;


@FXML
private ChoiceBox<String> typeOptions;



@FXML
private ChoiceBox<String> sortOptions;


private final ObservableList<Component> componentList = FXCollections.observableArrayList();

public ComponentsController() {
    // Load data from the database into the ObservableList
    componentList.addAll(ComponentDAO.getAllComponents());
}

@FXML
private void initialize() {
    // Initialize the component table with the columns
    
    componentIdColumn.setCellValueFactory(new PropertyValueFactory<>("componentId"));
    componentNameColumn.setCellValueFactory(new PropertyValueFactory<>("componentName"));
    componentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("componentType"));
    sellPriceColumn.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));

    componentsTable.setItems(componentList);
    
    // Add sorting options
    sortOptions.getItems().addAll(
        "Price: Low to High",
        "Price: High to Low",
        "Name: A to Z",
        "Name: Z to A",
        "All"
    );
    
    //Add type options
    typeOptions.getItems().addAll(
        "Chip",
        "Mainboard",
        "HDD",
        "SSD",
        "RAM",
        "VGA"
    );
}

@FXML
private void handleAddComponent() {
    // Handle adding a new component
    // Open a dialog to add a new component
    /*Component newComponent = new Component();
    boolean saveClicked = showComponentEditDialog(newComponent);
    if (saveClicked) {
        ComponentDAO.addComponent(newComponent);
        componentList.add(newComponent);
        componentList.clear();
        componentList.addAll(ComponentDAO.getAllComponents());
    }*/
}

@FXML
private void handleEditComponent() {
    // Handle editing a selected component
    // Open a dialog to edit the selected component
    /*Component selectedComponent = componentsTable.getSelectionModel().getSelectedItem();
    if (selectedComponent != null) {
        boolean saveClicked = showComponentEditDialog(selectedComponent);
        if (saveClicked) {
            ComponentDAO.updateComponent(selectedComponent);
            refreshComponentTable();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Component Selected");
        alert.setContentText("Please select a component in the table.");
        alert.showAndWait();
    }*/
}

@FXML
private void handleDeleteComponent() {
    // Handle deleting a selected component
    /*int selectedIndex = componentsTable.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Component selectedComponent = componentsTable.getItems().get(selectedIndex);
        ComponentDAO.deleteComponent(selectedComponent.getComponentId());
        componentsTable.getItems().remove(selectedIndex);
        
        componentList.clear();
        componentList.addAll(ComponentDAO.getAllComponents());
    } else {
        // Nothing selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Component Selected");
        alert.setContentText("Please select a component in the table.");
        alert.showAndWait();
    }*/
}

private String selectedType = null;

@FXML
private void handleSort() {
    String selectedOption = sortOptions.getValue();
    if (selectedOption != null) {
        List<Component> sortedComponents;
        boolean ascending;
        String column;
        switch (selectedOption) {
            case "Price: Low to High" -> {
                column = "sell_price";
                ascending = true;
            }
            case "Price: High to Low" -> {
                column = "sell_price";
                ascending = false;
            }
            case "Name: A to Z" -> {
                column = "component_name";
                ascending = true;
            }
            case "Name: Z to A" -> {
                column = "component_name";
                ascending = false;
            }
            case "All" ->{
                sortedComponents = ComponentDAO.getAllComponents();
                componentList.setAll(sortedComponents);
                return;
            }
                
            default -> {
                sortedComponents = ComponentDAO.getAllComponents();
                componentList.setAll(sortedComponents);
                return;
            }
        }
        if (selectedType != null) {
            sortedComponents = ComponentDAO.getComponentsSortedByTypeAndColumn(selectedType, column, ascending);
        } else {
            sortedComponents = ComponentDAO.getComponentsSortedBy(column, ascending);
        }
        componentList.setAll(sortedComponents);
    }
}

@FXML
private void handleTypeSort() {
    selectedType = typeOptions.getValue();
    if (selectedType != null) {
        List<Component> typedComponents;
        String selectedOption = sortOptions.getValue();
        if (selectedOption != null) {
            boolean ascending;
            String column;
            switch (selectedOption) {
                case "Price: Low to High" -> {
                    column = "sell_price";
                    ascending = true;
                }
                case "Price: High to Low" -> {
                    column = "sell_price";
                    ascending = false;
                }
                case "Name: A to Z" -> {
                    column = "component_name";
                    ascending = true;
                }
                case "Name: Z to A" -> {
                    column = "component_name";
                    ascending = false;
                }
                default -> {
                    typedComponents = ComponentDAO.getComponentsTypedBy(selectedType);
                    componentList.setAll(typedComponents);
                    return;
                }
            }
            typedComponents = ComponentDAO.getComponentsSortedByTypeAndColumn(selectedType, column, ascending);
        } else {
            typedComponents = ComponentDAO.getComponentsTypedBy(selectedType);
        }
        componentList.setAll(typedComponents);
    }
}


}
