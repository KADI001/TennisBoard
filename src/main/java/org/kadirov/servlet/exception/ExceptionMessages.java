package org.kadirov.servlet.exception;

public final class ExceptionMessages {

    private ExceptionMessages() {
    }

    public static String MISSING_PARAMETER(String parameterName){
        return String.format("Missing parameter %s", parameterName);
    }

    public static String DATA_BASE_ERROR(){
        return "Error with data base, please try it again later";
    }

    public static String NOT_VALID_PARAMETER(String parameterName){
        return String.format("Parameter '%s' is not valid", parameterName);
    }

    public static String UNEXPECTED_ERROR(){
        return "Unexpected error occurred";
    }
}
