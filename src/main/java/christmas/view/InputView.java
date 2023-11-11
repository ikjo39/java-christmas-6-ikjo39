package christmas.view;

import static christmas.constant.ExceptionMessage.INVALID_ORDER;
import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;
import static christmas.constant.OutputMessage.ENTER_EXPECTED_VISIT_DATE;
import static christmas.constant.OutputMessage.ENTER_MENU_AND_COUNT_TO_ORDER;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.OutputMessage;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private static final String ORDERS_DELIMITER = ",";

    public int readVisitDate() {
        printOutputMessage(ENTER_EXPECTED_VISIT_DATE);
        return convertValueToInteger(readLine());
    }

    private int convertValueToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_VISIT_DAY.getMessage());
        }
    }

    public List<String> readMenuAndCount() {
        printOutputMessage(ENTER_MENU_AND_COUNT_TO_ORDER);
        String input = readLine();
        validateEndWithDelimiter(input);
        return Arrays.stream(input.split(ORDERS_DELIMITER)).toList();
    }

    private void validateEndWithDelimiter(String input) {
        if (input.endsWith(ORDERS_DELIMITER)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private String readLine() {
        return Console.readLine();
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
