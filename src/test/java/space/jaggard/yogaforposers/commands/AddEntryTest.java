package space.jaggard.yogaforposers.commands;

import org.junit.jupiter.api.Test;
import space.jaggard.yogaforposers.messages.Messages;
import space.jaggard.yogaforposers.program.Program;
import space.jaggard.yogaforposers.program.TestConsole;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AddEntryTest {

    @Test
    void handleAdd() {
    }

    @Test
    void createEntry() {
    }

    @Test
    void getEnglishNameIsARequiredField(){
        ArrayList<String> input = new ArrayList<>(Arrays.asList(" ", "English" +
                " title"));

        TestConsole console = new TestConsole(input);
        AddEntry add = new AddEntry(console, null);

        String addName = Messages.ADD_ENGLISH_NAME.stringify();
        String requiredField = Messages.REQUIRED_FIELD.stringify();

        add.getRequiredEnglishName();
        assertEquals(addName + requiredField + addName, console.printedText());
    }
}