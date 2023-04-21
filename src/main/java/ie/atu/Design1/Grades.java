package ie.atu.Design1;

public class Grades {

    private int grade_id;
    private int student_id;
    private int module_id;
    private int grade;
    private int year;

    public int getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(int grade_id) {
        this.grade_id = grade_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "grade_id=" + grade_id +
                ", student_id=" + student_id +
                ", module_id=" + module_id +
                ", grade=" + grade +
                ", year=" + year +
                '}';
    }
}
