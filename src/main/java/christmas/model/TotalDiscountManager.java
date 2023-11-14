package christmas.model;

import christmas.constant.EventBadge;
import christmas.dto.EventBenefits;
import java.util.Arrays;
import java.util.Comparator;

public class TotalDiscountManager {
    private final EventBenefits benefits;
    private final EventManager eventManager;

    public TotalDiscountManager(EventBenefits benefits, EventManager eventManager) {
        this.benefits = benefits;
        this.eventManager = eventManager;
    }

    public int getDiscountedTotalPrice() {
        return eventManager.getTotalDiscountedPrice(benefits.getTotalDiscounts());
    }

    public EventBadge getEventBadge() {
        return Arrays.stream(EventBadge.values())
                .sorted(Comparator.comparingInt(EventBadge::getMinimumAmount).reversed())
                .filter(eventBadge -> eventBadge.isMoreThanMinimumAmount(benefits.getTotalBenefits()))
                .findFirst()
                .orElse(EventBadge.NONE);
    }
}
