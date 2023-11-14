package christmas.model;

import christmas.constant.EventBadge;
import java.util.Arrays;
import java.util.Comparator;

public class TotalDiscountManager {
    private final int totalDiscount;
    private final EventManager eventManager;

    public TotalDiscountManager(int totalDiscount, EventManager eventManager) {
        this.totalDiscount = totalDiscount;
        this.eventManager = eventManager;
    }

    public int getDiscountedTotalPrice() {
        return eventManager.getTotalDiscountedPrice(totalDiscount);
    }

    public EventBadge getEventBadge() {
        return Arrays.stream(EventBadge.values())
                .sorted(Comparator.comparingInt(EventBadge::getMinimumAmount).reversed())
                .filter(eventBadge -> eventBadge.isMoreThanMinimumAmount(totalDiscount))
                .findFirst()
                .orElse(EventBadge.NONE);
    }
}
