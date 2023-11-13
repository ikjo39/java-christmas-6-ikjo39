package christmas.dto;

import static christmas.constant.OutputMessage.NONE;

import christmas.constant.DiscountEvent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public record Benefits(Map<DiscountEvent, Integer> events) {
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0");

    public List<String> convertOutputText() {
        if (events.isEmpty()) {
            return List.of(String.format(NONE.getMessage()));
        }
        return events.keySet().stream()
                .map(discountEvent ->
                        String.format(discountEvent.getFormat(), NUMBER_FORMAT.format(events.get(discountEvent))))
                .toList();
    }
}
