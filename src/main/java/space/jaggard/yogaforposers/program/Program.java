package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class Program {

    IO ioType;
    ArrayList<String> data;

    public Program(){
       this(new Console(), new ArrayList<>());
    }

    public Program(IO ioType, ArrayList<String> data){
        this.ioType = ioType;
        this.data = data;
    }

    public void go(){
        output(ioType, Messages.GREETING.send());
        output(ioType, Messages.INSTRUCTIONS.send());
        output(ioType, Messages.MENU.send());
        output(ioType, Messages.EMPTY_LIST.send());
        ioType.getInput();
    }

    public String createEntry(IO ioType){

        output(ioType, Messages.ADD_BEGIN.send());

        output(ioType, Messages.ADD_ENGLISH_NAME.send());
        String englishName = ioType.getInput();

        output(ioType, Messages.ADD_SANSKRIT_NAME.send());
        String sanskritName = ioType.getInput();

        output(ioType, Messages.ADD_POSE_TYPE.send());
        String poseType = ioType.getInput();

        output(ioType, Messages.ADD_BENEFITS.send());
        String benefits = ioType.getInput();

        return new Entry(englishName, sanskritName, poseType, benefits).create();
    }

    public void output(IO ioType, String message){
        ioType.print(message);
    }
}
