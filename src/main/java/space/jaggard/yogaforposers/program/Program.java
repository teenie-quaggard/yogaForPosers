package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class Program {

    boolean isRunning;
    IO ioType;
    ArrayList<String> data;

    public Program(){
       this(new Console(), new ArrayList<>());
    }

    public Program(IO ioType, ArrayList<String> data){
        this.isRunning = true;
        this.ioType = ioType;
        this.data = data;
    }

    public void go(){
        output(Messages.GREETING.send());
        output(Messages.INTRO.send());

        while (isRunning) {
            String input = getInput(Messages.MENU);
            String[] parsedInput = parseArguments(input);
            selectAction(parsedInput);
        }
    }

    public String[] parseArguments(String userInput){
        String input = trimWhitespace(userInput);
        return input.split(" ");
 }

    public void selectAction(String[] parsedInput){
        String command = parsedInput[0];
        String argument = null;

        if (parsedInput.length == 2) {
            argument = parsedInput[1];
        }

        switch(command){
            case "-add":
                newEntry();
                break;
            case "-view":
                listData(data);
                break;
            case "-delete":
                deleteEntry(argument);
                break;
            case "-exit":
                exitProgram();
                break;
            default:
                output(Messages.ERR_WRONG_COMMAND.send());
                output(Messages.MENU.send());
                break;
        }
    }

    public void newEntry(){
        output(Messages.ADD_BEGIN.send());
        String entry = createEntryString();
        addEntry(entry);
        listData(data);
    }

    public String createEntryString(){
        String englishName = getInput(Messages.ADD_ENGLISH_NAME);
        String sanskritName = getInput(Messages.ADD_SANSKRIT_NAME);
        String poseType = getInput(Messages.ADD_POSE_TYPE);
        String benefits = getInput(Messages.ADD_BENEFITS);
        return new Entry(englishName, sanskritName, poseType, benefits).create();
    }

    public void addEntry(String entry){
        data.add(0, entry);
        output(Messages.ADD_END.send());
    }

    public void listData (ArrayList<String> data) {
        if (data.isEmpty()) {
            output(Messages.EMPTY_LIST.send());
        } else {
           prependsDataWithListNumber(data);
        }
    }

    public void deleteEntry(String userInput){
        int index = Integer.parseInt(userInput) - 1;
        data.remove( index );
    }

    public void exitProgram(){
        output(Messages.EXIT.send());
        System.exit(0);
    }

    public String getInput(Messages message){
        output(message.send());
        return ioType.getInput();
    }

    public void output(String message){
        ioType.print(message);
    }

    public ArrayList<String> getData(){
        return data;
    }

    public String getEntryFromData(int index){
        return data.get(index);
    }

    private String trimWhitespace(String string) {
        return string.trim();
    }

    private void prependsDataWithListNumber(ArrayList<String> data){
        for (String entry : data) {
            output((data.indexOf(entry) + 1) + ".");
            output(entry);
        }
    }

}
