package softeer2nd.view;

import softeer2nd.controller.Command;

public interface OutputView {
    void print(final String text);

    void printCommandInputGuideLine(final String turn, Command[] values);
}
