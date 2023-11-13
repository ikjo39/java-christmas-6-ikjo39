package christmas.model;

import static christmas.constant.DiscountEvent.GIVEAWAY_EVENT;

import christmas.constant.DiscountEvent;
import christmas.dto.BenefitInfos;
import java.util.Map;

public class Benefits {
    private final Map<DiscountEvent, Integer> events;

    public Benefits(Map<DiscountEvent, Integer> events) {
        this.events = events;
    }

    public BenefitInfos getBenefitInfos() {
        return new BenefitInfos(events, events.isEmpty());
    }

    public int getTotalBenefits() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscounts() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.keySet()
                .stream()
                .filter(discountEvent -> !discountEvent.equals(GIVEAWAY_EVENT))
                .map(events::get)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
