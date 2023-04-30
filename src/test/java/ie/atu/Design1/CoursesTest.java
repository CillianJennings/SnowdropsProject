package ie.atu.Design1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoursesTest {

    Courses firstCourse;

    @Test
    public void testSetAndGet()
    {
        firstCourse = new Courses();
        String points = "300 Points";
        firstCourse.setPoints(points);

        assertEquals("300 Points", firstCourse.getPoints());
    }
}
