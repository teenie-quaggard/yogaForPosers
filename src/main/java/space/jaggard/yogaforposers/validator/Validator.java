package space.jaggard.yogaforposers.validator;

import space.jaggard.yogaforposers.messages.Messages;

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

    public static boolean badArguments(String userInput){
        return userInput == null || userInput.equals("");
    };


}
