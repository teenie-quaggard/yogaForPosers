package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.commands.*;
import space.jaggard.yogaforposers.database.Database;
import space.jaggard.yogaforposers.database.ProductionDB;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class Program {

    boolean running;
    IO ioType;
    ArrayList<Entry> data;
    Database db;

    public Program(){
       this(new Console(), new ArrayList<>(), new ProductionDB(ProductionDB.PRODUCTION));
    }

    public Program(IO ioType, ArrayList<Entry> data, Database db){
        this.running = true;
        this.ioType = ioType;
        this.data = data;
        this.db = db;
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

        String command = parsedInput[0].toUpperCase();

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
        AddCommand addCommand = new AddCommand(ioType);
        addCommand.handleAdd(data);
        listData();
    }

    public void listData() {
        ListCommand listCommand = new ListCommand(ioType);
        listCommand.listData(data);
    }

    public void editEntry(String userInput){
        EditCommand editCommand = new EditCommand(ioType);
        editCommand.edit(userInput, data);
        listData();
    }

    public void handleDelete(String userInput){
        DeleteCommand deleteCommand = new DeleteCommand(ioType);
        deleteCommand.delete(userInput, data);
    }

    public void exitProgram(){
        ExitCommand exitCommand = new ExitCommand(ioType);
        exitCommand.exitProgram();
    }

    public static String getEnglishName(Entry entry){
        return entry.returnEnglishName();
    };

    public Entry getEntryFromData(int index){
        return data.get(index);
    }

    private String[] parse(String userInput){
        String input = trimWhitespace(userInput);
        return input.split(" ");
    }

    private String trimWhitespace(String string) {
        return string.trim();
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
