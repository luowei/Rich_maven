package GameMap;

import RichMap.GameMap;
import RichMap.Ground;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GameMapTest {

    private GameMap gameMap;

    @Before
    public void setUp() throws Exception {

        gameMap = new GameMap();
    }

    @Test
    public void shouldInitialRightMap()
    {
        List<Ground> grounds = gameMap.initalizeMap();

        assertThat(grounds.size(), is(70));
        assertThat(grounds.get(0), isGroundAs("S", 0, 0));
        assertThat(grounds.get(1), isGroundAs("0", 200, 0));
        assertThat(grounds.get(13), isGroundAs("0", 200, 0));
        assertThat(grounds.get(14), isGroundAs("H", 0, 0));
        assertThat(grounds.get(15), isGroundAs("0", 200, 0));
        assertThat(grounds.get(27), isGroundAs("0", 200, 0));
        assertThat(grounds.get(28), isGroundAs("T", 0, 0));
        assertThat(grounds.get(29), isGroundAs("0", 500, 0));
        assertThat(grounds.get(34), isGroundAs("0", 500, 0));
        assertThat(grounds.get(35), isGroundAs("G", 0, 0));
        assertThat(grounds.get(36), isGroundAs("0", 300, 0));
        assertThat(grounds.get(48), isGroundAs("0", 300, 0));
        assertThat(grounds.get(49), isGroundAs("P", 0, 0));
        assertThat(grounds.get(50), isGroundAs("0", 300, 0));
        assertThat(grounds.get(62), isGroundAs("0", 300, 0));
        assertThat(grounds.get(63), isGroundAs("M", 0, 0));
        assertThat(grounds.get(64), isGroundAs("$", 0, 20));
        assertThat(grounds.get(69), isGroundAs("$", 0, 60));
    }

    private Matcher<? super Ground> isGroundAs(final String s, final int price, final int point) {
        return new TypeSafeMatcher<Ground>() {
            @Override
            protected boolean matchesSafely(Ground ground) {
                return ground.getDisplay().equals(s) && (ground.getPrice() == price) && (ground.getPoint() == point);
            }

            @Override
            public void describeTo(Description description) {
            }

            @Override
            protected void describeMismatchSafely(Ground ground, Description mismatchDescription) {
                String errorMessage = "The ground shouldGetInitStatus be with displayName: " + s + ", price: " + price + ", and point: " + point;
                errorMessage += "but in fact is (" + ground.getDisplay() + " " + ground.getPrice() + " " + ground.getPoint() +")";
                mismatchDescription.appendText(errorMessage);
            }
        };
    }
}
