package structural.facade;

//https://www.digitalocean.com/community/tutorials/facade-design-pattern-in-java
public class RunTest {
    public static void main(String[] args) {
        ReportGeneratorFacade.generateReport(ReportGeneratorFacade.DBType.MY_SQL, ReportGeneratorFacade.ReportType.PDF, "Employee");
        ReportGeneratorFacade.generateReport(ReportGeneratorFacade.DBType.POSTGRE_SQL, ReportGeneratorFacade.ReportType.HTML, "Contacts");
    }
}
