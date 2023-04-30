package ie.atu.Design1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressTest {

    Address firstAddress;

    @Test
    public void testSetAndGet()
    {
        firstAddress = new Address();
        String postal_code = "H91R7XN";
        firstAddress.setPostal_code(postal_code);

        assertEquals("H91R7XN", firstAddress.getPostal_code());
    }
}
