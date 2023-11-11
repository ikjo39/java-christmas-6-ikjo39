package christmas.model;

import static christmas.constant.ExceptionMessage.MORE_THAN_MAXIMUM_ORDER_COUNT;
import static christmas.constant.ExceptionMessage.ORDER_ONLY_DRINK;

import java.util.List;

public class OrderedMenus {
    private static final int MAX_ORDER_COUNT = 20;

    private final List<OrderedMenu> orderedMenus;

    public OrderedMenus(List<OrderedMenu> orderedMenus) {
        validateMenusOnlyDrink(orderedMenus);
        validateMenuCount(orderedMenus);
        this.orderedMenus = orderedMenus;
    }

    private void validateMenusOnlyDrink(List<OrderedMenu> orderMenus) {
        long count = orderMenus.stream()
                .filter(OrderedMenu::isMenuDrink)
                .count();
        if (count == orderMenus.size()) {
            throw new IllegalArgumentException(ORDER_ONLY_DRINK.getMessage());
        }
    }

    private void validateMenuCount(List<OrderedMenu> orderedMenus) {
        int countSum = orderedMenus.stream()
                .map(OrderedMenu::getAmount)
                .mapToInt(Integer::intValue)
                .sum();
        if (countSum > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(MORE_THAN_MAXIMUM_ORDER_COUNT.getMessage());
        }
    }
}
