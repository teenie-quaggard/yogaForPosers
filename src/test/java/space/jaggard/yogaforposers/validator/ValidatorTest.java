package space.jaggard.yogaforposers.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void hasInputReturnsFalseIfNoInput() {
        String emptyInput = "  ";
        String validInput = "Y";

        assertFalse(Validator.hasInput(emptyInput));
        assertTrue(Validator.hasInput(validInput));
    }

    @Test
    void hasTwoArgumentsReturnsFalseIfOnlyPassedOneArguments(){
        String oneArgument = "-help";
        String twoArguments = "-edit 1";

        assertFalse(Validator.hasTwoArguments(oneArgument));
        assertTrue(Validator.hasTwoArguments(twoArguments));
    }
}