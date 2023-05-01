package ie.atu.Design1;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;

public class CollegeApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the College Database");

        Scanner scan = new Scanner(System.in);
        String exit = "y";

        while (exit.equalsIgnoreCase("y")) {
            System.out.print("What do you want to do:\n1 - Read from College\n2 - Write to College\n3 - Update College\n4 - Delete from College\n");

            String operation = scan.nextLine();

            switch (operation) {
                case "1":   //Read
                    tables();
                    read();
                    break;
                case "2":   //Create
                    tables();
                    write();
                    break;
                case "3":   //Update
                    tables();
                    update();
                    break;
                case "4":   //Delete
                    tables();
                    delete();
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

    public static void tables() {    //Writes all tables in College DB to an arraylist, then prints the arraylist
        String selectSQL = "SHOW tables";
        ArrayList<String> tables = new ArrayList<>();

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

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
        System.out.println("1 - Choose one table\n2 - View all student details\n3 - View all lecturer details");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        Formatter fmt = new Formatter();

        switch (choice) {
            case "1":   //Read from one chosen table in the college DB
                System.out.println("Type in table you would like to read from: ");
                String input = scan.nextLine();
                String selectSQL = "SELECT * FROM " + input;

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectSQL)) {

                    if (input.equals("student")) {
                        fmt.format("%10s %15s %15s %17s", "Student ID", "First Name", "Last Name", "Email");
                        System.out.println(fmt);

                        while (resultSet.next()) {
                            Student student = new Student();
                            fmt = new Formatter();
                            student.setStudent_id(resultSet.getString("id"));
                            student.setFirst_name(resultSet.getString("first_name"));
                            student.setLast_name(resultSet.getString("last_name"));
                            student.setEmail(resultSet.getString("email"));

                            fmt.format("%10s %15s %15s %17s", student.getStudent_id(), student.getFirst_name(), student.getLast_name(), student.getEmail());
                            System.out.println(fmt);
                        }
                    }

                    if (input.equals("address")) {
                        fmt = new Formatter();
                        fmt.format("%10s %15s %15s %15s %17s", "Address ID", "Student ID", "Postal Code", "County", "Street");
                        System.out.println(fmt);

                        while (resultSet.next()) {
                            Address address = new Address();
                            fmt = new Formatter();
                            address.setAddress_id(resultSet.getString("id"));
                            address.setStudent_id(resultSet.getString("student_id"));
                            address.setPostal_code(resultSet.getString("postal_code"));
                            address.setCounty(resultSet.getString("county"));
                            address.setStreet(resultSet.getString("street"));

                            fmt.format("%10s %15s %15s %15s %17s", address.getAddress_id(), address.getStudent_id(), address.getPostal_code(), address.getCounty(), address.getStreet());
                            System.out.println(fmt);
                        }
                    }

                    if (input.equals("course")) {
                        fmt = new Formatter();
                        fmt.format("%10s %15s %15s %15s", "Course ID", "Name", "Points", "Length");
                        System.out.println(fmt);

                        while (resultSet.next()) {
                            Courses course = new Courses();
                            fmt = new Formatter();
                            course.setCourse_id(resultSet.getString("id"));
                            course.setName(resultSet.getString("name"));
                            course.setPoints(resultSet.getString("points"));
                            course.setLength(resultSet.getString("length"));

                            fmt.format("%10s %15s %15s %15s", course.getCourse_id(), course.getName(), course.getPoints(), course.getLength());
                            System.out.println(fmt);
                        }
                    }

                    if (input.equals("lecturer")) {
                        fmt = new Formatter();
                        fmt.format("%10s %14s %15s %22s", "Lecturer ID", "First Name", "Last Name", "Email");
                        System.out.println(fmt);

                        while (resultSet.next()) {
                            Lecturer lecturer = new Lecturer();
                            fmt = new Formatter();
                            lecturer.setLecturer_id(resultSet.getString("id"));
                            lecturer.setFirst_name(resultSet.getString("first_name"));
                            lecturer.setLast_name(resultSet.getString("last_name"));
                            lecturer.setEmail(resultSet.getString("email"));

                            fmt.format("%10s %15s %15s %22s", lecturer.getLecturer_id(), lecturer.getFirst_name(), lecturer.getLast_name(), lecturer.getEmail());
                            System.out.println(fmt);
                        }
                    }

                    if (input.equals("module")) {
                        fmt = new Formatter();
                        fmt.format("%10s %20s %15s %15s", "Module ID", "Name", "Credits", "Year");
                        System.out.println(fmt);

                        while (resultSet.next()) {
                            Module module = new Module();
                            fmt = new Formatter();
                            module.setModule_id(resultSet.getString("id"));
                            module.setName(resultSet.getString("name"));
                            module.setCredits(resultSet.getString("credits"));
                            module.setYear(resultSet.getString("year"));

                            fmt.format("%10s %20s %15s %15s", module.getModule_id(), module.getName(), module.getCredits(), module.getYear());
                            System.out.println(fmt);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Not an option");
                }
                break;
            case "2":
                //Joins student course and address
                selectSQL = "SELECT s.id, s.first_name, s.last_name, s.email, a.postal_code, a.county, a.street, c.name, c.points, c.length FROM student s" +
                        " JOIN address a ON s.id = a.student_id" +
                        " JOIN course_student i ON s.id = i.student_id" +
                        " JOIN course c ON i.course_id = c.id";

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectSQL)) {

                    fmt = new Formatter();
                    fmt.format("%10s %15s %15s %17s %15s %15s %17s %15s %15s %15s", "Student ID", "First Name", "Last Name", "Email", "Postal Code", "County", "Street", "Name", "Points", "Length");
                    System.out.println(fmt);
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        Address address = new Address();
                        fmt = new Formatter();
                        course.setStudent_id(resultSet.getString("id"));
                        course.setFirst_name(resultSet.getString("first_name"));
                        course.setLast_name(resultSet.getString("last_name"));
                        course.setEmail(resultSet.getString("email"));
                        address.setPostal_code(resultSet.getString("postal_code"));
                        address.setCounty(resultSet.getString("county"));
                        address.setStreet(resultSet.getString("street"));
                        course.setName(resultSet.getString("name"));
                        course.setPoints(resultSet.getString("points"));
                        course.setLength(resultSet.getString("length"));

                        fmt.format("%10s %15s %15s %17s %15s %15s %17s %15s %15s %15s", course.getStudent_id(), course.getFirst_name(), course.getLast_name(), course.getEmail(),
                                address.getPostal_code(), address.getCounty(), address.getStreet(), course.getName(), course.getPoints(), course.getLength());
                        System.out.println(fmt);
                    }

                } catch (SQLException e) {
                    System.out.println("Failed to read from database");
                }
                break;

            case "3":
                //Joins lecturer and module
                selectSQL = "SELECT l.id, l.first_name, l.last_name, l.email, m.name, m.credits, m.year FROM lecturer l" +
                        " JOIN lecturer_module i ON l.id = i.lecturer_id" +
                        " JOIN module m ON i.module_id = m.id";

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectSQL)) {

                    fmt = new Formatter();
                    fmt.format("%10s %14s %15s %22s %20s %15s %15s", "Lecturer ID", "First Name", "Last Name", "Email", "Module Name", "Credits", "Year");
                    System.out.println(fmt);
                    while (resultSet.next()) {
                        Module module = new Module();
                        fmt = new Formatter();
                        module.setLecturer_id(resultSet.getString("id"));
                        module.setFirst_name(resultSet.getString("first_name"));
                        module.setLast_name(resultSet.getString("last_name"));
                        module.setEmail(resultSet.getString("email"));
                        module.setName(resultSet.getString("name"));
                        module.setCredits(resultSet.getString("credits"));
                        module.setYear(resultSet.getString("year"));

                        fmt.format("%10s %15s %15s %22s %20s %15s %15s", module.getLecturer_id(), module.getFirst_name(), module.getLast_name(), module.getEmail(),
                                module.getName(), module.getCredits(), module.getYear());
                        System.out.println(fmt);
                    }

                } catch (SQLException e) {
                    System.out.println("Failed to read from database");
                }
                break;

            default:
                System.out.println("Not a choice");
                break;
        }
    }

    public static void write() {
        System.out.println("What information would you like to add to the database\n1 - New Student\n2 - New Course\n3 - Add a course to an existing student\n4 - New lecturer\n5 - New Module\n6 - Add a lecturer to an existing module");
        Scanner keyboard = new Scanner(System.in);
        String option = keyboard.nextLine();

        switch (option) {

            // Enter information for student table
            case "1":
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    PreparedStatement stmt = connection.prepareStatement("INSERT INTO student(first_name, last_name, email) VALUES (?,?,?)");
                    Scanner input = new Scanner(System.in);
                    Student student = new Student();
                    System.out.println("Please enter the first name");
                    student.setFirst_name(input.nextLine());
                    stmt.setString(1, student.getFirst_name());
                    System.out.println("Please enter the last name");
                    student.setLast_name(input.nextLine());
                    stmt.setString(2, student.getLast_name());
                    System.out.println("Please enter the email");
                    student.setEmail(input.nextLine());
                    stmt.setString(3, student.getEmail());
                    stmt.executeUpdate();

                    //gets the student id of the student that was just created
                    ResultSet resultSet = stmt.executeQuery("SELECT id FROM student WHERE first_name = '" + student.getFirst_name() + "'");
                    Address address = new Address();
                    while (resultSet.next()) {
                        address.setStudent_id(resultSet.getString("id"));
                    }

                    stmt = connection.prepareStatement("INSERT INTO address(student_id, postal_code, county, street) VALUES (?,?,?,?)");
                    stmt.setString(1, address.getStudent_id());
                    System.out.println("Please enter postal code");
                    address.setPostal_code(input.nextLine());
                    stmt.setString(2, address.getPostal_code());
                    System.out.println("Please enter county");
                    address.setCounty(input.nextLine());
                    stmt.setString(3, address.getCounty());
                    System.out.println("Please enter the street");
                    address.setStreet(input.nextLine());
                    stmt.setString(4, address.getStreet());
                    stmt.executeUpdate();

                    resultSet = stmt.executeQuery("SELECT id, name FROM course");
                    System.out.println("Choice of courses: ");
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        course.setCourse_id(resultSet.getString("id"));
                        course.setName(resultSet.getString("name"));
                        System.out.println(course.getCourse_id() + "\t" + course.getName());
                    }

                    Courses course = new Courses();
                    System.out.println("Please enter the ID of the course");
                    course.setCourse_id(input.nextLine());
                    stmt = connection.prepareStatement("INSERT INTO course_student(student_id, course_id) VALUES (?,?)");
                    stmt.setString(1, address.getStudent_id());
                    stmt.setString(2, course.getCourse_id());
                    stmt.executeUpdate();

                    System.out.println("New student has been added");

                } catch (SQLException e) {
                    System.out.println("The system failed to add the information");
                    e.printStackTrace();
                }
                break;

            // Add course information to the database
            case "2":
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    PreparedStatement record = connection.prepareStatement("INSERT INTO course(name, points, length) VALUES (?,?,?)");
                    Scanner scan = new Scanner(System.in);
                    Courses course = new Courses();
                    System.out.println("Please enter the name for the course");
                    course.setName(scan.nextLine());
                    record.setString(1, course.getName());
                    System.out.println("Please enter the points for the course");
                    course.setPoints(scan.nextLine());
                    record.setString(2, course.getPoints());
                    System.out.println("Please enter the length of the course");
                    course.setLength(scan.nextLine());
                    record.setString(3, course.getLength());
                    record.executeUpdate();
                    System.out.println("Information has been added to the course table");

                } catch (SQLException e) {
                    System.out.println("The system failed to add the information");
                    e.printStackTrace();
                }
                break;

            case "3":
                ArrayList<String> studentID = new ArrayList<>();
                ArrayList<String> courseID = new ArrayList<>();
                String courseInput = "";
                String studentInput = "";
                int correctID = 0;
                Scanner scan = new Scanner(System.in);

                try (Connection connection = DatabaseUtils.getConnection()) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id FROM student");
                    System.out.print("Select the ID of the student that needs a course: ");
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudent_id(resultSet.getString("id"));
                        studentID.add(resultSet.getString("id"));
                        System.out.print(student.getStudent_id() + ", ");
                    }

                    while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                        studentInput = scan.nextLine();
                        if (studentID.contains(studentInput)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    resultSet = statement.executeQuery("SELECT id, name FROM course");
                    System.out.print("Select the ID of the course: ");
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        course.setCourse_id(resultSet.getString("id"));
                        courseID.add(resultSet.getString("id"));
                        System.out.print(course.getCourse_id() + ", ");
                    }

                    correctID = 0;
                    while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                        courseInput = scan.nextLine();
                        if (courseID.contains(courseInput)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    PreparedStatement pStatement = connection.prepareStatement("INSERT INTO course_student(student_id, course_id) VALUES (?,?)");
                    pStatement.setString(1, studentInput);
                    pStatement.setString(2, courseInput);
                    pStatement.executeUpdate();
                    System.out.println("Successfully added course to student");

                } catch (SQLException e) {
                    System.out.println("Failed to set new course");
                }
                break;

            case "4":
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    PreparedStatement record = connection.prepareStatement("INSERT INTO lecturer(first_name, last_name, email) VALUES (?,?,?)");
                    scan = new Scanner(System.in);
                    Lecturer lecturer = new Lecturer();
                    System.out.println("Please enter first name");
                    lecturer.setFirst_name(scan.nextLine());
                    record.setString(1, lecturer.getFirst_name());
                    System.out.println("Please enter last name");
                    lecturer.setLast_name(scan.nextLine());
                    record.setString(2, lecturer.getLast_name());
                    System.out.println("Please enter email");
                    lecturer.setEmail(scan.nextLine());
                    record.setString(3, lecturer.getEmail());
                    record.executeUpdate();
                    System.out.println("New lecturer has been added");

                } catch (SQLException e) {
                    System.out.println("The system failed to add the information");
                    e.printStackTrace();
                }
                break;
            case "5":
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    PreparedStatement record = connection.prepareStatement("INSERT INTO module(name, credits, year) VALUES (?,?,?)");
                    scan = new Scanner(System.in);
                    Module module = new Module();
                    System.out.println("Please enter name");
                    module.setName(scan.nextLine());
                    record.setString(1, module.getName());
                    System.out.println("Please enter credits");
                    module.setCredits(scan.nextLine());
                    record.setString(2, module.getCredits());
                    System.out.println("Please enter year");
                    module.setYear(scan.nextLine());
                    record.setString(3, module.getYear());
                    record.executeUpdate();
                    System.out.println("New module has been added");

                } catch (SQLException e) {
                    System.out.println("The system failed to add the information");
                    e.printStackTrace();
                }
                break;

            case "6":
                ArrayList<String> lecturerID = new ArrayList<>();
                ArrayList<String> moduleID = new ArrayList<>();
                String lecturerInput = "";
                String moduleInput = "";
                correctID = 0;
                scan = new Scanner(System.in);

                try (Connection connection = DatabaseUtils.getConnection()) {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id FROM module");
                    System.out.print("Select the ID of the module that needs a lecturer: ");
                    while (resultSet.next()) {
                        Module module = new Module();
                        module.setModule_id(resultSet.getString("id"));
                        moduleID.add(resultSet.getString("id"));
                        System.out.print(module.getModule_id() + ", ");
                    }

                    while (correctID == 0) {
                        moduleInput = scan.nextLine();
                        if (moduleID.contains(moduleInput)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    resultSet = statement.executeQuery("SELECT id FROM lecturer");
                    System.out.print("Select the ID of the lecturer: ");
                    while (resultSet.next()) {
                        Lecturer lecturer = new Lecturer();
                        lecturer.setLecturer_id(resultSet.getString("id"));
                        lecturerID.add(resultSet.getString("id"));
                        System.out.print(lecturer.getLecturer_id() + ", ");
                    }

                    correctID = 0;
                    while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                        lecturerInput = scan.nextLine();
                        if (lecturerID.contains(lecturerInput)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    PreparedStatement pStatement = connection.prepareStatement("INSERT INTO lecturer_module(module_id, lecturer_id) VALUES (?,?)");
                    pStatement.setString(1, moduleInput);
                    pStatement.setString(2, lecturerInput);
                    pStatement.executeUpdate();
                    System.out.println("Successfully added module to lecturer");

                } catch (SQLException e) {
                    System.out.println("Failed to add the information");
                }
                break;

            default:
                System.out.println("Invalid option. Please try again");
                break;

        }
    }

    public static void update() {
        System.out.println("Choose what you would like to update");
        System.out.println("1 - Student Details\n2 - Student Address\n3 - Student Course\n4 - Course\n5 - Lecturer Details\n6 - Lecturer Modules\n7 - Module");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        String setColumn = "";
        String updateSQL;
        int correctID = 0;
        String inputID = "";
        String exit;
        ArrayList<String> array = new ArrayList<>();

        switch (choice) {
            case "1":
                System.out.println("Please enter student ID to update their details:");
                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT id FROM student")) {
                    System.out.print("Student IDs: ");
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudent_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(student.getStudent_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                exit = "1";
                while (exit.equals("1")) {
                    System.out.println("Please select the column you would like to update:\n1 - First Name\n2 - Last Name\n3 - Email");
                    int columnOption = scan.nextInt();
                    String bugfix = scan.nextLine(); //Required to make the next scan.nextLine() work
                    if (columnOption == 1) {
                        setColumn = "first_name";
                    }
                    if (columnOption == 2) {
                        setColumn = "last_name";
                    }
                    if (columnOption == 3) {
                        setColumn = "email";
                    }

                    System.out.println("Type in the updated information: ");
                    String update = scan.nextLine();

                    updateSQL = "UPDATE student SET " + setColumn + " = '" + update + "' WHERE id = '" + inputID + "'";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        System.out.println("Rows updated: " + rowsUpdated);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press 1 to update another column\nOtherwise press any other number to exit");
                    exit = scan.nextLine();
                }
                break;

            case "2":
                System.out.println("Please enter student ID to update their address:");

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT student_id FROM address")) {
                    System.out.print("Student IDs: ");
                    while (resultSet.next()) {
                        Address address = new Address();
                        address.setStudent_id(resultSet.getString("student_id"));
                        array.add(resultSet.getString("student_id"));
                        System.out.print(address.getStudent_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) {
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                exit = "1";
                while (exit.equals("1")) {
                    System.out.println("Please select the column you would like to update:\n1 - Postal Code\n2 - County\n3 - Street");
                    int columnOption = scan.nextInt();
                    String bugfix = scan.nextLine(); //Required to make the next scan.nextLine() work
                    if (columnOption == 1) {
                        setColumn = "postal_code";
                    }
                    if (columnOption == 2) {
                        setColumn = "county";
                    }
                    if (columnOption == 3) {
                        setColumn = "street";
                    }

                    System.out.println("Type in the updated information: ");
                    String update = scan.nextLine();

                    updateSQL = "UPDATE address SET " + setColumn + " = '" + update + "' WHERE student_id = '" + inputID + "'";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        System.out.println("Rows updated: " + rowsUpdated);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press 1 to update another column\nOtherwise press any other number to exit");
                    exit = scan.nextLine();
                }
                break;
            case "3":
                System.out.println("Please enter student ID to update their course");

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT student_id FROM course_student")) {
                    System.out.print("Student IDs: ");
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        course.setStudent_id(resultSet.getString("student_id"));
                        array.add(resultSet.getString("student_id"));
                        System.out.print(course.getStudent_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) {
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement()) {

                    ResultSet resultSet = statement.executeQuery("SELECT id, name FROM course");
                    System.out.println("Choice of courses: ");
                    Courses course = new Courses();
                    while (resultSet.next()) {
                        course.setCourse_id(resultSet.getString("id"));
                        course.setName(resultSet.getString("name"));
                        System.out.println(course.getCourse_id() + "\t" + course.getName());
                    }

                    System.out.println("Please enter the ID of the course");
                    course.setCourse_id(scan.nextLine());

                    updateSQL = "UPDATE course_student SET course_id = '" + course.getCourse_id() + "' WHERE student_id = '" + inputID + "'";
                    int rowsUpdated = statement.executeUpdate(updateSQL);
                    System.out.println("Rows updated: " + rowsUpdated);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            case "4":
                System.out.println("Please enter the ID of the course you want to update:");

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT id FROM course")) {
                    System.out.print("Course IDs: ");
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        course.setCourse_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(course.getCourse_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) {
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                exit = "1";
                while (exit.equals("1")) {
                    System.out.println("Please select the column you would like to update:\n1 - Name\n2 - Points\n3 - Length");
                    int columnOption = scan.nextInt();
                    String bugfix = scan.nextLine(); //Required to make the next scan.nextLine() work
                    if (columnOption == 1) {
                        setColumn = "name";
                    }
                    if (columnOption == 2) {
                        setColumn = "points";
                    }
                    if (columnOption == 3) {
                        setColumn = "length";
                    }

                    System.out.println("Type in the updated information: ");
                    String update = scan.nextLine();

                    updateSQL = "UPDATE course SET " + setColumn + " = '" + update + "' WHERE id = '" + inputID + "'";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        System.out.println("Rows updated: " + rowsUpdated);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press 1 to update another column\nOtherwise press any other button to exit");
                    exit = scan.nextLine();
                }
                break;

            case "5":
                //This will be to update the lecturer details (first_name, last_name, email). Case 1 is very similar to how it should look
                System.out.println("Please enter lecture to update their details:");
                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT id FROM lecture")) {
                    System.out.print("Lectures ID: ");
                    while (resultSet.next()) {
                        Lecturer lecturer = new Lecturer();
                        lecturer.setLecturer_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(lecturer.getLecturer_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                exit = "1";
                while (exit.equals("1")) {
                    System.out.println("Please select the column you would like to update:\n1 - First Name\n2 - Last Name\n3 - Email");
                    int columnOption = scan.nextInt();
                    String bugfix = scan.nextLine(); //Required to make the next scan.nextLine() work
                    if (columnOption == 1) {
                        setColumn = "first_name";
                    }
                    if (columnOption == 2) {
                        setColumn = "last_name";
                    }
                    if (columnOption == 3) {
                        setColumn = "email";
                    }

                    System.out.println("Type in the updated information: ");
                    String update = scan.nextLine();

                    updateSQL = "UPDATE lecture SET " + setColumn + " = '" + update + "' WHERE id = '" + inputID + "'";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        System.out.println("Rows updated: " + rowsUpdated);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press 1 to update another column\nOtherwise press any other number to exit");
                    exit = scan.nextLine();
                }
                break;

            case "6":
                //This will be to update lecturer module(what module they teach). Case 3 is similar to how it should look.
                //Each module can have only one lecturer, while a lecturer can have more than one module, i.e. prompt user for the module id first and then prompt for the lecturer
                break;

            case "7":
                //This case will be to update the module details(name, credits, year). Case 4 is very similar to how it should look
                System.out.println("Please enter the Module details you want to update:");

                try (Connection connection = DatabaseUtils.getConnection();
                     Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT Module")) {
                    System.out.print("module IDs: ");
                    while (resultSet.next()) {
                        Module module = new Module();
                        module.setModule_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(module.getModule_id() + ", ");
                    }
                } catch (SQLException e) {
                    System.out.println("Error getting IDs");
                }

                while (correctID == 0) {
                    inputID = scan.nextLine();
                    if (array.contains(inputID)) {
                        correctID = 1;
                    } else {
                        System.out.println("Please enter an existing ID");
                    }
                }

                exit = "1";
                while (exit.equals("1")) {
                    System.out.println("Please select the column you would like to update:\n1 - Name\n2 - Credits\n3 - Year");
                    int columnOption = scan.nextInt();
                    String bugfix = scan.nextLine(); //Required to make the next scan.nextLine() work
                    if (columnOption == 1) {
                        setColumn = "name";
                    }
                    if (columnOption == 2) {
                        setColumn = "credits";
                    }
                    if (columnOption == 3) {
                        setColumn = "year";
                    }

                    System.out.println("Type in the updated information: ");
                    String update = scan.nextLine();

                    updateSQL = "UPDATE module SET " + setColumn + " = '" + update + "' WHERE id = '" + inputID + "'";

                    try (Connection connection = DatabaseUtils.getConnection();
                         Statement statement = connection.createStatement()) {
                        int rowsUpdated = statement.executeUpdate(updateSQL);
                        System.out.println("Rows updated: " + rowsUpdated);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Press 1 to update another column\nOtherwise press any other button to exit");
                    exit = scan.nextLine();
                }
                break;

            default:
                System.out.println("Not a choice");
                break;
        }
    }

    public static void delete() {

        System.out.println("Please select what you would like to delete\n1 - Student Information\n2 - Course\n3 - Lecturer Information\n4 - Module");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        ArrayList<String> array = new ArrayList<>();

        switch (choice) {
            case "1":
                System.out.println("****Deleting from student will also delete address associated with the student****");
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    String input = "";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id FROM student");
                    System.out.print("Please select the ID of the student you wish to delete: ");
                    while (resultSet.next()) {
                        Student student = new Student();
                        student.setStudent_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(student.getStudent_id() + ", ");
                    }

                    int correctID = 0;
                    while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                        input = scan.nextLine();
                        if (array.contains(input)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    PreparedStatement stmt = connection.prepareStatement("DELETE FROM address WHERE student_id = " + input);
                    stmt.executeUpdate();
                    stmt = connection.prepareStatement("DELETE FROM course_student WHERE student_id = " + input);
                    stmt.executeUpdate();
                    stmt = connection.prepareStatement("DELETE FROM student WHERE id = " + input);
                    stmt.executeUpdate();
                    System.out.println("Student successfully deleted");

                }catch (SQLException e){
                        System.out.println("The system failed to remove the information");
                        e.printStackTrace();
                }
                break;

            case "2":
                try {
                    Connection connection = DatabaseUtils.getConnection();
                    String input = "";
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT id FROM course");
                    System.out.print("Please select the ID of the course you wish to delete: ");
                    while (resultSet.next()) {
                        Courses course = new Courses();
                        course.setCourse_id(resultSet.getString("id"));
                        array.add(resultSet.getString("id"));
                        System.out.print(course.getCourse_id() + ", ");
                    }

                    int correctID = 0;
                    while (correctID == 0) { //Checks if the ID inputted is actually an option using an arraylist
                        input = scan.nextLine();
                        if (array.contains(input)) {
                            correctID = 1;
                        } else {
                            System.out.println("Please enter an existing ID");
                        }
                    }

                    PreparedStatement stmt = connection.prepareStatement("DELETE FROM course_student WHERE course_id = " + input);
                    stmt.executeUpdate();
                    stmt = connection.prepareStatement("DELETE FROM course WHERE id = " + input);
                    stmt.executeUpdate();
                    System.out.println("Course successfully deleted");

                } catch (SQLException e) {
                    System.out.println("The system failed to remove the information");
                    e.printStackTrace();
                }
                break;

            case "3":
                //This case will be for deleting lecturer information. Very similar to case 1.
                //Remember you have to delete from the lecturer_module table first before deleting from the lecturer table
                break;

            case "4":
                //This case will be for deleting a module. Very similar to case 2.
                //Remember you have to delete from lecturer_module table first before deleting from the lecturer table
                break;

            default:
                System.out.println("Not a choice");
                break;
        }
    }
}
