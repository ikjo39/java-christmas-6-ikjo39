package christmas;

import christmas.controller.EventPlannerController;
import christmas.handler.ExceptionRetryHandler;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ExceptionRetryHandler retryHandler = new ExceptionRetryHandler(outputView::printExceptionMessage);
        EventPlannerController eventPlannerController = new EventPlannerController(inputView, outputView, retryHandler);
        eventPlannerController.run();
    }
}
