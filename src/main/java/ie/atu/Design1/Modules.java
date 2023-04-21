package ie.atu.Design1;

public class Modules extends Student {

    private String name;
    private String credits;
    private double module_id;
    private String year;
    private double course_id;
    private String lecturer;



    public Modules() {
        super();
        name = "";
        credits = "";
        module_id = 0;
        year = "";
        course_id = 0;
        lecturer = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public double getModule_id() {
        return module_id;
    }

    public void setModule_id(double module_id) {
        this.module_id = module_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public double getCourse_id() {
        return course_id;
    }

    @Override
    public void setCourse_id(double course_id) {
        this.course_id = course_id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "name='" + name + '\'' +
                ", credits='" + credits + '\'' +
                ", module_id=" + module_id +
                ", year='" + year + '\'' +
                ", course_id=" + course_id +
                ", lecturer='" + lecturer + '\'' +
                '}';
    }
}
