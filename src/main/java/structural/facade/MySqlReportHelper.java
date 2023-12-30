package structural.facade;

import java.sql.Connection;

public class MySqlReportHelper {

    public static Connection getConnection(){
        System.out.println("Creating MySql connection");
        return null;
    }

    public void generateHTMLReport(String tableName, Connection con){
        System.out.println("Generating MySql HTML report for table: " + tableName);
    }

    public void generatePDFReport(String tableName, Connection con){
        System.out.println("Generating MySql PDF report for table: " + tableName);
    }
}
