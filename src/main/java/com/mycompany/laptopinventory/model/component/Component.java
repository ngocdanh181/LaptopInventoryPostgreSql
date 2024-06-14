package com.mycompany.laptopinventory.model.component;

public class Component {
    private Integer componentId;
    private String componentName, componentType;
    private Double sellPrice;

    public Component() {
    }

    public Component(Integer componentId, String componentName, String componentType, Double sellPrice) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.componentType = componentType;
        this.sellPrice = sellPrice;
    }

    public Integer getComponentId() {
        return componentId;
    }

    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    
    
}
