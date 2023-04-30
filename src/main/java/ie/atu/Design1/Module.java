package ie.atu.Design1;

public class Module extends Lecturer {

    private String module_id;
    private String name;
    private String credits;
    private String year;

    public Module() {
        super();
        module_id = "";
        name = "";
        credits = "";
        year = "";
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
