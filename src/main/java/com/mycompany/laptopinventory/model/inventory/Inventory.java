package com.mycompany.laptopinventory.model.inventory;

public class Inventory {
    
    private String productId;
    private Integer inventoryId, quantity;
    private double entryPrice;
    private Integer componentId;

    public Inventory() {
        
    }

    public Inventory(Integer inventoryId, String productId , Integer quantity, double entryPrice, Integer componentId) {
        this.productId = productId;
        this.inventoryId = inventoryId;
        this.quantity = quantity;
        this.entryPrice = entryPrice;
        this.componentId = componentId;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }
    
    

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(double entryPrice) {
        this.entryPrice = entryPrice;
    }
    
}
