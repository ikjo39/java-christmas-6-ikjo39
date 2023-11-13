package christmas.dto;

import static christmas.constant.OutputMessage.NONE;
import static christmas.constant.OutputMessage.ORDERED_MENU_FORMAT;

import christmas.model.OrderedMenu;
import java.util.Objects;

public record GiveAway(OrderedMenu orderedMenu) {

    public String convertOutputText() {
        if (Objects.isNull(orderedMenu)) {
            return String.format(NONE.getMessage());
        }
        return String.format(ORDERED_MENU_FORMAT.getMessage(), orderedMenu.getMenuName(), orderedMenu.getAmount());
    }
}
