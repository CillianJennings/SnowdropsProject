package ie.atu.Design1;

public class Courses extends Student {

    private String course_id;
    private String name;
    private String points;
    private String length;

    public Courses() {
        super();
        course_id = "";
        name = "";
        points = "";
        length = "";
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
