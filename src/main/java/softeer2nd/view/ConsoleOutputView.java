package softeer2nd.view;

import softeer2nd.controller.Command;

public class ConsoleOutputView implements OutputView {
    @Override
    public void print(final String text) {
        System.out.println(text);
    }

    @Override
    public void printCommandInputGuideLine(final String turn, final Command[] values) {
        print("커맨드를 입력해주세요.");
        print(String.format("현재 %s 입니다.", turn));
        for (int i = 0; i < values.length; i++) {
            Command command = values[i];
            print(String.format("%s. %s : %s", i + 1, command.name(), command.getValue()));
        }
    }
}
