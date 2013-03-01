package Game;

import Game.RichGame;
import org.junit.Before;
import org.junit.Test;
import player.Player;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-21
 * Time: 下午10:14
 * To change this template use File | Settings | File Templates.
 */
public class RichGameTest {

    private RichGame richGame;

    @Before
    public void setUp()
    {
         richGame = new RichGame();
    }

     @Test
    public void shouldInitialFundsRight() throws Exception {
        richGame.setFunds(14000);
        assertThat(richGame.getFunds(), is(14000));
    }

    @Test
    public void shouldGetDefaultFundsWhenNotSetAmount() throws Exception {
        assertThat(richGame.getFunds(), is(10000));
    }

    @Test
    public void shouldInitialPlayersRight(){
         List<Player> playerList = new ArrayList<Player>();
         richGame.initialPlayers(playerList);
         int playerCount = richGame.getPlayerCount();
         assertThat(playerCount,is(0));
    }


}
