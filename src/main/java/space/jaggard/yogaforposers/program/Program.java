package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class Program {

    Boolean isRunning;
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
        output(Messages.INSTRUCTIONS.send());

        while (isRunning) {
            output(Messages.MENU.send());
            String input = ioType.getInput();
            selectAction(input);
        }

    }

    public void selectAction(String userInput){
        switch(userInput){
            case "-add":
                createEntry();
                break;
            case "-view":
                listData(data);
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

    public void createEntry(){

        output(Messages.ADD_BEGIN.send());

        output(Messages.ADD_ENGLISH_NAME.send());
        String englishName = ioType.getInput();

        output(Messages.ADD_SANSKRIT_NAME.send());
        String sanskritName = ioType.getInput();

        output(Messages.ADD_POSE_TYPE.send());
        String poseType = ioType.getInput();

        output(Messages.ADD_BENEFITS.send());
        String benefits = ioType.getInput();

        String entry =
                new Entry(englishName, sanskritName, poseType, benefits).create();

        addEntry(entry);
    }

    public void addEntry(String entry){
        data.add(0, entry);
        output(Messages.ADD_END.send());
        listData(data);
    }

    public void listData (ArrayList<String> data) {
        if (data.isEmpty()) {
            output(Messages.EMPTY_LIST.send());
        } else {
            for (String entry : data) {
                output((data.indexOf(entry) + 1) + ".");
                output(entry);
            }
        }
    }

    public void exitProgram(){
        output(Messages.EXIT.send());
        System.exit(0);
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
}
