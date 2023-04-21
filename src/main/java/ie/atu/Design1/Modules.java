package ie.atu.Design1;

public class Modules extends Student {

    private double module_id;
    private String name;
    private double credits;
    private String year;

    public Modules() {
        super();
        module_id = 0;
        name = "";
        credits = 0;
        year = "";
    }

    public double getModule_id() {
        return module_id;
    }

    public void setModule_id(double module_id) {
        this.module_id = module_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
