package ie.atu;

import java.util.Scanner;
public class CollegeApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the College Database");

        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            System.out.print("Enter student ID: ");
            String studentID = sc.nextLine();
        }

        Student s = CollegeDB.getStudent(student_id);

        System.out.println();
        if (s != null) {
            System.out.println(s.toString());
            System.out.println("Student ID: " + s.getStudent_id());
        }
        else {
            System.out.println("No product matches this product code.");
        }
    }
}
