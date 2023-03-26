package ie.atu;

public class Courses {

    private double id;
    private String name;
    private int points;
    private int length;

    public Courses() {
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