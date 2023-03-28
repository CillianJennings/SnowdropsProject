package ie.atu.Design1;

public class CollegeDB {
    public static Student getStudent(String studentID) {

        Student s = null;

        if (studentID.equalsIgnoreCase("java"))
        {
            Student myStudent = new Student();
            myStudent.setStudent_id(00456);
            myStudent.setFirst_name("Jane");
            myStudent.setLast_name("Doe");
            myStudent.setCourse_id(00012);
            s = myStudent;
        }
        return s;
    }
}
