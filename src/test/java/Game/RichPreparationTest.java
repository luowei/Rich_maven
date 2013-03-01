package Game;

import Game.RichPreparation;
import Game.UserInput;
import convertor.StringConvert;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.mockito.Mockito;
import player.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-23
 * Time: 下午8:23
 * To change this template use File | Settings | File Templates.
 */

public class RichPreparationTest {

    @Test
    public void shouldMapPrepared(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        RichPreparation richPreparation=new RichPreparation(userInput);
    }


    @Test
    public void shouldFundsPreparedRightUntilInputTheValidNumberAfterOneInvalidInput(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("s105000").thenReturn("10000").thenReturn("1234");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareInteractiveData();
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.FUNDS_SHOULD_BE_A_NUMBER);
        assertThat(richPreparation.getRichGame().getFunds(), is(10000)) ;
    }

    @Test
    public void shouldFundsPreparedRightUntilInputTheValidNumberAfterThreeInvalidInput(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("105000").thenReturn("10df000").thenReturn("900").thenReturn("1000").thenReturn("1234");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareInteractiveData();
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.Funds_SHOULD_NOT_EXCEED_THE_MAXIMUM);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.FUNDS_SHOULD_BE_A_NUMBER);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.FUNDS_SHOULD_NOT_UNDER_THE_MINIMUM);
        assertThat(richPreparation.getRichGame().getFunds(), is(1000)) ;
    }
    @Test
    public void shouldPlayersPreparedRightWhenInputValidTypes(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("1000").thenReturn("2134");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareInteractiveData();
        assertThat(richPreparation.getRichGame().getPlayerCount(), is(4)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(0), isPlayerAs("A",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(1), isPlayerAs("Q",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(2), isPlayerAs("S",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(3), isPlayerAs("J",1000)) ;
    }

    @Test
    public void shouldPlayersPreparedRightUntilInputValidTypes(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("1000").thenReturn("5134").thenReturn("2143");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareInteractiveData();
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.PLAY_TYPE_SHOULD_BE_1_OR_2_OR_3_OR_4);
        assertThat(richPreparation.getRichGame().getPlayerCount(), is(4)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(0), isPlayerAs("A",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(1), isPlayerAs("Q",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(2), isPlayerAs("J",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(3), isPlayerAs("S",1000)) ;
    }

    @Test
    public void shouldPlayersAndFundsPreparedRightAfterBothHaveSeveralErrorsThenInputValidTypes(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("100").thenReturn("10000a").thenReturn("1000").thenReturn("5134").thenReturn("2").thenReturn("12345").thenReturn("1123").thenReturn("132");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareInteractiveData();
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.FUNDS_SHOULD_NOT_UNDER_THE_MINIMUM);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.FUNDS_SHOULD_BE_A_NUMBER);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.PLAY_TYPE_SHOULD_BE_1_OR_2_OR_3_OR_4);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.PLAYERS_NUMBER_SHOULD_BE_OVER_2);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.PLAYERS_NUMBER_SHOULD_BE_UNDER_5);
        Mockito.verify(userInput, Mockito.times(1)).printMessage(StringConvert.PLAYERS_NUMBER_SHOULD_NOT_BE_REPEAT);
        assertThat(richPreparation.getRichGame().getPlayerCount(), is(3)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(0), isPlayerAs("Q",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(1), isPlayerAs("S",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(2), isPlayerAs("A",1000)) ;
    }

    private Matcher<? super Player> isPlayerAs(final String display, final int funds) {
        return new TypeSafeMatcher<Player>() {
            @Override
            protected boolean matchesSafely(Player player) {
                return player.getDisplayName().equals(display) &&  player.getFunds()== funds;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    @Test
    public void shouldMapAndPropsPrepared(){
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("1000").thenReturn("132");
        RichPreparation richPreparation=new RichPreparation(userInput);
        richPreparation.prepareRichGame();
        assertThat(richPreparation.getRichGame().getPlayerCount(), is(3)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(0), isPlayerAs("Q",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(1), isPlayerAs("S",1000)) ;
        assertThat(richPreparation.getRichGame().getPlayers().get(2), isPlayerAs("A",1000)) ;
        assertThat(richPreparation.getRichGame().getGameMap().getGroundList().size(),is(70));
        assertTrue(richPreparation.getRichGame().getProps().isEmpty());
    }


}
