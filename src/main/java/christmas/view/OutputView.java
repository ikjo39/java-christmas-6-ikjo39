package christmas.view;

import static christmas.constant.OutputMessage.EVENT_PREVIEW_INTRODUCTION;
import static christmas.constant.OutputMessage.PLANNER_INTRODUCTION;

import christmas.constant.OutputMessage;
import christmas.model.VisitDate;

public class OutputView {
    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printPlannerIntroduction() {
        printOutputMessage(PLANNER_INTRODUCTION);
    }

    public void printEventPreviewIntroduction(VisitDate visitDate) {
        System.out.printf(EVENT_PREVIEW_INTRODUCTION.getMessage(), visitDate.getMonth(), visitDate.getDayOfMonth());
        System.out.println();
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
