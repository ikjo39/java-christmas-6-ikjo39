package christmas.model;

import static christmas.constant.DiscountEvent.GIVEAWAY_EVENT;
import static christmas.constant.OutputMessage.NONE;

import christmas.constant.DiscountEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class Benefits {
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0");
    private final Map<DiscountEvent, Integer> events;

    public Benefits(Map<DiscountEvent, Integer> events) {
        this.events = events;
    }

    public List<String> convertOutputText() {
        if (events.isEmpty()) {
            return List.of(String.format(NONE.getMessage()));
        }
        return events.keySet().stream()
                .map(discountEvent ->
                        String.format(discountEvent.getFormat(), NUMBER_FORMAT.format(events.get(discountEvent))))
                .toList();
    }

    public int getTotalBenefits() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscounts() {
        if (events.isEmpty()) {
            return 0;
        }
        return events.keySet()
                .stream()
                .filter(discountEvent -> !discountEvent.equals(GIVEAWAY_EVENT))
                .map(events::get)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
