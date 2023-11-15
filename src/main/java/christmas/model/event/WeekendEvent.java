package christmas.model.event;

import static christmas.constant.EventDateConfig.WEEKEND_EVENT_END_DAY;
import static christmas.constant.EventDateConfig.WEEKEND_EVENT_START_DAY;
import static christmas.constant.EventDiscountAmount.WEEK_DISCOUNT;
import static christmas.constant.EventNameFormat.WEEKEND_EVENT;
import static christmas.constant.MenuCategory.MAIN_DISH;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public class WeekendEvent extends Event {
    public WeekendEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(WEEKEND_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return orderedMenus.calculateCategoryCount(MAIN_DISH) * WEEK_DISCOUNT.getValue();
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return isTotalEventEnabled() && visitDate.isWeekend()
                && visitDate.isVisitDateInRange(WEEKEND_EVENT_START_DAY.getDate(), WEEKEND_EVENT_END_DAY.getDate());
    }
}
