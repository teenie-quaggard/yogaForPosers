package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.commands.*;
import space.jaggard.yogaforposers.database.Database;
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
       this(new Console(), new ArrayList<>(),
               new Database(Database.PRODUCTION_CONNECTION_STRING));
    }

    public Program(IO ioType, ArrayList<Entry> data, Database db){
        this.running = true;
        this.ioType = ioType;
        this.data = data;
        this.db = db;
    }

    public void go(){
        connectDB();
        welcomeUser();
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
        Entry entry = addCommand.handleAdd();
        db.addEntry(entry);
        listData();
    }

    public void listData() {
        ListCommand listCommand = new ListCommand(ioType);
        ArrayList<Entry> savedEntries = db.getEntries();
        listCommand.listData(savedEntries);
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
        return entry.getEnglishName();
    };

    public Entry getEntryFromData(int index){
        return data.get(index);
    }

    private void connectDB(){
        db.connect();
        db.createTable();
    }

    private void welcomeUser(){
        outputMessage(Messages.GREETING);
        outputMessage(Messages.INTRO);
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
