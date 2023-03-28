package ie.atu;

public class CollegeDB {
    public static College getProduct(String productCode) {
        // In a more realistic application, this code would
        // get the data for the product from a file or database
        // For now, this code just uses if/else statements
        // to return the correct product data

        College p = null;

        if (productCode.equalsIgnoreCase("java"))
        {
            Student myStudent = new Student();
            myStudent.setStudent_id("G00456");
            myStudent.setFirst_name("Jane");
            myStudent.setLast_name("Doe");
            myStudent.setCourse_id("G000123");
            p = myStudent;
        }
        return p;
    }
}
