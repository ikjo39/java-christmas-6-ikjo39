package christmas.model.event;

import static christmas.constant.EventNameFormat.SPECIAL_EVENT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;
import java.time.LocalDate;

public class SpecialEvent extends Event {
    private static final int EVENT_START_DAY = 1;
    private static final int EVENT_END_DAY = 31;
    private static final LocalDate eventStart = LocalDate.of(2023, 12, EVENT_START_DAY);
    private static final LocalDate eventEnd = LocalDate.of(2023, 12, EVENT_END_DAY);
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;

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
            return SPECIAL_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return totalPrice.isEventEnabled() && visitDate.isSpecialDate()
                && visitDate.isVisitDateInRange(eventStart, eventEnd);
    }
}
