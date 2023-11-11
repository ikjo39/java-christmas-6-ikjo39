package christmas.view;

import static christmas.constant.ExceptionMessage.INVALID_VISIT_DAY;
import static christmas.constant.OutputMessage.ENTER_EXPECTED_VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.OutputMessage;

public class InputView {

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

    private String readLine() {
        return Console.readLine();
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
