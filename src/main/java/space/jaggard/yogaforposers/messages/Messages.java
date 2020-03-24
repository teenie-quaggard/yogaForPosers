package space.jaggard.yogaforposers.messages;

public enum Messages {
    GREETING("\n--------------------\n 🙏🏼 Nameste yogi 🙏🏼 " +
            "\n--------------------\n"),
    INSTRUCTIONS("My name is Yogi Ja-va. I am here to help you save and keep " +
            "track of all your favourite yogic poses.\nWhat would you like " +
            "to do?\n"),
    MENU("MENU:\n---------------\n" +
            "-add : to add an entry to your saved list of yoga poses\n" +
            "-view : to see what you have already saved to your list\n" +
            "-edit X : used when you'd like to edit an entry, where X is the " +
            "number of the entry\n" +
            "-delete X : used to delete an entry, where X is the number of " +
            "the entry\n" +
            "-exit : to close the program and lose your saved data"),
    ERR_WRONG_COMMAND("Sorry, incorrect command. Please try again with one " +
            "of the following:\n"),
    EMPTY_LIST("At the moment, you do not have any saved yoga poses. When " +
            "you're ready to add one to your list, type '-add':"),
    ADD_BEGIN("Great! Let's add to your favourite yoga poses.\n"),
    ADD_ENGLISH_NAME("What's the english name for the pose?: "),
    ADD_SANSKRIT_NAME("What's the Sanskrit name for the pose?: "),
    ADD_POSE_TYPE("What's the pose type?: "),
    ADD_BENEFITS("Finally, what health benefits does the pose have?: "),
    ADD_END("Thanks! Here's what you have saved to your list: \n"),
    EXIT("Bye bye! Hope to see you soon! 👋🏼")
    ;

    Messages(String message){
        this.message = message;
    }

    private String message;
    public String send() { return message; }


}
