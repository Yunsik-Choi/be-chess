package softeer2nd.view;

public class ConsoleOutputView implements OutputView {
    @Override
    public void print(final String text) {
        System.out.println(text);
    }
}
