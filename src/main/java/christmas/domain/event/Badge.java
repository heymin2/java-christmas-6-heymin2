package christmas.domain.event;

public enum Badge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NO(0, "없음");

    private final int discount;
    private final String badge;

    Badge(int discount, String badge) {
        this.discount = discount;
        this.badge = badge;
    }

    public static String getBadge(int totalDiscount) {
        for (Badge badge : values()) {
            if (totalDiscount >= badge.discount) {
                return badge.badge;
            }
        }
        return NO.badge;
    }
}