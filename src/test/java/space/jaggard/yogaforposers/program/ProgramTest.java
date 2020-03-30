package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.messages.Messages;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {

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
        String listTopMsg = Messages.LIST_TOP.stringify();
        String listBottomMsg = Messages.LIST_BOTTOM.stringify();

        program.handleAdd();

        assertEquals(addMsg + englishNameMsg + sanskritMsg + poseMsg
                        + benefitsMsg + finishedMsg + listTopMsg + "1." +
                        "---------------------------------------------\n" +
                        "ENGLISH NAME: Pigeon pose\n" +
                        "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                        "POSE TYPE: Hip opener\n" +
                        "HEALTH BENEFITS: Opens hip joint\n" +
                        "---------------------------------------------\n"
                        + listBottomMsg,
                console.printedText());
    }

    @Test
    void handleAddShouldAddAnEntryToData(){
        ArrayList<String> input = new ArrayList<>(Arrays.asList("Pigeon pose"
                , "Eka Pada Rajakapotasana", "Hip opener", "Opens hip joint"));
        TestConsole console = new TestConsole(input);
        Program program = new Program(console, new ArrayList<>());

        program.handleAdd();

        assertEquals(1, program.data.size());
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

        String listTop = Messages.LIST_TOP.stringify();
        String listBottom = Messages.LIST_BOTTOM.stringify();
        program.listData();

        assertEquals(listTop +
                "1." +
                "---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                "POSE TYPE: Hip opener\n" +
                "HEALTH BENEFITS: Opens hip joint\n" +
                "---------------------------------------------\n" +
                "2." +
                "---------------------------------------------\n" +
                "ENGLISH NAME: Pigeon pose\n" +
                "SANSKRIT NAME: Eka Pada Rajakapotasana\n" +
                "POSE TYPE: Hip opener\n" +
                "HEALTH BENEFITS: Opens hip joint\n" +
                "---------------------------------------------\n" + listBottom,
                console.printedText());
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

    @Test
    void handleDeleteShouldRemoveASpecifiedEntryFromData(){
        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("Y")));

        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        Entry entry2 = new Entry("Corpse pose", "Savasana",
                "Relaxation", "Focus");

        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry, entry2)));

        program.handleDelete("1");

        assertEquals(1, program.data.size());
        assertEquals(new ArrayList<>(Arrays.asList(entry2)), program.data);
    };

    @Test
    void editEntryEditsASelectedEntry(){
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        ArrayList<Entry> entries =
                new ArrayList<>(Arrays.asList(entry));
        ArrayList<String> input = new ArrayList<>(Arrays.asList("Y", "1", "New title"));

        TestConsole console = new TestConsole(input);
        Program program = new Program(console, entries);

        program.editEntry("1");
        Entry editedEntry = program.getEntryFromData(0);

        assertEquals("New title", Program.getEnglishName(editedEntry));
    }

    @Test
    void getFieldFromEntry(){
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        String fieldName = "englishName";

        assertEquals("Pigeon pose", Program.getEnglishName(entry));
    }

}