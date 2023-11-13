package christmas.model;

import static christmas.constant.DiscountEvent.CHRISTMAS_D_DAY_EVENT;
import static christmas.constant.DiscountEvent.SPECIAL_EVENT;
import static christmas.constant.DiscountEvent.WEEKDAY_EVENT;
import static christmas.constant.DiscountEvent.WEEKEND_EVENT;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.MAIN_DISH;

import christmas.constant.DiscountEvent;
import christmas.constant.Menu;
import christmas.dto.GiveAway;
import java.util.HashMap;
import java.util.Map;

public class EventValidator {
    private static final int CHRISTMAS_DISCOUNT_AMOUNT = 100;
    private static final int BASIC_DISCOUNT_AMOUNT = 1_000;
    private static final int WEEK_DISCOUNT_AMOUNT = 2023;
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final OrderedMenus orderedMenus;
    private final VisitDate visitDate;
    private final TotalPrice totalPrice;

    public EventValidator(OrderedMenus orderedMenus, VisitDate visitDate, TotalPrice totalPrice) {
        this.orderedMenus = orderedMenus;
        this.visitDate = visitDate;
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveAway() {
        return new GiveAway(GIVE_AWAY, totalPrice.isSatisfiedGiveaway());
    }

    public int getChristmasDiscount() {
        if (visitDate.isChristmasDiscountEnabled()) {
            int additionalBenefits = CHRISTMAS_DISCOUNT_AMOUNT * (visitDate.getDayOfMonth() - 1);
            return BASIC_DISCOUNT_AMOUNT + additionalBenefits;
        }
        return 0;
    }

    public int getWeekendDiscount() {
        if (visitDate.isWeekend()) {
            return orderedMenus.calculateCategoryCount(DESSERT) * WEEK_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    public int getWeekdayDiscount() {
        if (!visitDate.isWeekend()) {
            return orderedMenus.calculateCategoryCount(MAIN_DISH) * WEEK_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    public int getSpecialDiscount() {
        if (visitDate.isSpecialDay()) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    public Benefits getBenefits() {
        Map<DiscountEvent, Integer> discountEvents = new HashMap<>();
        if (!isEnabled()) {
            return new Benefits(discountEvents);
        }

        if (totalPrice.isSatisfiedGiveaway()) {
            discountEvents.put(DiscountEvent.GIVEAWAY_EVENT, GIVE_AWAY.calculatePrice());
        }

        int christmasDiscount = getChristmasDiscount();
        if (christmasDiscount > 0) {
            discountEvents.put(CHRISTMAS_D_DAY_EVENT, christmasDiscount);
        }
        int weekendDiscount = getWeekendDiscount();
        if (weekendDiscount > 0) {
            discountEvents.put(WEEKEND_EVENT, weekendDiscount);
        }

        int weekdayDiscount = getWeekdayDiscount();
        if (weekdayDiscount > 0) {
            discountEvents.put(WEEKDAY_EVENT, weekdayDiscount);
        }

        int specialDiscount = getSpecialDiscount();
        if (specialDiscount > 0) {
            discountEvents.put(SPECIAL_EVENT, specialDiscount);
        }

        return new Benefits(discountEvents);
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
