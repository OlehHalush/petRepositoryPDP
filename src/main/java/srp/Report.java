package srp;

public class Report {
    private String content;

    public Report(String content) {
        this.content = content;
    }

    public Report generateReport() {
        System.out.println("Generating report: " + content);
        return this;
    }
}
