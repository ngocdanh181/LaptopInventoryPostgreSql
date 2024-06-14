/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laptopinventory.model.invoice;

/**
 *
 * @author ADMIN
 */

import com.mycompany.laptopinventory.model.orderdetail.OrderDetail;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private Integer stt;
    private String invoiceId ;
    private String customerId;
    private String employeeId;
    private LocalDate creationDate;
    private Double totalValue;
    private List<OrderDetail> orderDetails;

    public Invoice() {
        this.orderDetails = new ArrayList<>();
        invoiceId=" ";
    }

    public Invoice(Integer stt, String invoiceId, String customerId, String employeeId, LocalDate creationDate, Double totalValue) {
        this.stt = stt;
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.creationDate = creationDate;
        this.totalValue = totalValue;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }
    
    

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    

    
}
