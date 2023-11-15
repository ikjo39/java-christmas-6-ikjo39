package christmas.dto;

import christmas.model.OrderedMenu;

public record Giveaway(OrderedMenu orderedMenu, boolean isEnabled) {

    public String getMenuName() {
        return orderedMenu.getMenuName();
    }

    public int getAmount() {
        return orderedMenu.getAmount();
    }
}
