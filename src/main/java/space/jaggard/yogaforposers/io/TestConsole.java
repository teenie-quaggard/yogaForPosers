package space.jaggard.yogaforposers.io;

import java.util.ArrayList;

public class TestConsole implements IO {

    ArrayList<String> input;
    String output;

    public TestConsole(ArrayList<String> input){
        this.input = input;
        this.output = "";
    }

    @Override
    public String getInput() {
        return input.get(0);
    }


    @Override
    public void print(String message) {
        output += message;
    }

    public String printedText() {
        return output;
    }
}
