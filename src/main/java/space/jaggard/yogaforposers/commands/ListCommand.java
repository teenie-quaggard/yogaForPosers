package space.jaggard.yogaforposers.commands;

import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.io.IO;
import space.jaggard.yogaforposers.messages.Messages;

import java.util.ArrayList;

public class ListCommand {

    IO ioType;

    public ListCommand(IO ioType){
        this.ioType = ioType;
    }


    public void listData(ArrayList<Entry> data) {
        if (data.isEmpty()) {
            outputMessage(Messages.EMPTY_LIST);
        } else {
            outputMessage(Messages.LIST_TOP);
            prependDataWithNumber(data);
            outputMessage(Messages.LIST_BOTTOM);
        }
    }

    private void prependDataWithNumber(ArrayList<Entry> data){
        for (Entry entry : data) {
            outputString((data.indexOf(entry) + 1) + ".");
            outputString(entry.stringify());
        }
    }

    private void outputMessage(Messages message){
        String string = message.stringify();
        ioType.print(string);
    }

    private void outputString(String string){
        ioType.print(string);
    }

}
