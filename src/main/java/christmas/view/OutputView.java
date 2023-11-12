package christmas.view;

import static christmas.constant.OutputMessage.EVENT_PREVIEW_INTRODUCTION_FORMAT;
import static christmas.constant.OutputMessage.ORDERED_MENU;
import static christmas.constant.OutputMessage.ORDERED_MENU_FORMAT;
import static christmas.constant.OutputMessage.PLANNER_INTRODUCTION;

import christmas.constant.OutputMessage;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.VisitDate;
import java.util.List;

public class OutputView {
    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printPlannerIntroduction() {
        printOutputMessage(PLANNER_INTRODUCTION);
    }

    public void printEventPreviewIntroduction(VisitDate visitDate) {
        System.out.printf(EVENT_PREVIEW_INTRODUCTION_FORMAT.getMessage(), visitDate.getMonth(), visitDate.getDayOfMonth());
        System.out.println();
    }

    public void printOrderedMenus(OrderedMenus orderedMenus) {
        printOutputMessage(ORDERED_MENU);
        List<OrderedMenu> menus = orderedMenus.getOrderedMenus();
        menus.forEach(this::printOrderedMenu);
    }

    private void printOrderedMenu(OrderedMenu orderedMenu) {
        System.out.printf(ORDERED_MENU_FORMAT.getMessage(), orderedMenu.getMenuName(), orderedMenu.getAmount());
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
