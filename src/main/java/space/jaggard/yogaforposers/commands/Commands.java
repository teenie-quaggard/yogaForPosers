package space.jaggard.yogaforposers.commands;

public enum Commands {
    ADD("-ADD"),
    LIST("-LIST"),
    EDIT("-EDIT X"),
    DEL("-DEL X"),
    EXIT("-EXIT");

    private String command;
    Commands(String command) { this.command = command; }

    public String stringify() { return command; }

}
