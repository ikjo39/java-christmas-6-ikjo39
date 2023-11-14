package christmas.model;

import static christmas.constant.DiscountEvent.CHRISTMAS_D_DAY_EVENT;
import static christmas.constant.DiscountEvent.SPECIAL_EVENT;
import static christmas.constant.DiscountEvent.WEEKDAY_EVENT;
import static christmas.constant.DiscountEvent.WEEKEND_EVENT;

import christmas.constant.DiscountEvent;
import christmas.constant.Menu;
import christmas.dto.GiveAway;
import java.util.HashMap;
import java.util.Map;

public class EventValidator {
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final DiscountCalculator discountCalculator;
    private final TotalPrice totalPrice;

    public EventValidator(DiscountCalculator discountCalculator, TotalPrice totalPrice) {
        this.discountCalculator = discountCalculator;
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveAway() {
        return new GiveAway(GIVE_AWAY, totalPrice.isSatisfiedGiveaway());
    }


    public Benefits getBenefits() {
        Map<DiscountEvent, Integer> benefits = new HashMap<>();
        if (!isEnabled()) {
            return new Benefits(benefits);
        }
        benefits.put(DiscountEvent.GIVEAWAY_EVENT, getGiveawayDiscount());
        benefits.put(CHRISTMAS_D_DAY_EVENT, discountCalculator.getChristmasDiscount());
        benefits.put(WEEKDAY_EVENT, discountCalculator.getWeekdayDiscount());
        benefits.put(WEEKEND_EVENT,discountCalculator.getWeekendDiscount());
        benefits.put(SPECIAL_EVENT, discountCalculator.getSpecialDiscount());
        return new Benefits(benefits);
    }

    private int getGiveawayDiscount() {
        if (totalPrice.isSatisfiedGiveaway()) {
            return GIVE_AWAY.calculatePrice();
        }
        return 0;
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
