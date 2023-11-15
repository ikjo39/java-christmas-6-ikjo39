package christmas.model.event;

import static christmas.constant.EventDateConfig.SPECIAL_EVENT_END_DAY;
import static christmas.constant.EventDateConfig.SPECIAL_EVENT_START_DAY;
import static christmas.constant.EventDiscountAmount.SPECIAL_DISCOUNT;
import static christmas.constant.EventNameFormat.SPECIAL_EVENT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public class SpecialEvent extends Event {
    public SpecialEvent(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        super(visitDate, orderedMenus, totalPrice);
    }

    @Override
    public EventBenefit getEventBenefit() {
        int discountAmount = getDiscountAmount();
        return new EventBenefit(SPECIAL_EVENT, discountAmount);
    }

    private int getDiscountAmount() {
        if (isEventEnabled()) {
            return SPECIAL_DISCOUNT.getValue();
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return totalPrice.isEventEnabled() && visitDate.isSpecialDate()
                && visitDate.isVisitDateInRange(SPECIAL_EVENT_START_DAY.getDate(), SPECIAL_EVENT_END_DAY.getDate());
    }
}
