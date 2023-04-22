package ie.atu.Design1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CollegeApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the College Database");

        Scanner scan = new Scanner(System.in);
        String exit = "y";

        while (exit.equalsIgnoreCase("y")) {
            System.out.print("What do you want to do:\n1 - Read from College\n2 - Write to College\n3 - Update College\n4 - Delete from College\n");

            String operation = scan.nextLine();

            switch(operation){
                case "1":
                    tables();
                    read();
                    break;
                case "2":
                    tables();
                    System.out.println("Write to College");
                    break;
                case "3":
                    tables();
                    System.out.println("Update College");
                    break;
                case "4":
                    tables();
                    System.out.println("Delete from College");
                    break;
                default:
                    System.out.println("Not an option, please enter a number between 1 and 4");
                    break;
            }

            System.out.print("Continue? (y/n): ");
            exit = scan.nextLine();
            System.out.println();
        }
    }

    public static void tables(){    //Writes all tables in College DB to an arraylist, then prints the arraylist
        String selectSQL = "SHOW tables";
        ArrayList<String> tables = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)){

            while (resultSet.next()) {
                tables.add(resultSet.getString("Tables_in_college"));
            }
            System.out.println("Tables in College:");
            System.out.println(Arrays.toString(tables.toArray()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void read() {   //Read from either one or multiple tables in College DB
        System.out.println("1 - Read from one table\n2 - Read from multiple tables");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();

        switch(choice) {
            case "1":   //Read from one chosen table in the college DB
                System.out.println("Type in table you would like to read from: ");
                String input = scan.nextLine();
                String selectSQL = "SELECT * FROM " + input;

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectSQL)) {

                    if (input.equals("student")) {
                        System.out.println("Student ID\tFirst Name\t Last Name\t\tEmail");
                        while (resultSet.next()) {
                            Student student = new Student();
                            student.setStudent_id(resultSet.getString("id"));
                            student.setFirst_name(resultSet.getString("first_name"));
                            student.setLast_name(resultSet.getString("last_name"));
                            student.setEmail(resultSet.getString("email"));

                            System.out.println(student.getStudent_id() + "\t\t\t" + student.getFirst_name() + "\t\t " + student.getLast_name() + "\t\t\t" + student.getEmail());
                        }
                    }

                    if(input.equals("address")) {
                        System.out.println("Address ID\tStudent ID\tPostal Code\t\tCounty\t\tStreet");
                        while (resultSet.next()) {
                            Address address = new Address();
                            address.setAddress_id(resultSet.getString("id"));
                            address.setStudent_id(resultSet.getString("student_id"));
                            address.setPostal_code(resultSet.getString("postal_code"));
                            address.setCounty(resultSet.getString("county"));
                            address.setStreet(resultSet.getString("street"));

                            System.out.println(address.getAddress_id() + "\t\t\t" + address.getStudent_id() + "\t\t\t" + address.getPostal_code() + "\t\t\t" + address.getCounty() + "\t\t" + address.getStreet());
                        }
                    }

                    if(input.equals("course")) {
                        System.out.println("Course ID\tName\t\t\tPoints\t\tLength");
                        while (resultSet.next()) {
                            Courses course = new Courses();
                            course.setCourse_id(resultSet.getString("id"));
                            course.setName(resultSet.getString("name"));
                            course.setPoints(resultSet.getString("points"));
                            course.setLength(resultSet.getString("length"));

                            System.out.println(course.getCourse_id() + "\t\t\t" + course.getName() + "\t\t" + course.getPoints() + "\t\t\t" + course.getLength());
                        }
                    }
                }catch(SQLException e) {
                    System.out.println("Not an option");
                }
                break;
            case "2":   //Read from multiple chosen DB in college
                System.out.println("1 - Read from Student and Address\n2 - Read from Student and Course");
                int mulChoice = scan.nextInt();
                if(mulChoice == 1){   //Joins student and address together and reads it
                    selectSQL = "SELECT s.first_name, s.last_name, s.email, a.postal_code, a.county, a.street FROM student s" +
                                " JOIN address a ON s.id = a.student_id";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(selectSQL)) {

                        System.out.println("First Name\tLast Name\tEmail\t\t\t\tPostal Code\t\tCounty\t\tStreet");
                        while (resultSet.next()) {
                            Address address = new Address();
                            address.setFirst_name(resultSet.getString("first_name"));
                            address.setLast_name(resultSet.getString("last_name"));
                            address.setEmail(resultSet.getString("email"));
                            address.setPostal_code(resultSet.getString("postal_code"));
                            address.setCounty(resultSet.getString("county"));
                            address.setStreet(resultSet.getString("street"));

                            System.out.println(address.getFirst_name() + "\t\t" + address.getLast_name() + "\t\t" + address.getEmail() + "\t\t" + address.getPostal_code() +
                                    "\t\t\t" + address.getCounty() + "\t\t" + address.getStreet());
                        }

                    }catch(SQLException e) {
                        System.out.println("Not an option");
                    }
                }
                if(mulChoice == 2){   //Joins student and course together and reads it
                    selectSQL = "SELECT s.first_name, s.last_name, s.email, c.name, c.points, c.length from student s" +
                            " JOIN course_student i ON s.id = i.student_id" +
                            " JOIN course c ON i.course_id = c.id";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(selectSQL)) {

                        System.out.println("First Name\tLast Name\tEmail\t\t\t\tName\t\t\tPoints\t\tLength");
                        while (resultSet.next()) {
                            Courses course = new Courses();
                            course.setFirst_name(resultSet.getString("first_name"));
                            course.setLast_name(resultSet.getString("last_name"));
                            course.setEmail(resultSet.getString("email"));
                            course.setName(resultSet.getString("name"));
                            course.setPoints(resultSet.getString("points"));
                            course.setLength(resultSet.getString("length"));

                            System.out.println(course.getFirst_name() + "\t\t" + course.getLast_name() + "\t\t" + course.getEmail() + "\t\t" + course.getName() +
                                    "\t\t" + course.getPoints() + "\t\t\t" + course.getLength());
                        }
                    }catch(SQLException e) {
                        System.out.println("Not an option");
                    }
                }
                break;
            default:
                System.out.println("Not a choice");
                break;
        }
    }
}


