package christmas.view;

import static christmas.constant.OutputMessage.AFTER_DISCOUNTED_AMOUNT;
import static christmas.constant.OutputMessage.AMOUNT_FORMAT;
import static christmas.constant.OutputMessage.BENEFITS;
import static christmas.constant.OutputMessage.EVENT_BADGE;
import static christmas.constant.OutputMessage.EVENT_PREVIEW_INTRODUCTION_FORMAT;
import static christmas.constant.OutputMessage.GIVEAWAY_MENU;
import static christmas.constant.OutputMessage.NONE;
import static christmas.constant.OutputMessage.ORDERED_MENU;
import static christmas.constant.OutputMessage.ORDERED_MENU_FORMAT;
import static christmas.constant.OutputMessage.PLANNER_INTRODUCTION;
import static christmas.constant.OutputMessage.TOTAL_BENEFITS_AMOUNT;
import static christmas.constant.OutputMessage.TOTAL_PRICE;

import christmas.constant.OutputMessage;
import christmas.dto.EventBenefit;
import christmas.dto.EventBenefits;
import christmas.dto.Giveaway;
import christmas.model.DiscountedPriceBadgeManager;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.VisitDate;
import java.text.DecimalFormat;
import java.util.List;

public class OutputView {
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0;-#,##0");

    public void printExceptionMessage(String message) {
        System.out.println(message);
    }

    public void printPlannerIntroduction() {
        printOutputMessage(PLANNER_INTRODUCTION);
    }

    public void printEventPreviewIntroduction(VisitDate visitDate) {
        System.out.printf(EVENT_PREVIEW_INTRODUCTION_FORMAT.getMessage(), visitDate.getMonth(),
                visitDate.getDayOfMonth());
        System.out.println();
    }

    public void printOrderedMenus(OrderedMenus orderedMenus) {
        printOutputMessage(ORDERED_MENU);
        List<OrderedMenu> menus = orderedMenus.getOrderedMenus();
        menus.forEach(this::printOrderedMenu);
        System.out.println();
    }

    private void printOrderedMenu(OrderedMenu orderedMenu) {
        System.out.printf(ORDERED_MENU_FORMAT.getMessage(), orderedMenu.getMenuName(), orderedMenu.getAmount());
    }

    public void printTotalPriceBeforeDiscount(OrderedMenus orderedMenus) {
        printOutputMessage(TOTAL_PRICE);
        System.out.printf(AMOUNT_FORMAT.getMessage(), NUMBER_FORMAT.format(orderedMenus.calculateTotalPrice()));
        System.out.println();
    }

    public void printGiveAwayMenu(Giveaway giveAway) {
        printOutputMessage(GIVEAWAY_MENU);
        if (giveAway.isEnabled()) {
            System.out.printf(ORDERED_MENU_FORMAT.getMessage(), giveAway.getMenuName(), giveAway.getAmount());
            System.out.println();
            return;
        }
        printNone();
    }

    public void printBenefits(EventBenefits eventBenefits) {
        printOutputMessage(BENEFITS);
        List<EventBenefit> enabledBenefits = eventBenefits.getEnabledBenefits();
        if (enabledBenefits.isEmpty()) {
            printNone();
            return;
        }
        enabledBenefits.forEach(this::printEventBenefit);
        System.out.println();
    }

    private void printEventBenefit(EventBenefit eventBenefit) {
        String format = eventBenefit.eventNameFormat().getFormat();
        System.out.printf(format, NUMBER_FORMAT.format(eventBenefit.discountAmount()));
    }

    public void printBenefitsTotal(EventBenefits benefits) {
        printOutputMessage(TOTAL_BENEFITS_AMOUNT);
        System.out.printf(AMOUNT_FORMAT.getMessage(), NUMBER_FORMAT.format(-benefits.getTotalBenefits()));
        System.out.println();
    }

    public void printAfterDiscounted(DiscountedPriceBadgeManager discountedPriceBadgeManager) {
        printOutputMessage(AFTER_DISCOUNTED_AMOUNT);
        String formattedNumber = NUMBER_FORMAT.format(discountedPriceBadgeManager.getDiscountedTotalPrice());
        System.out.printf(AMOUNT_FORMAT.getMessage(), formattedNumber);
        System.out.println();
    }

    public void printBadge(DiscountedPriceBadgeManager discountedPriceBadgeManager) {
        printOutputMessage(EVENT_BADGE);
        System.out.println(discountedPriceBadgeManager.getEventBadge().getName());
    }

    private void printNone() {
        System.out.printf(NONE.getMessage());
        System.out.println();
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
