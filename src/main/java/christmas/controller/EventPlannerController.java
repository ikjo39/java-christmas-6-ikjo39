package christmas.controller;

import christmas.dto.EventBenefits;
import christmas.handler.ExceptionRetryHandler;
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
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        EventManager eventManager = new EventManager(visitDate, orderedMenus, totalPrice);
        EventBenefits benefits = new EventBenefits(eventManager.getEventBenefits());

        printGivenUserInfo(visitDate, orderedMenus);
        printEventResult(totalPrice, eventManager, benefits);
    }

    private void printIntroduction() {
        outputView.printPlannerIntroduction();
    }

    private void printGivenUserInfo(VisitDate visitDate, OrderedMenus orderedMenus) {
        outputView.printEventPreviewIntroduction(visitDate);
        outputView.printOrderedMenus(orderedMenus);
        outputView.printTotalPriceBeforeDiscount(orderedMenus);
    }

    private void printEventResult(TotalPrice totalPrice, EventManager eventManager, EventBenefits benefits) {
        printDiscountInfo(eventManager, benefits);
        printDiscountedPriceAndBadge(totalPrice, benefits);
    }

    private void printDiscountInfo(EventManager eventManager, EventBenefits benefits) {
        outputView.printGiveAwayMenu(eventManager.getGiveaway());
        outputView.printBenefits(benefits);
        outputView.printBenefitsTotal(benefits);
    }

    private void printDiscountedPriceAndBadge(TotalPrice totalPrice, EventBenefits benefits) {
        TotalDiscountManager totalDiscountManager = new TotalDiscountManager(totalPrice, benefits);
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
