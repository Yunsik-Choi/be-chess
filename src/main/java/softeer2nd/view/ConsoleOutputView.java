package softeer2nd.view;

import softeer2nd.controller.Command;

public class ConsoleOutputView implements OutputView {
    @Override
    public void print(final String text) {
        System.out.println(text);
    }

    @Override
    public void printCommandInputGuideLine(final Command[] values) {
        print("커맨드를 입력해주세요.");
        for (int i = 0; i < values.length; i++) {
            Command command = values[i];
            print(String.format("%s. %s : %s", i + 1, command.name(), command.getValue()));
        }
    }
}
