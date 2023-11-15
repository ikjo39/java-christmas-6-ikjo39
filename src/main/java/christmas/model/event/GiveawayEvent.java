package christmas.model.event;

import static christmas.constant.EventDateConfig.GIVEAWAY_EVENT_END_DAY;
import static christmas.constant.EventDateConfig.GIVEAWAY_EVENT_START_DAY;
import static christmas.constant.EventNameFormat.GIVEAWAY_EVENT;
import static christmas.constant.Menu.CHAMPAGNE;

import christmas.constant.Menu;
import christmas.dto.EventBenefit;
import christmas.dto.Giveaway;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public class GiveawayEvent extends Event {
    private static final int GIVE_AWAY_AMOUNT = 1;
    private static final Menu GIVEAWAY_MENU = CHAMPAGNE;
    private static final OrderedMenu GIVE_AWAY = new OrderedMenu(GIVEAWAY_MENU, GIVE_AWAY_AMOUNT);

    public GiveawayEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    public Giveaway getGiveaway() {
        return new Giveaway(GIVE_AWAY, isEventEnabled());
    }

    @Override
    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(GIVEAWAY_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return GIVE_AWAY.calculatePrice();
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return totalPrice.isSatisfiedGiveaway()
                && visitDate.isVisitDateInRange(GIVEAWAY_EVENT_START_DAY.getDate(), GIVEAWAY_EVENT_END_DAY.getDate());
    }
}
