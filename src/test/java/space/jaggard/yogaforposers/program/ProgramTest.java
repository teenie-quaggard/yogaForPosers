package space.jaggard.yogaforposers.program;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.database.Database;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.testConsole.TestConsole;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {
    private static Database database;
    private static Connection connection;

    @BeforeAll
    private static void connectToTestDB() {
        database = new Database(Database.TEST_CONNECTION_STRING);
        database.connect();
        database.createTable();
    }

    @AfterEach
    private void clearEntries(){
        database.clearTable();
    }

    @AfterAll
    private static void endTestDB() {
        database.closeConnection();
    }

    @Test
    void listDataPrintsMessageIfDataIsEmpty(){
        TestConsole console = new TestConsole(null);
        Program program = new Program(console, new ArrayList<>(),
                database);
        String emptyListMsg = Messages.EMPTY_LIST.stringify();
        program.listData();

        assertEquals(emptyListMsg, console.printedText());
    }

    @Test
    void handleDeleteShouldConfirmThatEntryHasNotBeenDeleted(){
        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("N")));
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint", "");
        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry)), database);

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
                "Hip opener", "Opens hip joint", "");
        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry)), database);

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
                "Hip opener", "Opens hip joint", "");
        Entry entry2 = new Entry("Corpse pose", "Savasana",
                "Relaxation", "Focus", "");

        Program program = new Program(console,
                new ArrayList<>(Arrays.asList(entry, entry2)),
                database);

        program.handleDelete("1");

        assertEquals(1, program.data.size());
        assertEquals(new ArrayList<>(Arrays.asList(entry2)), program.data);
    };

    @Test
    void editEntryEditsASelectedEntry(){
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint", "");
        ArrayList<Entry> entries =
                new ArrayList<>(Arrays.asList(entry));
        ArrayList<String> input = new ArrayList<>(Arrays.asList("Y", "1", "New title"));

        TestConsole console = new TestConsole(input);
        Program program = new Program(console, entries, database);

        program.editEntry("1");
        Entry editedEntry = program.getEntryFromData(0);

        assertEquals("New title", Program.getEnglishName(editedEntry));
    }

    @Test
    void getFieldFromEntry(){
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint", "");
        String fieldName = "englishName";

        assertEquals("Pigeon pose", Program.getEnglishName(entry));
    }

}