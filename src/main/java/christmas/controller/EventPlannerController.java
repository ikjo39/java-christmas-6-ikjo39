package christmas.controller;

import christmas.dto.EventBenefits;
import christmas.handler.ExceptionRetryHandler;
import christmas.model.DiscountCalculator;
import christmas.model.EventManager;
import christmas.model.OrderSheets;
import christmas.model.OrderedMenus;
import christmas.model.TotalDiscountManager;
import christmas.model.TotalPrice;
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
        printIntroduction();

        VisitDate visitDate = getVisitDateUntilValidInput();
        OrderedMenus orderedMenus = getOrderedMenusUntilValidInput();
        EventManager eventManager = getEventManager(visitDate, orderedMenus);
        EventBenefits benefits = new EventBenefits(eventManager.getEventBenefits());

        printGivenUserInfo(visitDate, orderedMenus);
        printEventResult(eventManager, benefits);
    }

    private void printIntroduction() {
        outputView.printPlannerIntroduction();
    }

    private static EventManager getEventManager(VisitDate visitDate, OrderedMenus orderedMenus) {
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        return new EventManager(new DiscountCalculator(orderedMenus, visitDate), totalPrice);
    }

    private void printGivenUserInfo(VisitDate visitDate, OrderedMenus orderedMenus) {
        outputView.printEventPreviewIntroduction(visitDate);
        outputView.printOrderedMenus(orderedMenus);
        outputView.printTotalPriceBeforeDiscount(orderedMenus);
    }

    private void printEventResult(EventManager eventManager, EventBenefits benefits) {
        printDiscountInfo(eventManager, benefits);
        printDiscountedPriceAndBadge(eventManager, benefits);
    }

    private void printDiscountInfo(EventManager eventManager, EventBenefits benefits) {
        outputView.printGiveAwayMenu(eventManager.getGiveaway());
        outputView.printBenefits(benefits);
        outputView.printBenefitsTotal(benefits);
    }

    private void printDiscountedPriceAndBadge(EventManager eventManager, EventBenefits benefits) {
        TotalDiscountManager totalDiscountManager =
                new TotalDiscountManager(benefits, eventManager);
        outputView.printAfterDiscounted(totalDiscountManager);
        outputView.printBadge(totalDiscountManager);
    }

    private OrderedMenus getOrderedMenusUntilValidInput() {
        return retryHandler.retryUntilValid(() -> {
            OrderSheets orderSheets = new OrderSheets(inputView.readMenuAndCount());
            return new OrderedMenus(orderSheets.getOrderedMenus());
        });
    }

    private VisitDate getVisitDateUntilValidInput() {
        return retryHandler.retryUntilValid(() -> new VisitDate(inputView.readVisitDate()));
    }
}
