package christmas.event;

import christmas.domain.event.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadgeTest {
    @DisplayName("총혜택 금액 20,000이상, 산타 배지")
    @Test
    void getBadge_Santa() {
        String badge = Badge.getBadge(20000);
        assertEquals("산타", badge);
    }

    @DisplayName("총혜택 금액 10,000이상, 트리 배지")
    @Test
    void getBadge_Tree() {
        String badge = Badge.getBadge(10000);
        assertEquals("트리", badge);
    }

    @DisplayName("총혜택 금액 5,000이상, 별 배지")
    @Test
    void getBadge_Star() {
        String badge = Badge.getBadge(5000);
        assertEquals("별", badge);
    }


    @DisplayName("총혜택 금액 5,000미만, 배지 없음")
    @Test
    void getBadge_No() {
        String badge = Badge.getBadge(3000);
        assertEquals("없음", badge);
    }
}
