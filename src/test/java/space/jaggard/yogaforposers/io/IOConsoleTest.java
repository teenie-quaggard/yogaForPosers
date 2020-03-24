package space.jaggard.yogaforposers.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

class IOConsoleTest {

    @Test void printPrintsAString() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in = new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        String message = "Hello world!";

        console.print(message);

        assertEquals(message + "\n", output.toString());
    }

    @Test void getInputTakesUserInput(){
       ByteArrayOutputStream output = new ByteArrayOutputStream();
       PrintStream out = new PrintStream(output);
       String userInput = "I am user input.";
       InputStream in = new ByteArrayInputStream(userInput.getBytes());
       Console console = new Console(in, out);

       assertEquals(userInput, console.getInput());
    }
}