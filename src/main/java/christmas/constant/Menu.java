package christmas.constant;

import static christmas.constant.MenuCategory.APPETIZER;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.MenuCategory.DRINK;
import static christmas.constant.MenuCategory.MAIN_DISH;
import static christmas.constant.MenuCategory.NONE;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_CREAM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MAIN_DISH, "티본스테이크", 55_000),
    BBQ_RIB(MAIN_DISH, "바비큐립", 54_000),
    SEAFOOD_SPAGHETTI(MAIN_DISH, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN_DISH, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),

    ZERO_COKE(DRINK, "제로콜라", 3_000),
    RED_WINE(DRINK, "레드와인", 60_000),
    CHAMPAGNE(DRINK, "샴페인", 25_000),

    NOTHING(NONE, "", 0);

    private final MenuCategory menuCategory;
    private final String name;
    private final int price;

    Menu(MenuCategory menuCategory, String name, int price) {
        this.menuCategory = menuCategory;
        this.name = name;
        this.price = price;
    }

    public static Menu from(String name) {
        return Arrays.stream(Menu.values())
                .filter(menu -> name.equals(menu.name))
                .findFirst()
                .orElse(NOTHING);
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
