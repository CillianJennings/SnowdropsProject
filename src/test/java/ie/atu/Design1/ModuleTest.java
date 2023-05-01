package ie.atu.Design1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModuleTest {

    Module module;

    @Test
    public void testSetandGetModule_ID()
    {
        module = new Module();
        String module_id = "ESOEG";
        module.setModule_id(module_id);

        assertEquals("ESOEG", module.getModule_id());
    }
}
