package ie.atu;

import java.util.Scanner;

public class Lecture extends Student{

    private String module_name;
    private String lecturer_name;
    private int module_id;

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

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getLecurer_id() {
        return lecurer_id;
    }

    public void setLecurer_id(int lecurer_id) {
        this.lecurer_id = lecurer_id;
    }

    private int lecurer_id;

    @Override
    public String toString() {
        return "Lecture{" +
                "module_name='" + module_name + '\'' +
                ", lecturer_name='" + lecturer_name + '\'' +
                ", module_id=" + module_id +
                ", lecurer_id=" + lecurer_id +
                '}';
    }
}
