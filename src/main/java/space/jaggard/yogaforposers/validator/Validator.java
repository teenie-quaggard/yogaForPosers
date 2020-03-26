package space.jaggard.yogaforposers.validator;

public class Validator {

    public static boolean hasInput(String input){
        String trimmedInput = input.trim();
        return trimmedInput.length() > 0;
    }
}
