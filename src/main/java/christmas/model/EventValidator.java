package christmas.model;

import christmas.constant.DiscountEvent;
import christmas.constant.Menu;
import christmas.dto.Benefits;
import christmas.dto.GiveAway;
import java.util.HashMap;
import java.util.Map;

public class EventValidator {
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final TotalPrice totalPrice;

    public EventValidator(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveAway() {
        if (totalPrice.isSatisfiedGiveaway()) {
            return new GiveAway(GIVE_AWAY);
        }
        return new GiveAway(null);
    }

    public Benefits getBenefits() {
        Map<DiscountEvent, Integer> discountEvents = new HashMap<>();
        if (!isEnabled()) {
            return new Benefits(discountEvents);
        }
        if (totalPrice.isSatisfiedGiveaway()) {
            discountEvents.put(DiscountEvent.GIVEAWAY_EVENT, GIVE_AWAY.calculatePrice());
        }
        return new Benefits(discountEvents);
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
