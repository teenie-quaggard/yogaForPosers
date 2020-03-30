package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.commands.AddEntry;
import space.jaggard.yogaforposers.commands.EditEntry;
import space.jaggard.yogaforposers.commands.ListData;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.validator.Validator;

import java.util.ArrayList;

public class Program {

    boolean running;
    IO ioType;
    ArrayList<Entry> data;

    public Program(){
       this(new Console(), new ArrayList<>());
    }

    public Program(IO ioType, ArrayList<Entry> data){
        this.running = true;
        this.ioType = ioType;
        this.data = data;
    }

    public void go(){
        outputMessage(Messages.GREETING);
        outputMessage(Messages.INTRO);

        while (running) {
            tick();
        }
    }

    public void tick(){
        String input = getInput(Messages.GET_COMMAND);
        String[] parsedInput = parse(input);
        selectCommand(parsedInput);
    }

    public void selectCommand(String[] parsedInput){

        String entryIndex = null;
        if (parsedInput.length == 2) {
            entryIndex = parsedInput[1];
        }

        String command = uppercaseInput(parsedInput[0]);

        switch(command){
            case "-HELP":
                outputMessage(Messages.MENU);
                break;
            case "-ADD":
                handleAdd();
                break;
            case "-LIST":
                listData();
                break;
            case "-EDIT":
                editEntry(entryIndex);
                break;
            case "-DEL":
                handleDelete(entryIndex);
                break;
            case "-EXIT":
                exitProgram();
                break;
            default:
                outputMessage(Messages.ERR_WRONG_COMMAND);
                break;
        }
    }

    public void handleAdd(){
        AddEntry addEntry = new AddEntry(ioType);
        addEntry.handleAdd(data);
        listData();
    }

    public void listData() {
        ListData list = new ListData(ioType);
        list.listData(data);
    }

    public void editEntry(String userInput){
        EditEntry edit = new EditEntry(ioType);
        edit.editEntry(userInput, data);
        listData();
    }

    public void handleDelete(String userInput){
        String input = confirmEntryToDelete(userInput);
        if (input.equals("Y")) {
            deleteEntry(userInput);
            outputMessage(Messages.CONFIRM_DELETE);
        } else {
            outputMessage(Messages.UNCONFIRMED_DELETE);
        }
    }

    public void exitProgram(){
        outputMessage(Messages.EXIT);
        System.exit(0);
    }

    private String confirmEntryToDelete(String userInput){
        outputMessage(Messages.REVIEW_ENTRY_PROMPT);
        displayEntry(userInput);
        return getInput(Messages.DELETE_ENTRY).toUpperCase();
    }

    public void deleteEntry(String userInput){
        int index = convertToIndex(userInput);
        data.remove( index );
    }

    public void validateNumberOfArguments(String userInput){
        if(!Validator.hasTwoArguments(userInput)){
            outputMessage(Messages.INCORRECT_CMD_ARGS);
        }
    }

    public static String getEnglishName(Entry entry){
        return entry.returnEnglishName();
    };

    private void displayEntry(String userInput ){
        int index = convertToIndex(userInput);
        Entry entry = getEntryFromData(index);
        String entryString = entry.stringify();
        outputString(entryString);
    }


    private String getInput(Messages message){
        outputMessage(message);
        return ioType.getInput();
    }

    public Entry getEntryFromData(int index){
        return data.get(index);
    }

    private String[] parse(String userInput){
        String input = trimWhitespace(userInput);
        return input.split(" ");
    }

    private String uppercaseInput(String userInput){
        return userInput.toUpperCase();
    }

    private int convertToIndex(String input){
        return Integer.parseInt(input) - 1;
    }


    private String trimWhitespace(String string) {
        return string.trim();
    }

    private void outputMessage(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    private void outputString(String string){
        ioType.print(string);
    }

}
