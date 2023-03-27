package ie.atu;

public class Modules extends Student, Lecture, Modules{

    private String name;
    private String credits;
    private String module_id;
    private String year;
    private String course_id;
    private String lecturer;



    public Modules() {
        name = "";
        credits = "";
        module_id = "";
        year = "";
        course_id = "";
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

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Modules(String name, String credits, String module_id, String year, String course_id, String lecturer) {
        this.name = name;
        this.credits = credits;
        this.module_id = module_id;
        this.year = year;
        this.course_id = course_id;
        this.lecturer = lecturer;
    }
}
