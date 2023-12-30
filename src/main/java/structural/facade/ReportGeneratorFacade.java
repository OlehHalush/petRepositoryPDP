package structural.facade;

import java.sql.Connection;

public class ReportGeneratorFacade {

    public static void generateReport(DBType dbType, ReportType reportType, String tableName) {
        Connection con = null;
        switch (dbType) {
            case MY_SQL:
                con = MySqlReportHelper.getConnection();
                MySqlReportHelper mySqlReportHelper = new MySqlReportHelper();
                switch (reportType) {
                    case HTML:
                        mySqlReportHelper.generateHTMLReport(tableName, con);
                        break;
                    case PDF:
                        mySqlReportHelper.generatePDFReport(tableName, con);
                        break;
                }
                break;
            case POSTGRE_SQL:
                con = PostgreSqlReportHelper.getConnection();
                PostgreSqlReportHelper postgreSqlReportHelper = new PostgreSqlReportHelper();
                switch (reportType) {
                    case HTML:
                        postgreSqlReportHelper.generateHTMLReport(tableName, con);
                        break;
                    case PDF:
                        postgreSqlReportHelper.generatePDFReport(tableName, con);
                        break;
                }
                break;
        }
    }

    public static enum DBType {
        MY_SQL, POSTGRE_SQL
    }

    public static enum ReportType {
        HTML, PDF
    }
}
