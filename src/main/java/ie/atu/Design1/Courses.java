package ie.atu.Design1;

public class Courses extends Student {

    private double id;
    private String name;
    private int points;
    private int length;

    public Courses() {
        super();
        id = 0;
        name = "";
        points = 0;
        length = 0;

    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Courses" +
                "id= " + id + '\n'+
                "name= " + name + '\n' +
                "points= " + points + '\n'+
                "length= " + length +'\n';
    }
}
