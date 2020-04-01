package space.jaggard.yogaforposers.mockClasses;

import space.jaggard.yogaforposers.io.IO;

import java.util.ArrayList;

public class TestConsole implements IO {

    private int index;
    ArrayList<String> input;
    String output;

    public TestConsole(ArrayList<String> input){
        this.input = input;
        this.output = "";
        this.index = 0;
    }

    @Override
    public String getInput() {
        return input.get(index++);
    }

    @Override
    public void print(String message) {
        output += message;
    }

    public String printedText() {
        return output;
    }
}
