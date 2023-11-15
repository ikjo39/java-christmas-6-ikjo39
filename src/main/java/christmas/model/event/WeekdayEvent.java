package christmas.model.event;

import static christmas.constant.EventNameFormat.WEEKDAY_EVENT;
import static christmas.constant.MenuCategory.DESSERT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;
import java.time.LocalDate;

public class WeekdayEvent extends Event {
    private static final int EVENT_START_DAY = 1;
    private static final int EVENT_END_DAY = 31;
    private static final LocalDate eventStart = LocalDate.of(2023, 12, EVENT_START_DAY);
    private static final LocalDate eventEnd = LocalDate.of(2023, 12, EVENT_END_DAY);
    private static final int WEEK_DISCOUNT_AMOUNT = 2_023;

    public WeekdayEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(WEEKDAY_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return orderedMenus.calculateCategoryCount(DESSERT) * WEEK_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return isTotalEventEnabled() && !visitDate.isWeekend() && visitDate.isVisitDateInRange(eventStart, eventEnd);
    }
}
