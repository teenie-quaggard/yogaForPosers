package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.IO;

public class Program {

    IO ioType;

    public Program(){
        this.ioType = new Console();
    }

    public Program(IO ioType){
        this.ioType = ioType;
    }

    public void go(){
        output(ioType, Messages.GREETING.send());
        output(ioType, Messages.INSTRUCTIONS.send());
    }

    public static void output(IO outputType, String message){
        outputType.print(message);
    }
}
