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

class DeleteCommandTest {

    @Test
    void deleteShouldDeleteAnEntryAtASpecificIndex() {
        Entry entry = new Entry("Pigeon pose", "Eka Pada Rajakapotasana",
                "Hip opener", "Opens hip joint");
        Entry entry2 = new Entry("Corpse pose", "Savasana",
                "Relaxation", "Focus");
        ArrayList<Entry> entries =
                new ArrayList<>(Arrays.asList(entry, entry2));

        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("Y")));
        Program program = new Program(console, entries, new TestDB());
        DeleteCommand deleteCommand = new DeleteCommand(console);

        deleteCommand.delete("1", entries);
        Entry remainingEntry = program.getEntryFromData(0);

        assertEquals(entry2, remainingEntry);
    }

    @Test
    void deleteShouldGiveWarningIfPassedTheIncorrectNumberOfArguments(){
        TestConsole console =
                new TestConsole(new ArrayList<>(Arrays.asList("")));
        Program program = new Program(console, null, new TestDB());
        DeleteCommand deleteCommand = new DeleteCommand(console);

        deleteCommand.delete("", null);
        String deleteError = Messages.INCORRECT_DEL.stringify();

        assertEquals(deleteError, console.printedText());
    }
}