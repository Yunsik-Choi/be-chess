package softeer2nd.controller;

import java.util.Arrays;

public enum Command {
    START("start"),
    END("end"),
    MOVE("move");

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_SOURCE_INDEX = 1;
    private static final int MOVE_TARGET_INDEX = 2;

    private final String value;

    Command(final String command) {
        this.value = command;
    }

    public static boolean isEnd(final String command) {
        return command.equals(END.value);
    }

    public static boolean isStart(final String command) {
        return command.equals(START.value);
    }

    public static boolean isMove(final String command) {
        return command.startsWith(MOVE.value);
    }

    public static boolean isNotExistsCommand(final String command) {
        if (command.startsWith(MOVE.value)) {
            return false;
        }
        return Arrays.stream(Command.values()).filter(c -> c.value.equals(command)).findFirst().isEmpty();
    }

    public static String moveSource(final String command) {
        return command.split(MOVE_DELIMITER)[MOVE_SOURCE_INDEX];
    }

    public static String moveTarget(final String command) {
        return command.split(MOVE_DELIMITER)[MOVE_TARGET_INDEX];
    }

    public String getValue() {
        return value;
    }
}
