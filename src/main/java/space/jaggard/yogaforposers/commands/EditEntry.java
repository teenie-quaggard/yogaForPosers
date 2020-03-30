package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;


import java.util.ArrayList;

public class EditEntry {

    IO ioType;

    public EditEntry(IO ioType){
        this.ioType = ioType;
    }

    public void editEntry(String userInput, ArrayList<Entry> data){

        if (badArguments(userInput)) {
           return;
        }

        String input = checkIfCorrectEntry(userInput, data);

        if (input.equals("Y")) {
            int index = convertToIndex(userInput);
            Entry entry = getEntryFromData(index,  data);
            String whichField = getInput(Messages.EDIT_GET_FIELD);
            editField(entry, whichField, userInput, data);
        } else {
            outputMessage(Messages.EDIT_WRONG_ENTRY);
        }
    }

    private boolean badArguments(String userInput){
        if (userInput == null || userInput.equals("")) {
            outputMessage(Messages.INCORRECT_EDIT);
            return true;
        } else {
            return false;
        }
    };

    private String checkIfCorrectEntry(String userInput, ArrayList<Entry> data){
        outputMessage(Messages.REVIEW_ENTRY_PROMPT);
        displayEntry(userInput, data);
        return getInput(Messages.EDIT_IS_RIGHT_ENTRY).toUpperCase();
    };

     private void editField(Entry entry, String field, String userInput,
                          ArrayList<Entry> data){
        switch(field){
            case "1":
                String englishName = getInput(Messages.EDIT_FIELD);
                entry.updateEnglishName(englishName);
                displayEntry(userInput, data);
                break;
            case "2":
                String sanskritName  = getInput(Messages.EDIT_FIELD);
                entry.updateSanskritName(sanskritName);
                displayEntry(userInput, data);
                break;
            case "3":
                String poseType = getInput(Messages.EDIT_FIELD);
                entry.updatePoseType(poseType);
                displayEntry(userInput, data);
                break;
            case "4":
                String benefits = getInput(Messages.EDIT_FIELD);
                entry.updateBenefits(benefits);
                displayEntry(userInput, data);
                break;
        }
    }

    private int convertToIndex(String input){
        return Integer.parseInt(input) - 1;
    }

    private void outputMessage(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    private void displayEntry(String userInput, ArrayList<Entry> data){
        int index = convertToIndex(userInput);
        Entry entry = getEntryFromData(index,  data);
        String entryString = entry.stringify();
        outputString(entryString);
    }

    private Entry getEntryFromData(int index, ArrayList<Entry> data){
        return data.get(index);
    }

    private void outputString(String string){
        ioType.print(string);
    }

    private String getInput(Messages message){
        outputMessage(message);
        return ioType.getInput();
    }


}
