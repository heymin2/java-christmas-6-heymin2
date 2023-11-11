package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderMenu {
    private final Map<String, Integer> menu;

    public OrderMenu(String menuInput) {
        this.menu = validate(menuInput);
    }

    private Map<String, Integer> validate(String menuInput){
        String[] splitMenus = splitComma(menuInput);
        Map<String, Integer> menu = createMenu(splitMenus);
        validateMenu(menu);
        validateOnlyDrink(menu);
        return menu;
    }

    private String[] splitComma(String menuInput){
        return menuInput.split(",");
    }

    private Map<String, Integer> createMenu(String[] splitMenus){
        Map<String, Integer> menu = new HashMap<>();
        int totalQuantity = 0;
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
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateContents(String[] splitEntry) {
        if (splitEntry.length != 2 || splitEntry[0].isEmpty() || splitEntry[1].isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNumber(String number){
        if (!number.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateUnderOne(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateDuplicateMenu(Map<String, Integer> menu, String menuName){
        if(menu.containsKey(menuName)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateTotalQuantity(int totalQuantity){
        if(totalQuantity > 20){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateNotMenu(String menuName){
        if(!Menu.contains(menuName)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateMenu(Map<String, Integer> menu){
       for (String key : menu.keySet()) {
            validateNotMenu(key);
        }
    }

    private void validateOnlyDrink(Map<String, Integer> menu){
        boolean isOnlyLastCategory = menu.keySet().stream()
                .map(Menu::fromMenuName)
                .allMatch(m -> m.getCategory() == 4);
        if(isOnlyLastCategory){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int calculateTotalPrice() {
        int total = 0;
        for (Map.Entry<String, Integer> entry : menu.entrySet()) {
            String menuName = entry.getKey();
            int quantity = entry.getValue();
            Menu menu = Menu.fromMenuName(menuName);
            total += menu.getPrice() * quantity;
        }
        return total;
    }


    @Override
    public String toString() {
        return menu.entrySet().stream()
                .map(entry -> entry.getKey() + " " + entry.getValue() + "개")
                .collect(Collectors.joining("\n"));
    }
}
