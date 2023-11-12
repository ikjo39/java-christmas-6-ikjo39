package christmas.model;

import static christmas.constant.ExceptionMessage.INVALID_ORDER;

import christmas.constant.Menu;

public class OrderedMenu {
    private static final int MINIMUM_ORDER_COUNT = 1;

    private final Menu menu;
    private final int amount;

    public OrderedMenu(Menu menu, int amount) {
        validateValidMenu(menu);
        validateCount(amount);
        this.menu = menu;
        this.amount = amount;
    }

    private void validateValidMenu(Menu menu) {
        if (menu.equals(Menu.NOTHING)) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    private void validateCount(int count) {
        if (count < MINIMUM_ORDER_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER.getMessage());
        }
    }

    public boolean isMenuDrink() {
        return menu.isMenuDrink();
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getAmount() {
        return amount;
    }
}
