package christmas.model;

import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.MAIN_DISH;

public class DiscountCalculator {
    private static final int CHRISTMAS_DISCOUNT_AMOUNT = 100;
    private static final int BASIC_DISCOUNT_AMOUNT = 1_000;
    private static final int WEEK_DISCOUNT_AMOUNT = 2_023;
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1_000;
    private static final int NO_DISCOUNT = 0;

    private final OrderedMenus orderedMenus;
    private final VisitDate visitDate;

    public DiscountCalculator(OrderedMenus orderedMenus, VisitDate visitDate) {
        this.orderedMenus = orderedMenus;
        this.visitDate = visitDate;
    }

    public int getChristmasDiscount() {
        if (visitDate.isChristmasDiscountEnabled()) {
            int additionalBenefits = CHRISTMAS_DISCOUNT_AMOUNT * (visitDate.getDayOfMonth() - 1);
            return BASIC_DISCOUNT_AMOUNT + additionalBenefits;
        }
        return NO_DISCOUNT;
    }

    public int getWeekdayDiscount() {
        if (!visitDate.isWeekend()) {
            return orderedMenus.calculateCategoryCount(DESSERT) * WEEK_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }

    public int getWeekendDiscount() {
        if (visitDate.isWeekend()) {
            return orderedMenus.calculateCategoryCount(MAIN_DISH) * WEEK_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }

    public int getSpecialDiscount() {
        if (visitDate.isSpecialDay()) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }
        return NO_DISCOUNT;
    }
}
