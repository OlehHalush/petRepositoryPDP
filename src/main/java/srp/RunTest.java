package srp;

public class RunTest {
    public static void main(String[] args) {
        Report report = new Report("My report content").generateReport();
        new ReportSaver().saveReportToFile(report, "MyFile");
    }
}
