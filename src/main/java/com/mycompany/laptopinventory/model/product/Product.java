/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.laptopinventory.model.product;

/**
 *
 * @author ADMIN
 */
public class Product {
    
    
    private int stt;
    private String productId, productName, ram, chip, main, vga, ssd, hdd;
    private double sellPrice;

    public Product() {
    }

    public Product(int stt, String productId, String productName, String ram, String chip, String main, String vga, String ssd, String hdd, double sellPrice) {
        this.stt = stt;
        this.productId = productId;
        this.productName = productName;
        this.ram = ram;
        this.chip = chip;
        this.main = main;
        this.vga = vga;
        this.ssd = ssd;
        this.hdd = hdd;
        this.sellPrice = sellPrice;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getHdd() {
        return hdd;
    }

    public void setHdd(String hdd) {
        this.hdd = hdd;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    
    
}
