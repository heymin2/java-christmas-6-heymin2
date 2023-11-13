package christmas.domain;

public enum Menu {
    MUSHROOM_SOUP("양송이수프", 6_000, 1),
    TAPAS("타파스", 5_500, 1),
    CAESAR_SALAD("시저샐러드", 8_000, 1),
    T_BONE_STEAK("티본스테이크", 55_000, 2),
    BBQ_RIBS("바비큐립", 54_000, 2),
    SEAFOOD_PASTA("해산물파스타", 35_000, 2),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000, 2),
    CHOCOLATE_CAKE("초코케이크", 15_000, 3),
    ICE_CREAM("아이스크림", 5_000, 3),
    ZERO_COLA("제로콜라", 3_000, 4),
    RED_WINE("레드와인", 60_000, 4),
    CHAMPAGNE("샴페인", 25_000, 4);

    private final String menu;
    private final int price;
    private final int category;

    Menu(String menu, int price, int category) {
        this.menu = menu;
        this.price = price;
        this.category = category;
    }

    public int getCategory(){
        return category;
    }

    public int getPrice() {
        return price;
    }

    public static boolean contains(String menuName){
        for (Menu menu : Menu.values()) {
            if (menu.menu.equals(menuName)) {
                return true;
            }
        }
        return false;
    }

    public static Menu fromMenuName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.menu.equals(menuName)) {
                return menu;
            }
        }
        return null;
    }
}
