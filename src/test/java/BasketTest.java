import TestingLab.Basket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BasketTest {
    private Basket basket;

    @BeforeEach
    void setUp(){
        basket=new Basket();
    }

    @Nested
    @DisplayName("when it's empty")
    class WhenEmpty{

        @Test
        void shouldBeEmpty(){
            assertThat(basket.isEmpty()).isTrue();
        }

        @Test
        void shouldHaveSizeZero(){
            assertThat(basket.size()).isZero();
        }
    }


    @Nested
    @DisplayName("When we add items")
    class WhenAddingItems{

        @Test
        void shouldContainAddedItems(){
            basket.add("Pomme");
            assertThat(basket.items()).containsExactly("Pomme");

        }

        @Test
        void shouldRejectBlankItems(){
            assertThatThrownBy(()->basket.add(" "))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
