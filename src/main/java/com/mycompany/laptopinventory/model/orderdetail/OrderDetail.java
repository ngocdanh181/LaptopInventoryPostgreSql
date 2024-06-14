package com.mycompany.laptopinventory.model.orderdetail;

/**
 *
 * @author Danh
 */
public class OrderDetail {
    
    private int orderDetailId;
    private String invoiceId;
    private String productName;
    private int quantity;
    private Double price = 0.0;
    private Double discount =0.0 ;
    

    // Constructors
    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, String invoiceId, String productName, int quantity, Double price, Double discount) {
        this.orderDetailId = orderDetailId;
        this.invoiceId = invoiceId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
    
    

    // Getters and Setters
    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    
}
