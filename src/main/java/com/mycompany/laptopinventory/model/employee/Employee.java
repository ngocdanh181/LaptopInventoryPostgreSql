package com.mycompany.laptopinventory.model.employee;

public class Employee {
    private int stt;
    private String employeeId;
    private String employeeName;
    private String address;
    private String phoneNumber;
    

    // Constructors
    public Employee() {
    }

    public Employee(int stt,String employeeId, String employeeName, String address, String phoneNumber) {
        this.stt = stt;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.address = address;
        this.phoneNumber = phoneNumber;
       
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
    

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                
                '}';
    }
}
