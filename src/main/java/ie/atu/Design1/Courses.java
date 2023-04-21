package ie.atu.Design1;

public class Courses extends Student {

    private double course_id;
    private String name;
    private double points;
    private String length;

    public Courses() {
        super();
        course_id = 0;
        name = "";
        points = 0;
        length = "";
    }

    public double getCourse_id() {
        return course_id;
    }

    public void setCourse_id(double course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
