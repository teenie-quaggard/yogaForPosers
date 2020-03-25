package space.jaggard.yogaforposers.messages;

import space.jaggard.yogaforposers.commands.Commands;

public enum Messages {
    GREETING("\n--------------------\n 🙏🏼 Nameste yogi 🙏🏼 " +
            "\n--------------------\n"),
    INTRO("Hello, my name is Yogi Ja-Va. \nI am here to help you save and " +
            "keep track of all your favourite yogic poses.\n"),
    MENU("What would you like to do? \n" +
            "-----------------------------------------------------------------------\n" +
            Commands.ADD.stringify() +
            "     | 👆 to add an entry to your saved list of yoga poses\n" +
            Commands.LIST.stringify() +
            "    | 🔭 to see what you have already saved to your list\n" +
            Commands.EDIT.stringify() +
            "  | ✂️ to edit an entry, where X is the number of the entry️\n" +
            Commands.DEL.stringify() +
            "   | ❌ to delete an entry, where X is the number of the entry\n" +
            Commands.EXIT.stringify() +
            "    | ✌️ to close the program (FYI - you'll lose your data!)\n" +
            "-----------------------------------------------------------------------\n" +
            "Enter a command from above and click 'return' on your keyboard: "),
    ERR_WRONG_COMMAND("🙈 Sorry, incorrect command. Please try again. 🙈"),
    EMPTY_LIST("At the moment, you do not have any saved yoga poses. When " +
            "you're ready to add one to your list, type '-add':"),
    LIST("YOUR LIST:‍"),
    ADD_PROMPT("Great! Let's add to your favourite yoga poses.\n"),
    ADD_ENGLISH_NAME("What's the english name for the pose?: "),
    ADD_SANSKRIT_NAME("What's the Sanskrit name for the pose?: "),
    ADD_POSE_TYPE("What's the pose type?: "),
    ADD_BENEFITS("Finally, what health benefits does the pose have?: "),
    ADD_FINISHED("Thanks! Here's what you have saved to your list: \n"),
    REVIEW_ENTRY_PROMPT("Here's the entry that you've selected:\n"),
    EDIT_PROMPT("Is this the entry you'd like to edit? (Y/N): "),
    EDIT_FIELD("Which field would you like to edit? Enter the number that " +
            "corresponds to the correct field. (1-4):\n" +
            "1. English name\n" +
            "2. Sanskrit name\n" +
            "3. Pose type\n" +
            "4. Health benefits"),
    EDIT_WRONG_ENTRY("Wrong entry? No problem, let's try again."),
    EDIT_FINISHED("All done!"),
    DELETE_ENTRY("⚠️ Are you sure you'd like to delete this entry? " +
            "(Y/N): ⚠️️"),
    CONFIRM_DELETE("Entry deleted ❌\n"),
    UNCONFIRMED_DELETE("No stress, we will save that for later."),
    EXIT("Bye bye! Hope to see you soon! 👋🏼")
    ;

    Messages(String message){
        this.message = message;
    }

    private String message;
    public String stringify() { return message; }


}
