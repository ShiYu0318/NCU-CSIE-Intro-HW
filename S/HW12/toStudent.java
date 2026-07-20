import java.util.Scanner;

public class toStudent {

    public static void main(String[] args) {
        System.out.println("Enter main");

        Scanner scanner = new Scanner(System.in);

//        System.out.println("Choose an option:");
//        System.out.println("1. Throw ArithmeticException");
//        System.out.println("2. Throw NullPointerException");
//        System.out.println("3. Throw NumberFormatException");
//        System.out.println("4. Throw DataNotFoundException");
//        System.out.println("5. Throw PermissionDeniedException");
//        System.out.println("6. Throw CriticalSystemException");
//        System.out.println("7. No Exception");
//        System.out.println("8. Throw LoginFailedException");
//        System.out.print("> ");

        int option = scanner.nextInt();

        try {
            controller(option);

        }
        // TODO:
        // Catch CriticalSystemException here.
        //
        // Required output:
        // main catches CriticalSystemException
        // Message: ...
        //
        // If this exception has a cause, also print:
        // Cause: ...
        // Cause message: ...
        //
        // Hint:
        // e.getCause()

        // TODO:
        // Catch any unexpected Exception here.
        //
        // Required output:
        // main catches unexpected Exception
        // Type: ...
        // Message: ...

        finally {
            System.out.println("main finally");
        }

        System.out.println("Exit main");
        scanner.close();
    }

    public static void controller(int option)
            throws DataNotFoundException, PermissionDeniedException, LoginFailedException {

        System.out.println("Enter controller");

        try {
            service(option);

        }
        // TODO:
        // Use multi-catch here.
        //
        // Requirement:
        // NumberFormatException and LoginFailedException
        // must be handled by controller().
        //
        // Required output:
        // controller catches ExceptionName
        // Message: ...
        //
        // Hint:
        // catch (NumberFormatException | LoginFailedException e) {
        //     ...
        // }

        // TODO:
        // Catch PermissionDeniedException here.
        //
        // Required output:
        // controller catches PermissionDeniedException
        // Message: ...

        finally {
            System.out.println("controller finally");
        }

        System.out.println("Exit controller");
    }

    public static void service(int option)
            throws DataNotFoundException, PermissionDeniedException, LoginFailedException {

        System.out.println("Enter service");

        try {
            repository(option);

        }
        // TODO:
        // Catch NullPointerException here.
        //
        // Required output:
        // service catches NullPointerException
        // Message: ...

        // TODO:
        // Catch DataNotFoundException here.
        //
        // Required output:
        // service catches DataNotFoundException
        // Message: ...

        finally {
            System.out.println("service finally");
        }

        System.out.println("Exit service");
    }

    public static void repository(int option)
            throws DataNotFoundException, PermissionDeniedException, LoginFailedException {

        System.out.println("Enter repository");

        try {
            database(option);

        }
        // TODO:
        // Catch ArithmeticException here.
        //
        // Requirement:
        // 1. Print "repository catches ArithmeticException"
        // 2. Print "repository converts it to CriticalSystemException"
        // 3. Rethrow CriticalSystemException
        // 4. Must use cause chaining

        finally {
            System.out.println("repository finally");
        }

        System.out.println("Exit repository");
    }

    public static void database(int option)
            throws DataNotFoundException, PermissionDeniedException, LoginFailedException {

        System.out.println("Enter database");

        try {
            if (option == 1) {
                // Print "database throws ArithmeticException"
                // Throw ArithmeticException with message:
                // "Division by zero"
                System.out.println("database throws ArithmeticException");
                throw new ArithmeticException("Division by zero");

            } else if (option == 2) {
                System.out.println("database throws NullPointerException");
                // TODO:
                // Throw NullPointerException with message:
                // "Object is null"

            } else if (option == 3) {
                System.out.println("database throws NumberFormatException");
                // TODO:
                // Throw NumberFormatException with message:
                // "Cannot parse input"

            } else if (option == 4) {
                System.out.println("database throws DataNotFoundException");
                // TODO:
                // Throw DataNotFoundException with message:
                // "Data id not found"

            } else if (option == 5) {
                System.out.println("database throws PermissionDeniedException");
                // TODO:
                // Throw PermissionDeniedException with message:
                // "User has no permission"

            } else if (option == 6) {
                System.out.println("database throws CriticalSystemException");
                // TODO:
                // Throw CriticalSystemException with message:
                // "Database server crashed"

            } else if (option == 7) {
                System.out.println("database runs normally");

            } else if (option == 8) {
                System.out.println("database throws LoginFailedException");
                // TODO:
                // Throw LoginFailedException with message:
                // "Login failed: invalid username or password"

            } else {
                System.out.println("Invalid option, no exception is thrown");
            }
        } finally {
            System.out.println("database finally");
        }

        System.out.println("Exit database");
    }
}


// ============================================================
// Custom Exceptions
// ============================================================

class DataNotFoundException extends Exception {

    public DataNotFoundException(String message) {
        super(message);
    }
}


class PermissionDeniedException extends Exception {

    public PermissionDeniedException(String message) {
        super(message);
    }
}


class LoginFailedException extends Exception {

    public LoginFailedException(String message) {
        super(message);
    }
}


class CriticalSystemException extends RuntimeException {

    public CriticalSystemException(String message) {
        super(message);
    }

    public CriticalSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}