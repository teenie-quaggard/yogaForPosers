package space.jaggard.yogaforposers.program;

import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.io.IO;

public class Program {

    IO ioType;

    public Program(IO ioType){
        this.ioType = ioType;
    }

    public static void go(IO ioType){
        greetUser(ioType, Messages.GREETING.sendMessage());
    }

    public static void greetUser(IO outputType, String message){
        outputType.print(message);
    }
}
