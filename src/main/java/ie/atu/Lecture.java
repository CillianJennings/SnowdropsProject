package ie.atu;

public class Lecture extends Student{

    private String module_name;
    private String lecturer_name;
    private double module_id;

    public Lecture() {
        super();
        module_name = "";
        lecturer_name = "";
        module_id = 0;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getLecturer_name() {
        return lecturer_name;
    }

    public void setLecturer_name(String lecturer_name) {
        this.lecturer_name = lecturer_name;
    }

    public double getModule_id() {
        return module_id;
    }

    public void setModule_id(double module_id) {
        this.module_id = module_id;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "module_name='" + module_name + '\'' +
                ", lecturer_name='" + lecturer_name + '\'' +
                ", module_id=" + module_id;
    }
}
