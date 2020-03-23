package space.jaggard.yogaforposers.messages;

public enum Messages {
    GREETING("Hello world!");

    Messages(String message){
        this.message = message;
    }

    private String message;
    public String sendMessage() { return message; }


}
