package space.jaggard.yogaforposers.messages;

public enum Messages {
    GREETING("🙏🏼 Nameste yogi 🙏🏼"),
    INSTRUCTIONS("My name is Yogi Ja-va. I am here to help you save and keep " +
            "track of all your favourite yogic poses.")
    ;

    Messages(String message){
        this.message = message;
    }

    private String message;
    public String send() { return message; }


}
