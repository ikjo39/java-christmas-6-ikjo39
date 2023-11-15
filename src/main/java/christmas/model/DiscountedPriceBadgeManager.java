package christmas.model;

import christmas.constant.EventBadge;
import christmas.dto.EventBenefits;
import java.util.Arrays;
import java.util.Comparator;

public class DiscountedPriceBadgeManager {
    private final TotalPrice totalPrice;
    private final EventBenefits benefits;

    public DiscountedPriceBadgeManager(TotalPrice totalPrice, EventBenefits benefits) {
        this.totalPrice = totalPrice;
        this.benefits = benefits;
    }

    public int getDiscountedTotalPrice() {
        return totalPrice.calculateAfterDiscountedAmount(benefits.getTotalDiscounts());
    }

    public EventBadge getEventBadge() {
        return Arrays.stream(EventBadge.values())
                .sorted(Comparator.comparingInt(EventBadge::getMinimumAmount).reversed())
                .filter(eventBadge -> eventBadge.isMoreThanMinimumAmount(benefits.getTotalBenefits()))
                .findFirst()
                .orElse(EventBadge.NONE);
    }
}
