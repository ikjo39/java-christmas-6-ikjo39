package christmas.model;

import static christmas.constant.EventNameFormat.CHRISTMAS_D_DAY_EVENT;
import static christmas.constant.EventNameFormat.GIVEAWAY_EVENT;
import static christmas.constant.EventNameFormat.SPECIAL_EVENT;
import static christmas.constant.EventNameFormat.WEEKDAY_EVENT;
import static christmas.constant.EventNameFormat.WEEKEND_EVENT;

import christmas.constant.Menu;
import christmas.dto.EventBenefit;
import christmas.dto.GiveAway;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final Menu GIVEAWAY_MENU = Menu.CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    private final DiscountCalculator discountCalculator;
    private final TotalPrice totalPrice;

    public EventManager(DiscountCalculator discountCalculator, TotalPrice totalPrice) {
        this.discountCalculator = discountCalculator;
        this.totalPrice = totalPrice;
    }

    public GiveAway getGiveaway() {
        return new GiveAway(GIVE_AWAY, isSatisfiedGiveaway());
    }

    public List<EventBenefit> getEventBenefits() {
        List<EventBenefit> benefits = new ArrayList<>();
        if (!isEnabled()) {
            return benefits;
        }
        benefits.add(new EventBenefit(GIVEAWAY_EVENT, getGiveawayDiscount()));
        benefits.add((new EventBenefit(CHRISTMAS_D_DAY_EVENT, discountCalculator.getChristmasDiscount())));
        benefits.add((new EventBenefit(WEEKEND_EVENT, discountCalculator.getWeekendDiscount())));
        benefits.add((new EventBenefit(WEEKDAY_EVENT, discountCalculator.getWeekdayDiscount())));
        benefits.add(new EventBenefit(SPECIAL_EVENT, discountCalculator.getSpecialDiscount()));
        return benefits.stream().toList();
    }

    public int getTotalDiscountedPrice(int totalDiscount) {
        return totalPrice.calculateAfterDiscountedAmount(totalDiscount);
    }

    private int getGiveawayDiscount() {
        if (isSatisfiedGiveaway()) {
            return GIVE_AWAY.calculatePrice();
        }
        return 0;
    }

    private boolean isSatisfiedGiveaway() {
        return totalPrice.isSatisfiedGiveaway();
    }

    private boolean isEnabled() {
        return totalPrice.isEventEnabled();
    }
}
