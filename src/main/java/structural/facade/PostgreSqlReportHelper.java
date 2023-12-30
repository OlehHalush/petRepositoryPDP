package structural.facade;

import java.sql.Connection;

public class PostgreSqlReportHelper {

    public static Connection getConnection() {
        System.out.println("Creating PostgreSQL connection");
        return null;
    }

    public void generateHTMLReport(String tableName, Connection con) {
        System.out.println("Generating PostgreSQL HTML report for table: " + tableName);
    }

    public void generatePDFReport(String tableName, Connection con) {
        System.out.println("Generating PostgreSQL PDF report for table: " + tableName);
    }
}
