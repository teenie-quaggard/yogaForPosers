package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.io.Console;
import space.jaggard.yogaforposers.messages.Messages;

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

    @Test
    void outputPrintsAMessageWhenPassedOne(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in = new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        String stringToPrint = "Hello world!";

        Program program = new Program(console, null);
        program.output(stringToPrint);

        assertEquals(stringToPrint + "\n", output.toString());
    }


//    @Test void createEntryStringTakesUserInputAndReturnsStringifiedEntryData(){
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
//                new ByteArrayInputStream(englishName.getBytes());
//        Console console = new Console(in, out);
//
//
//        Program program = new Program(console, null);
//
//        assertEquals("\n---------------------------------------------\n" +
//                "ENGLISH NAME: Pigeon Pose\n" +
//                "SANSKRIT NAME: Eka Pada Rajakapotasana\n"+
//                "POSE TYPE: Hip opener\n"+
//                "HEALTH BENEFITS: Opens the hip joint" +
//                "\n---------------------------------------------\n",
//                program.createEntryString());
//    }

    @Test void listDataPrintsNumberedList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item 1", "Item " +
                "2", "Item X"));
        Program program = new Program(console, null);
        program.listData(list);

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
                "Y", "Item Z"), program.getData());
    }

    @Test void getDataReturnsTheListOfAnySavedEntries(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"));
        Program program = new Program(console, list);

        assertEquals(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"), program.getData());
    }

    @Test
    void getEntryFromDataTakesAnIndexAndReturnsEntryFromListAtThatIndex(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"));
        Program program = new Program(console, list);

        assertEquals("Item X", program.getEntryFromData(0));
    }

    @Test
    void deleteEntryShouldRemoveAnEntryFromTheDataList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"));
        Program program = new Program(console, list);

        program.deleteEntry("2");

        assertEquals(Arrays.asList("Item X", "Item Z"), program.getData());
    }


    @Test
    void printsAndGetsInputReturnsUserInput(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        String input = "Pigeon Pose";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Console console = new Console(in, out);

        Program program = new Program(console, null);
        Messages message = Messages.ADD_ENGLISH_NAME;

        assertEquals(input, program.getInput(message));
    }


    @Test void parseArgumentsShouldTakeInUserInputAndReturnAnArraySeparatingUserCommandsFromArguments(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        Program program = new Program(console, null);

        String[] parsedArguments = program.parseArguments("-delete 2");

        assertEquals("-delete", parsedArguments[0]);
        assertEquals("2", parsedArguments[1]);}
}