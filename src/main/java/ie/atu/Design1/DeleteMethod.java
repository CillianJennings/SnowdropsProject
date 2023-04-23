package ie.atu.Design1;

import java.sql.*;

public class DeleteMethod {
    public static void main(String[] args) throws SQLException {

        //Connect to database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "root");

        try{

            //Deleting from student table
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student");
            stmt.executeUpdate();

            //Deleting from address table
            stmt = conn.prepareStatement("DELETE FROM address");
            stmt.executeUpdate();

            //Deleting from course table
            stmt = conn.prepareStatement("DELETE FROM course");
            stmt.executeUpdate();

            System.out.println("Successfully deleted table");
        }
        catch (SQLException ex){
            System.out.println("Deleting failed");
            ex.printStackTrace();
        }

        //Closing Connection
        conn.close();
    }
}
