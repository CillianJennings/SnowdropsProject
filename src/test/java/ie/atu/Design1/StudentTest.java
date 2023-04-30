package ie.atu.Design1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    Student firstStudent, secondStudent;

    @Test
    public void testSetAndGetNames()
    {
        firstStudent = new Student();
        firstStudent.setFirst_name("John");
        firstStudent.setLast_name("Doe");

        assertEquals("John", firstStudent.getFirst_name());
        assertEquals("Doe", firstStudent.getLast_name());
    }
}
