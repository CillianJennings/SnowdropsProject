package ie.atu.Design1;

public class Lecture extends Student {

    private double lecturer_id;
    private String first_name;
    private String last_name;

    public Lecture() {
        super();
        lecturer_id = 0;
        first_name = "";
        last_name = "";
    }

    public double getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(double lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    @Override
    public String getFirst_name() {
        return first_name;
    }

    @Override
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Override
    public String getLast_name() {
        return last_name;
    }

    @Override
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
