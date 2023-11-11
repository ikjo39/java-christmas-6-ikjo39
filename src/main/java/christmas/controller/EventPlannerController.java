package christmas.controller;

import christmas.handler.ExceptionRetryHandler;
import christmas.model.VisitDate;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventPlannerController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ExceptionRetryHandler retryHandler;

    public EventPlannerController(InputView inputView, OutputView outputView, ExceptionRetryHandler retryHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
    }

    public void run() {
        outputView.printPlannerIntroduction();
        VisitDate visitDate = getVisitDate();
    }

    private VisitDate getVisitDate() {
        return retryHandler.retryUntilValid(() -> new VisitDate(inputView.readVisitDate()));
    }
}
