package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;

public class ExitProgram {

    IO ioType;

    public ExitProgram(IO ioType){
        this.ioType = ioType;
    }

    public void exitProgram(){
        outputMessage();
        System.exit(0);
    }

    private void outputMessage(){
        String string = Messages.EXIT.stringify();
        ioType.print(string);
    }

}
