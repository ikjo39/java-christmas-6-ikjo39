package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.OutputMessage;

public class InputView {

    private String readLine() {
        return Console.readLine();
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
