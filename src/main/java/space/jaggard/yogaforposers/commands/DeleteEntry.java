package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;

import java.util.ArrayList;

public class DeleteEntry {

    IO ioType;

    public DeleteEntry(IO ioType){
        this.ioType = ioType;
    }

    public void handleDelete(String userInput, ArrayList<Entry> data){
        String input = confirmEntryToDelete(userInput, data);
        if (input.equals("Y")) {
            deleteEntry(userInput, data);
            outputMessage(Messages.CONFIRM_DELETE);
        } else {
            outputMessage(Messages.UNCONFIRMED_DELETE);
        }
    }

    private void deleteEntry(String userInput, ArrayList<Entry> data){
        int index = convertToIndex(userInput);
        data.remove( index );
    }

    private void displayEntry(String userInput, ArrayList<Entry> data){
        int index = convertToIndex(userInput);
        Entry entry = getEntryFromData(index, data);
        String entryString = entry.stringify();
        outputString(entryString);
    }

    private String confirmEntryToDelete(String userInput,
                                        ArrayList<Entry> data){
        outputMessage(Messages.REVIEW_ENTRY_PROMPT);
        displayEntry(userInput, data);
        return getInput().toUpperCase();
    }

    private int convertToIndex(String input){
        return Integer.parseInt(input) - 1;
    }

    private void outputMessage(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    private void outputString(String string){
        ioType.print(string);
    }

    private String getInput(){
        outputMessage(Messages.DELETE_ENTRY);
        return ioType.getInput();
    }

    public Entry getEntryFromData(int index, ArrayList<Entry> data){
        return data.get(index);
    }


}
