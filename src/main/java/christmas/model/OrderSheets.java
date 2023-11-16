package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_ORDER;

import christmas.constant.Menu;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderSheets {
    private static final String MENU_COUNT_DELIMITER = "-";
    private static final String ORDER_FORMAT_REGEX = "[가-힣]+-[0-9]+";
    private static final Pattern ORDER_FORMAT_PATTERN = Pattern.compile(ORDER_FORMAT_REGEX);

    private final List<String> orderSheets;

    public OrderSheets(List<String> orderSheets) {
        orderSheets.forEach(this::validateOrderFormat);
        validateMenuNameDuplicated(orderSheets);
        this.orderSheets = orderSheets;
    }

    private void validateOrderFormat(String order) {
        Matcher matcher = ORDER_FORMAT_PATTERN.matcher(order);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateMenuNameDuplicated(List<String> orderSheets) {
        Set<String> distinctMenuNames = getDistinctMenuNames(orderSheets);
        if (distinctMenuNames.size() != orderSheets.size()) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private Set<String> getDistinctMenuNames(List<String> orderSheets) {
        return orderSheets.stream()
                .map(orderSheet -> orderSheet.split(MENU_COUNT_DELIMITER)[0])
                .collect(Collectors.toUnmodifiableSet());
    }

    public List<OrderedMenu> getOrderedMenus() {
        return orderSheets.stream()
                .map(orderSheet -> orderSheet.split(MENU_COUNT_DELIMITER))
                .map(splitSheet -> {
                            String menuName = splitSheet[0];
                            String amount = splitSheet[1];
                            return new OrderedMenu(Menu.from(menuName), convertValueToInt(amount));
                        }
                ).toList();
    }

    private int convertValueToInt(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }
}
