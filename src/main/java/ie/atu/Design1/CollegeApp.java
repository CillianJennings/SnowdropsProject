package ie.atu.Design1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CollegeApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the College Database");

        Scanner scan = new Scanner(System.in);
        String choice = "y";
        int operation;

        while (choice.equalsIgnoreCase("y")) {
            System.out.print("What do you want to do:\n1 Select from database\n2 Write to database\n3 Update database\n4 Delete from database");
            operation = scan.nextInt();

            switch(operation){
                case 1:
                    select();
                    break;
                case 2:
                    System.out.println("case 2");
                    break;
                case 3:
                    System.out.println("case 3");
                    break;
                case 4:
                    System.out.println("case 4");
                    break;
                default:
                    System.out.println("Not an option, please enter a number between 1 and 4");
            }

            System.out.print("Continue? (y/n): ");
            choice = scan.nextLine();
            System.out.println();
        }
    }

    public static void select(){
        String selectSQL = "SHOW tables";

        ArrayList<String> tables = new ArrayList<String>();

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)){

            while (resultSet.next()) {
                tables.add(resultSet.getString(1));

                System.out.println(Arrays.toString(tables.toArray()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


