package christmas.model.event;

import static christmas.constant.EventDateConfig.CHRISTMAS_EVENT_END_DAY;
import static christmas.constant.EventDateConfig.CHRISTMAS_EVENT_START_DAY;
import static christmas.constant.EventDiscountAmount.CHRISTMAS_BASIC_DISCOUNT;
import static christmas.constant.EventDiscountAmount.CHRISTMAS_DISCOUNT;
import static christmas.constant.EventNameFormat.CHRISTMAS_D_DAY_EVENT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public class ChristmasEvent extends Event {
    public ChristmasEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    @Override
    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(CHRISTMAS_D_DAY_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return CHRISTMAS_DISCOUNT.getValue() * visitDate.getDayOfMonth() + CHRISTMAS_BASIC_DISCOUNT.getValue();
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return isTotalEventEnabled()
                && visitDate.isVisitDateInRange(CHRISTMAS_EVENT_START_DAY.getDate(), CHRISTMAS_EVENT_END_DAY.getDate());
    }
}
