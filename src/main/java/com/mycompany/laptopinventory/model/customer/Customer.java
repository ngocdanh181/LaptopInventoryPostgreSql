package com.mycompany.laptopinventory.model.customer;

public class Customer {
    private int stt;
    private String customerId;
    private String customerType;
    private String customerName;
    private String phoneNumber;
    private String address;

    // Constructors
    public Customer() {
    }

    public Customer(int stt,String customerId, String customerType, String customerName, String phoneNumber, String address) {
        this.stt = stt;
        this.customerId = customerId;
        this.customerType = customerType;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
    
    public int getStt() {
        return stt;
    }

    // Getters and Setters
    public void setStt(int stt) {    
        this.stt = stt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerType='" + customerType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}


