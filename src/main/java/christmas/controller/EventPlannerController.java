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
        outputView.printPlannerIntroduction();
        VisitDate visitDate = getVisitDate();
        OrderedMenus orderedMenus = getOrderedMenus();
        outputView.printEventPreviewIntroduction(visitDate);
        outputView.printOrderedMenus(orderedMenus);
        outputView.printTotalPriceBeforeDiscount(orderedMenus);
        TotalPrice totalPrice = new TotalPrice(orderedMenus.calculateTotalPrice());
        EventManager eventManager = new EventManager(new DiscountCalculator(orderedMenus, visitDate), totalPrice);
        outputView.printGiveAwayMenu(eventManager.getGiveaway());
        EventBenefits benefits = new EventBenefits(eventManager.getEventBenefits());
        outputView.printBenefits(benefits);
        outputView.printBenefitsTotal(benefits);
        TotalDiscountManager totalDiscountManager = new TotalDiscountManager(
                benefits.getTotalDiscounts(), eventManager
        );
        outputView.printAfterDiscounted(totalDiscountManager);
        outputView.printBadge(totalDiscountManager);
    }

    private OrderedMenus getOrderedMenus() {
        return retryHandler.retryUntilValid(() -> {
            OrderSheets orderSheets = new OrderSheets(inputView.readMenuAndCount());
            return new OrderedMenus(orderSheets.getOrderedMenus());
        });
    }

    private VisitDate getVisitDate() {
        return retryHandler.retryUntilValid(() -> new VisitDate(inputView.readVisitDate()));
    }
}
