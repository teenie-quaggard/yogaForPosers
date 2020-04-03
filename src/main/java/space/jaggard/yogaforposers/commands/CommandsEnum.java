package space.jaggard.yogaforposers.commands;

public enum CommandsEnum {
    ADD("-ADD"),
    LIST("-LIST"),
    EDIT("-EDIT X"),
    DEL("-DEL X"),
    EXIT("-EXIT");

    private String command;
    CommandsEnum(String command) { this.command = command; }

    public String stringify() { return command; }

}
