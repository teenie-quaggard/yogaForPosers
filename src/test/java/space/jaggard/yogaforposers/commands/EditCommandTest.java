package space.jaggard.yogaforposers.commands;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.testDB.TestDB;
import space.jaggard.yogaforposers.entry.Entry;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.program.Program;
import space.jaggard.yogaforposers.testConsole.TestConsole;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EditCommandTest {

    @Test
    void editShouldAllowAUserToEditASpecificField() {
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        ArrayList<Entry> entries =
                new ArrayList<>(Arrays.asList(entry));
        ArrayList<String> input = new ArrayList<>(Arrays.asList("Y", "1", "New title"));
        TestConsole console = new TestConsole(input);
        Program program = new Program(console, entries, new TestDB());
        EditCommand editCommand = new EditCommand(console);

        editCommand.edit("1", entries);
        Entry editedEntry = program.getEntryFromData(0);

        assertEquals("New title", Program.getEnglishName(editedEntry));
    }

    @Test
    void editShouldDisplayErrorMessageIfPassedTheIncorrectArguments(){
        TestConsole console = new TestConsole(null);
        EditCommand editCommand = new EditCommand(console);

        editCommand.edit("", null);
        String errorMessage = Messages.INCORRECT_EDIT.stringify();

        assertEquals(errorMessage, console.printedText());
    }
}