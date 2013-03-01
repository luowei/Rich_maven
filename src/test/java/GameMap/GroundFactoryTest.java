package GameMap;

import RichMap.Ground;
import RichMap.GroundFactory;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class GroundFactoryTest {

    @Test
    public void shouldBuildPrisonGround() {
        Ground ground = GroundFactory.buildPrisonGround();
        assertThat(ground, isGroundAs("P", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildEmptyGroundWithPrice() {
        Ground ground = GroundFactory.buildEmptyGroundWithPrice(100);
        assertThat(ground, isGroundAs("0", 100, 0));
        assertFalse(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildStartGround() {
        Ground ground = GroundFactory.buildStartGround();
        assertThat(ground, isGroundAs("S", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildHospitalGround() {
        Ground ground = GroundFactory.buildHospitalGround();
        assertThat(ground, isGroundAs("H", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildToyGround() {
        Ground ground = GroundFactory.buildToyGround();
        assertThat(ground, isGroundAs("T", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildGiftGround() {
        Ground ground = GroundFactory.buildGiftGround();
        assertThat(ground, isGroundAs("G", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildMagicGround() {
        Ground ground = GroundFactory.buildMagicGround();
        assertThat(ground, isGroundAs("M", 0, 0));
        assertTrue(ground.getIsSystemLand());
    }

    @Test
    public void shouldBuildMoneyGround() {
        Ground ground = GroundFactory.buildMoneyGround(120);
        assertThat(ground, isGroundAs("$", 0, 120));
        assertTrue(ground.getIsSystemLand());
    }

    private Matcher<? super Ground> isGroundAs(final String s, final int price, final int point) {
        return new TypeSafeMatcher<Ground>() {
            @Override
            public boolean matchesSafely(Ground ground) {
                return ground.getDisplay().equals(s) && (ground.getPrice() == price) && (ground.getPoint() == point);
            }

            @Override
            public void describeTo(Description description) {
                //To change body of implemented methods use File | Settings | File Templates.
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
