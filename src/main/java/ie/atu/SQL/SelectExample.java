package ie.atu.SQL;

import java.sql.*;

public class SelectExample {

    public static void main(String[] args) {

        Connection connection = null;
        try
        {
            // Load the driver class
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlserver://snowdrops.database.windows.net:1433;database=College;user=CloudSA831cfb84@snowdrops;" +
                    "password=Airbender96;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
            System.out.println("Connection made to connection pool");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally
        {
            // Close the connection when finished
            if (connection != null) {
                try
                {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
