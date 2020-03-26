package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;
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
    void outputStringPrintsAStringeWhenPassedOne(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in = new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        String stringToPrint = "Hello world!";

        Program program = new Program(console, null);
        program.outputString(stringToPrint);

        assertEquals(stringToPrint + "\n", output.toString());
    }


//    @Test
//    void createEntryStringTakesUserInputAndReturnsStringifiedEntryData(){
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

    @Test
    void listDataPrintsNumberedList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item 1", "Item " +
                "2", "Item X"));
        Program program = new Program(console, null);
        program.listData(list);

        assertTrue(output.toString().contains("1.\n" +
                "Item 1\n" +
                "2.\n" +
                "Item 2\n" +
                "3.\n" +
                "Item X\n"));
    }

    @Test
    void addEntryAddsNewEntryToBeginningOfList(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
                "Y", "Item Z"));
        Program program = new Program(console, list);
        String newEntry = "Item A";

        program.addEntryToData(newEntry);

        assertEquals(Arrays.asList("Item A", "Item X", "Item " +
                "Y", "Item Z"), program.getData());
    }

    @Test
    void getDataReturnsTheListOfAnySavedEntries(){
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

        program.deleteEntryFromData("2");

        assertEquals(Arrays.asList("Item X", "Item Z"), program.getData());
    }


    @Test
    void getInputReturnsUserInput(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        String input = "Pigeon Pose";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Console console = new Console(in, out);

        Program program = new Program(console, null);
        Messages message = Messages.ADD_ENGLISH_NAME;

        assertEquals(input, program.getInput(message));
    }


    @Test
    void parseArgumentsShouldTakeInUserInputAndReturnAnArraySeparatingUserCommandsFromArguments(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(output);
        InputStream in =
                new ByteArrayInputStream("".getBytes());
        Console console = new Console(in, out);
        Program program = new Program(console, null);

        String[] parsedArguments = program.parse("-delete 2");

        assertEquals("-delete", parsedArguments[0]);
        assertEquals("2", parsedArguments[1]);
    }

//    @Test
//    void editEntryDisplaysSelectedEntry(){
//
//        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
//                "Hip opener", "Opens hip joint");
//
//        Entry entry2 = new Entry("Corpse pose", "Savasana",
//                "Relaxation", "Calming");
//
//        ArrayList<Entry> entries =
//                new ArrayList<>(Arrays.asList(entry, entry2));
//
//        ArrayList<String> input = new ArrayList<>(Arrays.asList("Y", "1"));
//
//        TestConsole console = new TestConsole(input);
//        Program program = new Program(console, entries);
//
//        program.editEntry("1");
//
//        String reviewEntryPrompt = Messages.REVIEW_ENTRY_PROMPT.stringify();
//        String editPrompt = Messages.EDIT_PROMPT.stringify();
//        String editField = Messages.EDIT_FIELD.stringify();
//
//        assertEquals(reviewEntryPrompt + "Item X\n" + editPrompt + editField,
//                console.printedText());
//    }

}