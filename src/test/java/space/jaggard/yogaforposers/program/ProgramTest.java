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
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

    @Test
    void go() {
    }

    @Test
    void addEntryHandlesAddingAnEntry(){
        ArrayList<String> input = new ArrayList<>(Arrays.asList("Pigeon pose"
                , "Eka Pada Rajakapotasana", "Hip opener", "Opens hip joint"));
        TestConsole console = new TestConsole(input);
        Program program = new Program(console, new ArrayList<>());

        String addMsg = Messages.ADD_PROMPT.stringify();
        String englishNameMsg = Messages.ADD_ENGLISH_NAME.stringify();
        String sanskritMsg = Messages.ADD_SANSKRIT_NAME.stringify();
        String poseMsg = Messages.ADD_POSE_TYPE.stringify();
        String benefitsMsg = Messages.ADD_BENEFITS.stringify();
        String finishedMsg = Messages.ADD_FINISHED.stringify();
        String listMsg = Messages.LIST.stringify();

        program.handleAdd();

        assertEquals(addMsg + englishNameMsg + sanskritMsg + poseMsg
                        + benefitsMsg + finishedMsg + listMsg + "1." +
                        "\n---------------------------------------------\n" +
                        "ENGLISH NAME: Pigeon pose\n" +
                        "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                        "POSE TYPE: Hip opener\n" +
                        "HEALTH BENEFITS: Opens hip joint\n" +
                        "---------------------------------------------\n",
                console.printedText());
    }

    @Test
    void listDataPrintsMessageIfDataIsEmpty(){
        TestConsole console = new TestConsole(null);
        Program program = new Program(console, new ArrayList<>());
        String emptyListMsg = Messages.EMPTY_LIST.stringify();
        program.listData();

        assertEquals(emptyListMsg, console.printedText());
    }

    @Test
    void listDataPrependsEachEntryWithNumber(){
        TestConsole console = new TestConsole(null);
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        Entry entry2 = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry, entry2)));

        String listMsg = Messages.LIST.stringify();
        program.listData();

        assertEquals(listMsg +
                "1." +
                "\n---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                "POSE TYPE: Hip opener\n" +
                "HEALTH BENEFITS: Opens hip joint\n" +
                "---------------------------------------------\n" +
                "2." +
                "\n---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                "POSE TYPE: Hip opener\n" +
                "HEALTH BENEFITS: Opens hip joint\n" +
                "---------------------------------------------\n", console.printedText());
    }

    @Test
    void handleDeleteShouldConfirmThatEntryHasNotBeenDeleted(){

        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("N")));

        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");

        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry)));

        String reviewMsg = Messages.REVIEW_ENTRY_PROMPT.stringify();
        String confirmDeleteMsg = Messages.DELETE_ENTRY.stringify();
        String dontConfirmDeleteMsg = Messages.UNCONFIRMED_DELETE.stringify();

        program.handleDelete("1");

        assertEquals(reviewMsg +
                "---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                "POSE TYPE: Hip opener\n" +
                "HEALTH BENEFITS: Opens hip joint\n" +
                "---------------------------------------------\n" + confirmDeleteMsg + dontConfirmDeleteMsg
                , console.printedText());
    }

    @Test
    void handleDeleteShouldConfirmWithUserWhenEntryIsDeleted(){

        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("Y")));

        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");

        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry)));

        String reviewMsg = Messages.REVIEW_ENTRY_PROMPT.stringify();
        String confirmDeleteMsg = Messages.DELETE_ENTRY.stringify();
        String confirmedMsg = Messages.CONFIRM_DELETE.stringify();

        program.handleDelete("1");

        assertEquals(reviewMsg +
                        "---------------------------------------------\n" +
                        "ENGLISH NAME: Pigeon pose\n" +
                        "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                        "POSE TYPE: Hip opener\n" +
                        "HEALTH BENEFITS: Opens hip joint\n" +
                        "---------------------------------------------\n" + confirmDeleteMsg
                        + confirmedMsg
                , console.printedText());
    }

//    @Test
//    void deleteEntryShouldRemoveAnEntryFromTheDataList(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
//                "Y", "Item Z"));
//        Program program = new Program(console, list);
//
//        program.deleteEntry("2");
//
//        assertEquals(Arrays.asList("Item X", "Item Z"), program.getData());
//    }


//    @Test
//    void listDataPrintsNumberedList(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
//                "Hip opener", "Opens hip joint");
//
//        ArrayList<Entry> list = new ArrayList<Entry>(Arrays.asList(entry,
//                entry));
//        Program program = new Program(console, null);
//        program.listData(list);
//
//        System.out.println(program.data);
//
//        assertTrue(output.toString().contains("Pigeon pose"));
//    }
//
//    @Test
//    void addEntryAddsNewEntryToBeginningOfList(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
//                "Y", "Item Z"));
//        Program program = new Program(console, list);
//        String newEntry = "Item A";
//
//        program.addEntryToData(newEntry);
//
//        assertEquals(Arrays.asList("Item A", "Item X", "Item " +
//                "Y", "Item Z"), program.getData());
//    }
//
//    @Test
//    void getDataReturnsTheListOfAnySavedEntries(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
//                "Y", "Item Z"));
//        Program program = new Program(console, list);
//
//        assertEquals(Arrays.asList("Item X", "Item " +
//                "Y", "Item Z"), program.getData());
//    }
//
//    @Test
//    void getEntryFromDataTakesAnIndexAndReturnsEntryFromListAtThatIndex(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("Item X", "Item " +
//                "Y", "Item Z"));
//        Program program = new Program(console, list);
//
//        assertEquals("Item X", program.getEntryFromData(0));
//    }
//

//
//
//    @Test
//    void getInputReturnsUserInput(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        String input = "Pigeon Pose";
//        InputStream in = new ByteArrayInputStream(input.getBytes());
//        Console console = new Console(in, out);
//
//        Program program = new Program(console, null);
//        Messages message = Messages.ADD_ENGLISH_NAME;
//
//        assertEquals(input, program.getInput(message));
//    }
//
//
//    @Test
//    void parseArgumentsShouldTakeInUserInputAndReturnAnArraySeparatingUserCommandsFromArguments(){
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream out = new PrintStream(output);
//        InputStream in =
//                new ByteArrayInputStream("".getBytes());
//        Console console = new Console(in, out);
//        Program program = new Program(console, null);
//
//        String[] parsedArguments = program.parse("-delete 2");
//
//        assertEquals("-delete", parsedArguments[0]);
//        assertEquals("2", parsedArguments[1]);
//    }

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