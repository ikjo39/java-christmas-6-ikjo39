package christmas.model;

import christmas.constant.Menu;
import christmas.dto.GiveAway;

public class EventValidator {
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final TotalPrice totalPrice;

    public EventValidator(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveAway() {
        if (isEnabled() && totalPrice.isSatisfiedGiveaway()) {
            return new GiveAway(GIVE_AWAY);
        }
        return new GiveAway(null);
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
