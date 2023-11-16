package christmas.model;

import christmas.dto.EventBenefit;
import christmas.dto.Giveaway;
import christmas.model.event.ChristmasEvent;
import christmas.model.event.Event;
import christmas.model.event.GiveawayEvent;
import christmas.model.event.SpecialEvent;
import christmas.model.event.WeekdayEvent;
import christmas.model.event.WeekendEvent;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final Event christmasEvent;
    private final Event weekendEvent;
    private final Event weekdayEvent;
    private final Event specialEvent;
    private final GiveawayEvent giveawayEvent;

    public EventManager(VisitDate visitDate, OrderedMenus orderedMenus, TotalPrice totalPrice) {
        this.christmasEvent = new ChristmasEvent(visitDate, orderedMenus, totalPrice);
        this.weekendEvent = new WeekendEvent(visitDate, orderedMenus, totalPrice);
        this.weekdayEvent = new WeekdayEvent(visitDate, orderedMenus, totalPrice);
        this.specialEvent = new SpecialEvent(visitDate, orderedMenus, totalPrice);
        this.giveawayEvent = new GiveawayEvent(visitDate, orderedMenus, totalPrice);
    }

    public Giveaway getGiveaway() {
        return giveawayEvent.getGiveaway();
    }

    public List<EventBenefit> getEventBenefits() {
        List<EventBenefit> benefits = new ArrayList<>();
        if (!christmasEvent.isTotalEventEnabled()) {
            return benefits;
        }
        benefits.add(christmasEvent.getEventBenefit());
        benefits.add(weekendEvent.getEventBenefit());
        benefits.add(weekdayEvent.getEventBenefit());
        benefits.add(specialEvent.getEventBenefit());
        benefits.add(giveawayEvent.getEventBenefit());
        return benefits.stream().toList();
    }
}
