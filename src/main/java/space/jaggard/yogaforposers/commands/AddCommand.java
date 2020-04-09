package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.validator.Validator;


public class AddCommand {

    IO ioType;

    public AddCommand(IO ioType){
        this.ioType = ioType;
    }

    public Entry handleAdd(){
        outputMessage(Messages.ADD_PROMPT);
        return createEntry();
    }

    public Entry createEntry(){
        String englishName = getRequiredEnglishName();
        String sanskritName = getInput(Messages.ADD_SANSKRIT_NAME);
        String poseType = getInput(Messages.ADD_POSE_TYPE);
        String benefits = getInput(Messages.ADD_BENEFITS);
        return new Entry(englishName, sanskritName, poseType, benefits, "");
    }

    public String getRequiredEnglishName(){
        boolean validInput = false;
        String englishName = "";

        while (!validInput) {
            englishName = getInput(Messages.ADD_ENGLISH_NAME);
            if (Validator.hasInput(englishName)) {
                validInput = true;
            } else {
                outputMessage(Messages.REQUIRED_FIELD);
                validInput = false;
            }
        }
        return englishName;
    }

    private void outputMessage(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    private String getInput(Messages message){
        outputMessage(message);
        return ioType.getInput();
    }


}
