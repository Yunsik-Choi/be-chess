package softeer2nd.common.util;

public class StringUtils {
    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {
        throw new InstantiationError(this.getClass().getName() + "은 인스턴스화 할 수 없습니다.");
    }

    public static String appendNewLine(String text) {
        return text + NEWLINE;
    }
}
