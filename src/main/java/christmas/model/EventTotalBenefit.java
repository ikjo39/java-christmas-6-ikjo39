package christmas.model;

import christmas.constant.EventBadge;
import java.util.Arrays;
import java.util.Comparator;

public class EventTotalBenefit {
    private final int amount;

    public EventTotalBenefit(int amount) {
        this.amount = amount;
    }

    public EventBadge getEventBadge() {
        return Arrays.stream(EventBadge.values())
                .sorted(Comparator.comparingInt(EventBadge::getMinimumAmount).reversed())
                .filter(eventBadge -> amount > eventBadge.getMinimumAmount())
                .findFirst()
                .orElse(EventBadge.NONE);
    }
}
