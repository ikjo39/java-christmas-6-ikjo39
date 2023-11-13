package christmas.model;

import static christmas.constant.DiscountEvent.CHRISTMAS_D_DAY_EVENT;

import christmas.constant.DiscountEvent;
import christmas.constant.Menu;
import christmas.dto.Benefits;
import christmas.dto.GiveAway;
import java.util.HashMap;
import java.util.Map;

public class EventValidator {
    private static final int BASIC_DISCOUNT_AMOUNT = 1_000;
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final VisitDate visitDate;
    private final TotalPrice totalPrice;

    public EventValidator(VisitDate visitDate, TotalPrice totalPrice) {
        this.visitDate = visitDate;
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveAway() {
        if (totalPrice.isSatisfiedGiveaway()) {
            return new GiveAway(GIVE_AWAY);
        }
        return new GiveAway(null);
    }

    public int getChristmasDiscount() {
        if (visitDate.isChristmasDiscountEnabled()) {
            return BASIC_DISCOUNT_AMOUNT + CHRISTMAS_D_DAY_EVENT.getDiscountAmount() * (visitDate.getDayOfMonth() - 1);
        }
        return 0;
    }

    public Benefits getBenefits() {
        Map<DiscountEvent, Integer> discountEvents = new HashMap<>();
        if (!isEnabled()) {
            return new Benefits(discountEvents);
        }

        int christmasDiscount = getChristmasDiscount();
        if (totalPrice.isSatisfiedGiveaway()) {
            discountEvents.put(DiscountEvent.GIVEAWAY_EVENT, GIVE_AWAY.calculatePrice());
        }
        if (christmasDiscount > 0) {
            discountEvents.put(CHRISTMAS_D_DAY_EVENT, christmasDiscount);
        }
        return new Benefits(discountEvents);
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
