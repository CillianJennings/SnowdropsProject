package ie.atu;

public class Student {

    private String first_name;
    private String last_name;
    private String address;
    private String email;
    private String grade;
    private double id;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student" + '\n' +
                "First_name: " + first_name + '\n' +
                "Last_name: " + last_name + '\n' +
                "Address: " + address + '\n' +
                "Email: " + email + '\n' +
                "Grade: " + grade + '\n' +
                "ID: " + id + '\n';
    }
}
