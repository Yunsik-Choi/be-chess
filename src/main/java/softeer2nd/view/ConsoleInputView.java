package softeer2nd.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public String command() {
        return scanner.next();
    }
}
