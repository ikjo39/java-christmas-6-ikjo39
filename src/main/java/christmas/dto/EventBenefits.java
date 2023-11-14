package christmas.dto;

import java.util.List;

public record EventBenefits(List<EventBenefit> eventBenefits) {

    public boolean isNone() {
        long disabledEventCount = eventBenefits.stream()
                .filter(eventBenefit -> !eventBenefit.isEventEnabled())
                .count();
        return disabledEventCount == eventBenefits.size();
    }

    public int getTotalBenefits() {
        return eventBenefits.stream()
                .map(EventBenefit::discountAmount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscounts() {
        return eventBenefits.stream()
                .filter(eventBenefit -> !eventBenefit.isGiveawayEvent())
                .map(EventBenefit::discountAmount)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<EventBenefit> getEnabledBenefits() {
        return eventBenefits
                .stream()
                .filter(EventBenefit::isEventEnabled)
                .toList();
    }
}
