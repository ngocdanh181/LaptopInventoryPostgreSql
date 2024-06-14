
package com.mycompany.laptopinventory.model.report;
import java.time.LocalDate;

public class Report {
    private int reportId;
    private LocalDate reportDate;
    private Double totalRevenue = 0.0 ;
    private String bestSellingProductId;
    private int bestSellingQuantity;
    private String leastSellingProductId;
    private int leastSellingQuantity;
    private String sellingEmployee;

    // Constructors
    public Report() {
       
    }

    public Report(LocalDate reportDate, Double totalRevenue) {
        this.reportDate = reportDate;
        this.totalRevenue = totalRevenue;
    }

    public Report(Double totalRevenue, int bestSellingQuantity, String sellingEmployee) {
        this.totalRevenue = totalRevenue;
        this.bestSellingQuantity = bestSellingQuantity;
        this.sellingEmployee = sellingEmployee;
    }
    
    

    public Report(int reportId, LocalDate reportDate, double totalRevenue, String bestSellingProductId, int bestSellingQuantity, String leastSellingProductId, int leastSellingQuantity, String sellingEmployee) {
        this.reportId = reportId;
        this.reportDate = reportDate;
        this.totalRevenue = totalRevenue;
        this.bestSellingProductId = bestSellingProductId;
        this.bestSellingQuantity = bestSellingQuantity;
        this.leastSellingProductId = leastSellingProductId;
        this.leastSellingQuantity = leastSellingQuantity;
        this.sellingEmployee = sellingEmployee;
    }

    // Getters and Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getBestSellingProductId() {
        return bestSellingProductId;
    }

    public void setBestSellingProductId(String bestSellingProductId) {
        this.bestSellingProductId = bestSellingProductId;
    }

    public int getBestSellingQuantity() {
        return bestSellingQuantity;
    }

    public void setBestSellingQuantity(int bestSellingQuantity) {
        this.bestSellingQuantity = bestSellingQuantity;
    }

    public String getLeastSellingProductId() {
        return leastSellingProductId;
    }

    public void setLeastSellingProductId(String leastSellingProductId) {
        this.leastSellingProductId = leastSellingProductId;
    }

    public int getLeastSellingQuantity() {
        return leastSellingQuantity;
    }

    public void setLeastSellingQuantity(int leastSellingQuantity) {
        this.leastSellingQuantity = leastSellingQuantity;
    }

    public String getSellingEmployee() {
        return sellingEmployee;
    }

    public void setSellingEmployee(String sellingEmployee) {
        this.sellingEmployee = sellingEmployee;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", reportDate=" + reportDate +
                ", totalRevenue=" + totalRevenue +
                ", bestSellingProductId='" + bestSellingProductId + '\'' +
                ", bestSellingQuantity=" + bestSellingQuantity +
                ", leastSellingProductId='" + leastSellingProductId + '\'' +
                ", leastSellingQuantity=" + leastSellingQuantity +
                ", sellingEmployee='" + sellingEmployee + '\'' +
                '}';
    }
}



