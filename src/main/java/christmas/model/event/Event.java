package christmas.model.event;

import christmas.dto.EventBenefit;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;

public abstract class Event {
    protected final VisitDate visitDate;
    protected final OrderedMenus orderedMenus;
    protected final TotalPrice totalPrice;

    protected Event(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        this.visitDate = visitDate;
        this.orderedMenus = orderedMenus;
        this.totalPrice = totalPrice;
    }

    public abstract EventBenefit getEventBenefit();

    protected abstract boolean isEventEnabled();

    public boolean isTotalEventEnabled() {
        return totalPrice.isEventEnabled();
    }
}
