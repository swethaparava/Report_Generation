package report;

import java.util.UUID;

/**
 * The type Report generation request.
 * Simulating report generation requests,
 * where each report generation needs to execute two queries from two different databases.
 * report1 from customer database
 * report2 from orders database
 */
public class ReportGenerationRequest {
    private String report1 = "SELECT * FROM customerdetails";
    private String report2 = "SELECT * FROM orderhistory";
    private String reportGenerationRequestId = UUID.randomUUID().toString();

    public String getReport1() {
        return report1;
    }

    public void setReport1(String report1) {
        this.report1 = report1;
    }

    public String getReport2() {
        return report2;
    }

    public void setReport2(String report2) {
        this.report2 = report2;
    }

    public String getReportGenerationRequestId() {
        return reportGenerationRequestId;
    }

    public void setReportGenerationRequestId(String reportGenerationRequestId) {
        this.reportGenerationRequestId = reportGenerationRequestId;
    }
}