package space.jaggard.yogaforposers.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Console implements IO {

    InputStream input;
    PrintStream output;

    public Console(){
        this.input = System.in;
        this.output = System.out;
    }

    public Console(InputStream input, PrintStream output){
        this.input = input;
        this.output = output;
    }

    @Override
    public String getInput(){
        Scanner scanner = new Scanner(input);
        return scanner.nextLine();
    }

    @Override
    public void print(String message) {
        output.println(message);
    }

}
