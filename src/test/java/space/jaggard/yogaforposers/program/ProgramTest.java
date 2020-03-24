package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.io.Console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void go() {
    }

    @Test void outputPrintsAMessageWhenPassedOne(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in = new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        String stringToPrint = "Hello world!";

        Program program = new Program(console, null);
        program.output(console, stringToPrint);

        assertEquals(stringToPrint + "\n", output.toString());
    }

    @Test void createEntryTakesUserInputAndReturnsStringifiedEntryData(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);

        String englishName = "Pigeon Pose";
        String sanskritName = "Eka Pada Rajakapotasana";
        String poseType = "Hip opener";
        String benefits = "Opens the hip joint";
        String userInput = englishName + System.lineSeparator()
                + sanskritName + System.lineSeparator()
                + poseType + System.lineSeparator()
                + benefits + System.lineSeparator();

        InputStream in =
                new ByteArrayInputStream(userInput.getBytes());
        Console console = new Console(in, out);


        Program program = new Program(console, null);
        assertEquals("\n---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon Pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n"+
                "POSE TYPE: Hip opener\n"+
                "HEALTH BENEFITS: Opens the hip joint" +
                "\n---------------------------------------------\n",
                program.createEntry(console));
    }
}