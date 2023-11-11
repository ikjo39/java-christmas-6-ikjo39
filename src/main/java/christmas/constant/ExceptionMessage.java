package christmas.constant;

public enum ExceptionMessage {
    INVALID_VISIT_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    private static final String HEADER = "[ERROR]";

    private final String message;

    ExceptionMessage(String message) {
        this.message = String.format("%s %s", HEADER, message);
    }

    public String getMessage() {
        return message;
    }
}
