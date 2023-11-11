package christmas.view;

import static christmas.constant.OutputMessage.PLANNER_INTRODUCTION;

import christmas.constant.OutputMessage;

public class OutputView {
    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printPlannerIntroduction() {
        printOutputMessage(PLANNER_INTRODUCTION);
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
