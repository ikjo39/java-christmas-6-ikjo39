package christmas.model.event;

import static christmas.constant.EventDateConfig.WEEKDAY_EVENT_END_DAY;
import static christmas.constant.EventDateConfig.WEEKDAY_EVENT_START_DAY;
import static christmas.constant.EventDiscountAmount.WEEK_DISCOUNT;
import static christmas.constant.EventNameFormat.WEEKDAY_EVENT;
import static christmas.constant.MenuCategory.DESSERT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public class WeekdayEvent extends Event {
    public WeekdayEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(WEEKDAY_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return orderedMenus.calculateCategoryCount(DESSERT) * WEEK_DISCOUNT.getValue();
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return isTotalEventEnabled() && !visitDate.isWeekend()
                && visitDate.isVisitDateInRange(WEEKDAY_EVENT_START_DAY.getDate(), WEEKDAY_EVENT_END_DAY.getDate());
    }
}
