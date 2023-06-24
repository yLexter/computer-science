package utils;

public class Constants {

    public static final String exitInputString = "-s";
    public static final int startOptionsIndex = 1;

    public static final class ErrorMessage {
        public static final String DATABASE_CONNECTION_ERROR = "Error connecting to the database";
        public static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists";
        public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
        public static final String EMPLOYEE_NOT_AUTHENTICATE = "The registation or password is incorret";
        private ErrorMessage() {}
    }



}
