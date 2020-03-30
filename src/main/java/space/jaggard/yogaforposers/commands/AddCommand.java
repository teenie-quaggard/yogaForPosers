package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.validator.Validator;

import java.util.ArrayList;

public class AddCommand {

    ArrayList<Entry> data;
    IO ioType;

    public AddCommand(IO ioType, ArrayList<Entry> data){
        this.ioType = ioType;
        this.data = data;
    }

    public void handleAdd(){
        outputMessage(Messages.ADD_PROMPT);
        Entry entry = createEntry();
        addEntryToData(entry);
    }

    public Entry createEntry(){
        String englishName = getRequiredEnglishName();
        String sanskritName = getInput(Messages.ADD_SANSKRIT_NAME);
        String poseType = getInput(Messages.ADD_POSE_TYPE);
        String benefits = getInput(Messages.ADD_BENEFITS);
        return new Entry(englishName, sanskritName, poseType, benefits);
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

    private void addEntryToData(Entry entry){
        data.add(0, entry);
        outputMessage(Messages.ADD_FINISHED);
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