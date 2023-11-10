package christmas;

import christmas.controller.EventPlannerController;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventPlannerController eventPlannerController = new EventPlannerController(new InputView(), new OutputView());
        eventPlannerController.run();
    }
}
