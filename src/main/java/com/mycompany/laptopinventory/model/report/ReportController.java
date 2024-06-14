package com.mycompany.laptopinventory.model.report;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import java.util.List;

public class ReportController {

    @FXML
    private ComboBox<String> reportTypeComboBox;
    
    @FXML
    private DatePicker startDatePicker;
    
    @FXML
    private DatePicker endDatePicker;

    @FXML
    private ListView<String> reportListView;

    private final ObservableList<String> reportTypes = FXCollections.observableArrayList(
        "Doanh thu", 
        "Lợi nhuận", 
        "Mặt hàng bán chạy nhất", 
        "Nhân viên bán chạy nhất"
    );

    @FXML
    public void initialize() {
        reportTypeComboBox.setItems(reportTypes);
    }

    @FXML
    private void handleGenerateReport() {
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        String selectedReportType = reportTypeComboBox.getValue();
        ObservableList<String> reportData = FXCollections.observableArrayList();

        if (selectedReportType != null && startDate != null && endDate != null) {
            switch (selectedReportType) {
                case "Doanh thu" -> reportData.addAll(getRevenueReport(startDate, endDate));
                case "Lợi nhuận" -> reportData.addAll(getProfitReport(startDate, endDate));
                case "Mặt hàng bán chạy nhất" -> reportData.addAll(getBestSellingProduct(startDate, endDate));
                case "Nhân viên bán chạy nhất" -> reportData.addAll(getBestSellingEmployee(startDate, endDate));
            }
        }

        reportListView.setItems(reportData);
    }

    private ObservableList<String> getRevenueReport(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = ReportDao.getRevenueReport(startDate, endDate);
        ObservableList<String> reportData = FXCollections.observableArrayList();
        for (Report report : reports) {
            reportData.add(report.getReportDate() + ": " + report.getTotalRevenue() + " VND");
        }
        return reportData;
    }

    private ObservableList<String> getProfitReport(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = ReportDao.getProfitReport(startDate, endDate);
        ObservableList<String> reportData = FXCollections.observableArrayList();
        for (Report report : reports) {
            reportData.add(report.getReportDate() + ": " + report.getTotalRevenue() + " VND");
        }
        return reportData;
    }

    private ObservableList<String> getBestSellingProduct(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = ReportDao.getBestSellingProduct(startDate, endDate);
        ObservableList<String> reportData = FXCollections.observableArrayList();
        for (Report report : reports) {
            reportData.add(report.getBestSellingProductId() + ": " + report.getBestSellingQuantity());
        }
        return reportData;
    }

    private ObservableList<String> getBestSellingEmployee(LocalDate startDate, LocalDate endDate) {
        List<Report> reports = ReportDao.getBestSellingEmployee(startDate, endDate);
        ObservableList<String> reportData = FXCollections.observableArrayList();
        for (Report report : reports) {
            reportData.add(report.getSellingEmployee() 
                    + ": " + report.getBestSellingQuantity()
                    + ": " + report.getTotalRevenue());
        }
        return reportData;
    }
}
