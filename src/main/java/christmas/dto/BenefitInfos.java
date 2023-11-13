package christmas.dto;

import christmas.constant.DiscountEvent;
import java.util.Map;

public record BenefitInfos(Map<DiscountEvent, Integer> events, boolean isEmpty) {
}
