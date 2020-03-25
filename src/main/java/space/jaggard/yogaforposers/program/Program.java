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
            String input = printsAndGetsInput(Messages.MENU);
            selectAction(input);
        }
    }

    public void selectAction(String userInput){
//        String[] arr = userInput.split(" ", 2);
//        String command = arr[0];
//        int index = Integer.parseInt(arr[1]);

        switch(userInput){
            case "-add":
                newEntry();
                break;
            case "-view":
                listData(data);
                break;
//            case "-delete":
//                deleteEntry(index);
//                break;
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
        String englishName = printsAndGetsInput(Messages.ADD_ENGLISH_NAME);
        String sanskritName = printsAndGetsInput(Messages.ADD_SANSKRIT_NAME);
        String poseType = printsAndGetsInput(Messages.ADD_POSE_TYPE);
        String benefits = printsAndGetsInput(Messages.ADD_BENEFITS);
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
            for (String entry : data) {
                output((data.indexOf(entry) + 1) + ".");
                output(entry);
            }
        }
    }

    public void deleteEntry(int userInput){
        int index = userInput - 1;
        data.remove( index );
    }

    public void exitProgram(){
        output(Messages.EXIT.send());
        System.exit(0);
    }

    public String printsAndGetsInput(Messages message){
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

}
