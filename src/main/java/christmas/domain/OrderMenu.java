package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenu {
    private static final String INVALID_ORDER_ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    private static final int MAX_MENU = 20;
    private static final int ZERO = 0;
    private static final int DRINK_CATEGORY = 4;
    private static final int ARRAY_LENGTH = 2;



    private final Map<String, Integer> menu;

    public OrderMenu(String menuInput) {
        this.menu = validate(menuInput);
    }

    private Map<String, Integer> validate(String menuInput){
        String[] splitMenus = splitComma(menuInput);
        Map<String, Integer> menu = createMenu(splitMenus);
        validateAllMenusExist(menu);
        validateOnlyDrink(menu);
        return menu;
    }

    private String[] splitComma(String menuInput){
        return menuInput.split(",");
    }

    private Map<String, Integer> createMenu(String[] splitMenus){
        Map<String, Integer> menu = new HashMap<>();
        int totalQuantity = ZERO;
        for (String menuEntry : splitMenus) {
            String[] split = splitDash(menuEntry);
            validateNumber(split[1]);
            int quantity = Integer.parseInt(split[1]);
            validateUnderOne(quantity);
            totalQuantity += quantity;
            validateDuplicateMenu(menu, split[0]);
            menu.put(split[0], quantity);
        }
        validateTotalQuantity(totalQuantity);
        return menu;
    }

    private String[] splitDash(String menuEntry){
        validateContainsDash(menuEntry);
        String[] splitEntry = menuEntry.split("-");
        validateContents(splitEntry);
        return splitEntry;
    }

    private void validateContainsDash(String menuEntry) {
        if (!menuEntry.contains("-")) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateContents(String[] splitEntry) {
        if (splitEntry.length != ARRAY_LENGTH || splitEntry[0].isEmpty() || splitEntry[1].isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateNumber(String number){
        if (!number.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateUnderOne(int quantity){
        if(quantity <= ZERO){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateDuplicateMenu(Map<String, Integer> menu, String menuName){
        if(menu.containsKey(menuName)){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateTotalQuantity(int totalQuantity){
        if(totalQuantity > MAX_MENU){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateMenuExists(String menuName){
        if(!Menu.contains(menuName)){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateAllMenusExist(Map<String, Integer> menu){
       for (String key : menu.keySet()) {
           validateMenuExists(key);
        }
    }

    private void validateOnlyDrink(Map<String, Integer> menu){
        boolean isOnlyLastCategory = menu.keySet().stream()
                .map(Menu::fromMenuName)
                .allMatch(m -> m.getCategory() == DRINK_CATEGORY);
        if(isOnlyLastCategory){
            throw new IllegalArgumentException(INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    public int calculateTotalPrice() {
        int total = ZERO;
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.fromMenuName(menuName);
            total += menu.getPrice() * quantity;
        }
        return total;
    }

    public Map<String, Integer> getMenu(){
        return this.menu;
    }

    @Override
    public String toString() {
        return menu.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
