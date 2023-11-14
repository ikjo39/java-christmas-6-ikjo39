package christmas.dto;

import static christmas.constant.EventNameFormat.GIVEAWAY_EVENT;

import christmas.constant.EventNameFormat;

public record EventBenefit(EventNameFormat eventNameFormat, int discountAmount) {

    public boolean isGiveawayEvent() {
        return eventNameFormat.equals(GIVEAWAY_EVENT);
    }

    public boolean isEventEnabled() {
        return discountAmount != 0;
    }
}
