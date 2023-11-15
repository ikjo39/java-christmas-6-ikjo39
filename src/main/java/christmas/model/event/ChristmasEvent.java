package christmas.model.event;

import static christmas.constant.EventNameFormat.CHRISTMAS_D_DAY_EVENT;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;
import java.time.LocalDate;

public class ChristmasEvent extends Event {
    private static final int EVENT_START_DAY = 1;
    private static final LocalDate eventStart = LocalDate.of(2023, 12, EVENT_START_DAY);
    private static final int CHRISTMAS_DISCOUNT_AMOUNT = 100;
    private static final int BASIC_DISCOUNT_AMOUNT = 900;

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
            return CHRISTMAS_DISCOUNT_AMOUNT * visitDate.getDayOfMonth() + BASIC_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    @Override
    protected boolean isEventEnabled() {
        return isTotalEventEnabled() && visitDate.isChristmasEventRange(eventStart);
    }
}
