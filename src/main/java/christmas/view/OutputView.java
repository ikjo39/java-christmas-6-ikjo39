package christmas.view;

import static christmas.constant.OutputMessage.AFTER_DISCOUNTED_AMOUNT;
import static christmas.constant.OutputMessage.AMOUNT_FORMAT;
import static christmas.constant.OutputMessage.BENEFITS;
import static christmas.constant.OutputMessage.BENEFIT_AMOUNT_FORMAT;
import static christmas.constant.OutputMessage.EVENT_BADGE;
import static christmas.constant.OutputMessage.EVENT_PREVIEW_INTRODUCTION_FORMAT;
import static christmas.constant.OutputMessage.GIVEAWAY_MENU;
import static christmas.constant.OutputMessage.NONE;
import static christmas.constant.OutputMessage.ORDERED_MENU;
import static christmas.constant.OutputMessage.ORDERED_MENU_FORMAT;
import static christmas.constant.OutputMessage.PLANNER_INTRODUCTION;
import static christmas.constant.OutputMessage.TOTAL_BENEFITS_AMOUNT;
import static christmas.constant.OutputMessage.TOTAL_PRICE;

import christmas.constant.DiscountEvent;
import christmas.constant.OutputMessage;
import christmas.dto.BenefitInfos;
import christmas.dto.GiveAway;
import christmas.model.Benefits;
import christmas.model.EventTotalBenefit;
import christmas.model.OrderedMenu;
import christmas.model.OrderedMenus;
import christmas.model.TotalPrice;
import christmas.model.VisitDate;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("#,##0");

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

    public void printGiveAwayMenu(GiveAway giveAway) {
        printOutputMessage(GIVEAWAY_MENU);
        if (giveAway.isEnabled()) {
            System.out.printf(ORDERED_MENU_FORMAT.getMessage(), giveAway.getMenuName(), giveAway.getAmount());
            return;
        }
        System.out.printf(NONE.getMessage());
    }

    public void printBenefits(BenefitInfos benefitInfos) {
        printOutputMessage(BENEFITS);
        if (!benefitInfos.isEmpty()) {
            Map<DiscountEvent, Integer> events = benefitInfos.events();
            events.keySet()
                    .stream()
                    .map(discountEvent ->
                            String.format(discountEvent.getFormat(), NUMBER_FORMAT.format(events.get(discountEvent))))
                    .forEach(System.out::printf);
            return;
        }
        System.out.printf(NONE.getMessage());
    }

    public void printBenefitsTotal(Benefits benefits) {
        printOutputMessage(TOTAL_BENEFITS_AMOUNT);
        System.out.printf(BENEFIT_AMOUNT_FORMAT.getMessage(), NUMBER_FORMAT.format(benefits.getTotalBenefits()));
        System.out.println();
    }

    public void printAfterDiscounted(TotalPrice totalPrice, Benefits benefits) {
        printOutputMessage(AFTER_DISCOUNTED_AMOUNT);
        int result = totalPrice.calculateAfterDiscountedAmount(benefits);
        System.out.printf(AMOUNT_FORMAT.getMessage(), NUMBER_FORMAT.format(result));
        System.out.println();
    }

    public void printBadge(EventTotalBenefit totalBenefit) {
        printOutputMessage(EVENT_BADGE);
        System.out.println(totalBenefit.getEventBadge().getName());
    }

    private void printOutputMessage(OutputMessage outputMessage) {
        System.out.println(outputMessage.getMessage());
    }
}
