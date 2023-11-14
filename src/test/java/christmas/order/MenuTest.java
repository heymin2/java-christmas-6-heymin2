package christmas.order;

import christmas.domain.order.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuTest {
    @DisplayName("메뉴판에 포함된 메뉴면 true")
    @Test
    void contains_ValidMenu() {
        assertTrue(Menu.contains("양송이수프"));
    }

    @DisplayName("메뉴판에 포함되지 않은 메뉴면 false")
    @Test
    void contains_InvalidMenu() {
        assertFalse(Menu.contains("소고기수프"));
    }

    @DisplayName("메뉴판에 포함된 메뉴면 menu 반환")
    @Test
    void fromMenuName_ValidMenu() {
        Menu menu = Menu.fromMenuName("양송이수프");
        assertNotNull(menu);
        assertEquals(6000, menu.getPrice());
        assertEquals(1, menu.getCategory());
    }

    @DisplayName("메뉴판에 포함되지 않은 메뉴면 null 반환")
    @Test
    void fromMenuName_InvalidMenu() {
        Menu menu = Menu.fromMenuName("소고기수프");
        assertNull(menu);
    }

    @DisplayName("메뉴의 가격 반환")
    @Test
    void getPrice() {
        Menu menu = Menu.fromMenuName("양송이수프");
        assertEquals(6000, menu.getPrice());
    }

    @DisplayName("메뉴의 카테고리 반환")
    @Test
    void getCategory() {
        Menu menu = Menu.fromMenuName("양송이수프");
        assertEquals(1, menu.getCategory());
    }
}
