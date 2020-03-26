package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class Program {

    boolean running;
    IO ioType;
    ArrayList<String> data;

    public Program(){
       this(new Console(), new ArrayList<>());
    }

    public Program(IO ioType, ArrayList<String> data){
        this.running = true;
        this.ioType = ioType;
        this.data = data;
    }

    public void go(){
        output(Messages.GREETING);
        output(Messages.INTRO);

        while (running) {
            tick();
        }
    }

    public void tick(){
        String input = getInput(Messages.MENU);
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
            case "-ADD":
                addsNewEntry();
                break;
            case "-LIST":
                listData(data);
                break;
            case "-EDIT":
                editEntry(entryIndex);
                break;
            case "-DEL":
                deleteEntry(entryIndex);
                break;
            case "-EXIT":
                exitProgram();
                break;
            default:
                output(Messages.ERR_WRONG_COMMAND);
                break;
        }
    }

    public void addsNewEntry(){
        output(Messages.ADD_PROMPT);
        String entry = createEntry();
        addEntryToData(entry);
        listData(data);
    }

    public void listData(ArrayList<String> data) {
        if (data.isEmpty()) {
            output(Messages.EMPTY_LIST);
        } else {
            output(Messages.LIST);
            prependDataWithNumber(data);
        }
    }

    public void editEntry(String userInput){
        output(Messages.REVIEW_ENTRY_PROMPT);
        displayEntry(userInput);
        String input = getInput(Messages.EDIT_PROMPT).toUpperCase();

        if (input.equals("Y")) {
            String field = getInput(Messages.EDIT_FIELD);
        }
//        } else {
//            output(Messages.EDIT_WRONG_ENTRY);
//            listData(data);
//        }
    }

//    public void editField(String field){
//        switch(field){
//            case "1":
//
//                break;
//            case "2":
//                break;
//            case "3":
//                break;
//            case "4":
//                break;
//        }
//    }


    public void deleteEntry(String userInput){
        output(Messages.REVIEW_ENTRY_PROMPT);
        displayEntry(userInput);
        String input = getInput(Messages.DELETE_ENTRY);
        if (input.equals("Y")) {
            deleteEntryFromData(userInput);
            output(Messages.CONFIRM_DELETE);
        } else {
            output(Messages.UNCONFIRMED_DELETE);
        }
    }

    public void exitProgram(){
        output(Messages.EXIT);
        System.exit(0);
    }

    public String createEntry(){
        String englishName = getInput(Messages.ADD_ENGLISH_NAME);
        String sanskritName = getInput(Messages.ADD_SANSKRIT_NAME);
        String poseType = getInput(Messages.ADD_POSE_TYPE);
        String benefits = getInput(Messages.ADD_BENEFITS);
        return new Entry(englishName, sanskritName, poseType, benefits).create();
    }

    public void addEntryToData(String entry){
        data.add(0, entry);
        output(Messages.ADD_FINISHED);
    }

    public void deleteEntryFromData(String userInput){
        int index = convertToIndex(userInput);
        data.remove( index );
    }

    public void displayEntry(String userInput){
        int index = convertToIndex(userInput);
        String entry = getEntryFromData(index);
        outputString(entry);
    }

    public String getInput(Messages message){
        output(message);
        return ioType.getInput();
    }

    public void output(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    public void outputString(String string){
        ioType.print(string);
    }

    public ArrayList<String> getData(){
        return data;
    }

    public String getEntryFromData(int index){
        return data.get(index);
    }

    public String[] parse(String userInput){
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

    private void prependDataWithNumber(ArrayList<String> data){
        for (String entry : data) {
            outputString((data.indexOf(entry) + 1) + ".");
            outputString(entry);
        }
    }


}
