package ie.atu.Design1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LecturerTest {

    Lecturer lecturer;

    @Test
    public void testSetandGetEmail()
    {
        lecturer = new Lecturer();
        String email = "lecturer@atu.ie";
        String secEmail = "student@atu.ie";
        lecturer.setEmail(email);

        assertEquals("lecturer@atu.ie", lecturer.getEmail());
    }
}
