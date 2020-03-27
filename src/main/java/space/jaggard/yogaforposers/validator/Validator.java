package space.jaggard.yogaforposers.validator;

public class Validator {

    public static boolean hasInput(String input){
        String trimmedInput = input.trim();
        return trimmedInput.length() > 0;
    }

    public static boolean hasTwoArguments(String input){
        String trimmedInput = input.trim();
        String[] splitString = trimmedInput.split(" ");
        return splitString.length == 2;
    }
}
