package ie.atu.Design1;

import ie.atu.Design1.College;

public class Student implements College {

    private String first_name;
    private String last_name;
    private String email;
    private double course_id;
    private double student_id;
    private double address_id;
    private double grade_id;

    public Student() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCourse_id() {
        return course_id;
    }

    public void setCourse_id(double course_id) {
        this.course_id = course_id;
    }

    public double getStudent_id() {
        return student_id;
    }

    public void setStudent_id(double student_id) {
        this.student_id = student_id;
    }

    public double getAddress_id() {
        return address_id;
    }

    public void setAddress_id(double address_id) {
        this.address_id = address_id;
    }

    public double getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(double grade_id) {
        this.grade_id = grade_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", course_id=" + course_id +
                ", student_id=" + student_id +
                ", address_id=" + address_id +
                ", grade_id=" + grade_id +
                '}';
    }
}
