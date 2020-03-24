package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.io.Console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

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

//    @Test void createEntryTakesUserInputAndReturnsStringifiedEntryData(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//
//        String englishName = "Pigeon Pose";
//        String sanskritName = "Eka Pada Rajakapotasana";
//        String poseType = "Hip opener";
//        String benefits = "Opens the hip joint";
//        String userInput = englishName + System.lineSeparator()
//                + sanskritName + System.lineSeparator()
//                + poseType + System.lineSeparator()
//                + benefits + System.lineSeparator();
//
//        InputStream in =
//                new ByteArrayInputStream(userInput.getBytes());
//        Console console = new Console(in, out);
//
//
//        Program program = new Program(console, null);
//        assertEquals("\n---------------------------------------------\n" +
//                "ENGLISH NAME: Pigeon Pose\n" +
//                "SANSKRIT NAME: Eka Pada Rajakapotasana\n"+
//                "POSE TYPE: Hip opener\n"+
//                "HEALTH BENEFITS: Opens the hip joint" +
//                "\n---------------------------------------------\n",
//                program.createEntry(console));
//    }

    @Test void printListPrintsNumberedList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item 1", "Item " +
                "2", "Item X"));
        Program program = new Program(console, null);
        program.printList(console, list);

        assertEquals("1.\nItem 1\n" +
                "2.\nItem 2\n" +
                "3.\nItem X\n", output.toString());
    }

    @Test void addEntryAddsNewEntryToBeginningOfList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"));
        Program program = new Program(console, list);
        String newEntry = "Item A";

        program.addEntry(newEntry);

        assertEquals(Arrays.asList("Item A", "Item X", "Item " +
                "Y", "Item Z"), program.getList());
    }
}