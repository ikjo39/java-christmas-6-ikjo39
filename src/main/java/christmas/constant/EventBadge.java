package christmas.constant;

public enum EventBadge {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int minimumAmount;

    EventBadge(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public boolean isMoreThanMinimumAmount(int amount) {
        return amount > minimumAmount;
    }

    public String getName() {
        return name;
    }

    public int getMinimumAmount() {
        return minimumAmount;
    }
}
