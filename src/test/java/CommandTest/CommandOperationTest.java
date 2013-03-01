package CommandTest;

import Command.CommandException;
import Command.CommandOperation;
import Game.RichGame;
import Game.RichPreparation;
import Game.UserInput;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import player.Player;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-25
 * Time: 下午11:57
 * To change this template use File | Settings | File Templates.
 */
public class CommandOperationTest {
    CommandOperation  commandOperation;
    Player player;
    RichGame richGame ;
    @Before
    public void setUp() throws Exception {
        UserInput userInput = Mockito.mock(UserInput.class) ;
        Mockito.when(userInput.readUserInput()).thenReturn("10000").thenReturn("2134");
        RichPreparation richPreparation=new RichPreparation(userInput);;
        richPreparation.prepareRichGame();
        richGame=richPreparation.getRichGame();
        player= richGame.getPlayers().get(2) ;
        richGame.getGameMap().getGroundList().get(13).setOwners("S");
        richGame.getGameMap().getGroundList().get(13).setGroundType(2);
        commandOperation=new CommandOperation(player,richGame);
    }

    @Test
    public void shouldSellLandProperty() {
        commandOperation.sell(13);
        int funds=commandOperation.getPlayer().getFunds() ;
        assertThat(funds,is(11200));
        assertThat(commandOperation.getRichGame().getGameMap().getGroundList().get(13).getDisplay(),is("0"));
        assertThat(commandOperation.getRichGame().getGameMap().getGroundList().get(13).getGroundType(),is(0));
        assertThat(commandOperation.getRichGame().getGameMap().getGroundList().get(13).getPrice(),is(200));
        assertThat(commandOperation.getRichGame().getGameMap().getGroundList().get(13).getOwners(),is("0"));
    }

    @Test(expected = CommandException.class)
    public void shouldGetExceptionInformationWhenSellSystemLand()
    {
        commandOperation.sell(0);
    }

    @Test(expected = CommandException.class)
    public void shouldGetExceptionInformationWhenTheLandOwnerIsNotThePlayer()
    {
        commandOperation.sell(12);
    }

    @Test
    public void shouldGetPlacePropLocationRight()
    {
        player.setLocaion(5);
        assertThat( commandOperation.calculatePropLocation(-9),is(66));
        assertThat( commandOperation.calculatePropLocation(9),is(14));
        assertThat( commandOperation.calculatePropLocation(-1),is(4));

    }

    @Test
    public void shouldGetPlacePropLocationRight2()
    {
        player.setLocaion(64);
        assertThat( commandOperation.calculatePropLocation(3),is(67));
        assertThat( commandOperation.calculatePropLocation(9),is(3));
        assertThat(commandOperation.calculatePropLocation(-1), is(63));

    }
    @Test
      public void shouldPlacedBlockSuccess()
    {
        player.setProps(new int[]{1,0,0});
        player.setLocaion(64);
        commandOperation=new CommandOperation(player,richGame);
        commandOperation.block(9);
        assertTrue(commandOperation.getRichGame().getProps().containsKey(3));
        assertThat(player.getProps()[0],is(0));
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBlockErrorWhenHaveNoBlock()
    {
        commandOperation.block(5);
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBlockErrorWhenHavePlayer()
    {
        richGame.getPlayers().get(1).setLocaion(3);
        player.setProps(new int[]{1,0,0});
        player.setLocaion(64);
        commandOperation=new CommandOperation(player,richGame);
        commandOperation.block(9);
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBlockErrorWhenHaveProp()
    {
        richGame.getProps().put(3,"BLOCK");
        commandOperation.block(3);
    }

    @Test
    public void shouldPlacedBombSuccess()
    {
        player.setProps(new int[]{1,0,1});
        commandOperation=new CommandOperation(player,richGame);
        commandOperation.bomb(-10);
        assertTrue(commandOperation.getRichGame().getProps().containsKey(60));
        assertThat(player.getProps()[2],is(0));
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBombErrorWhenHaveNoBomb()
    {
        commandOperation.bomb(2);
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBombErrorWhenHavePlayer()
    {
        richGame.getPlayers().get(1).setLocaion(5);
        commandOperation.bomb(5);
    }

    @Test(expected = CommandException.class)
    public void shouldPlacedBombErrorWhenHaveProp()
    {
        richGame.getProps().put(4,"BLOCK");
        commandOperation.bomb(4);
    }

    @Test
    public void shouldRobotRight()
    {
        player.setProps(new int[]{1,1,1});
        HashMap props=new HashMap();
        props.put(2,"BOMB") ;
        props.put(10,"BOMB") ;
        richGame.setProps(props);
        commandOperation=new CommandOperation(player,richGame);
        commandOperation.robot();
        assertFalse(commandOperation.getRichGame().getProps().containsKey(2));
        assertFalse(commandOperation.getRichGame().getProps().containsKey(10));
        assertThat(player.getProps()[1],is(0));
    }

    @Test(expected = CommandException.class)
    public void shouldGetExceptionWhenHaveNoRobot()
    {
        player.setProps(new int[]{1,0,1});
        commandOperation.robot();
    }

    @Test
    public void shouldGetRightDataAfterSellBlock()
    {
        player.setProps(new int[]{1,0,1});
        commandOperation.sellTool(1);
        assertThat(player.getProps()[0],is(0));
        assertThat(player.getPoints(),is(50));
    }

    @Test(expected = CommandException.class)
    public void shouldGetExceptionWhenHaveNoRobotToSell()
    {
        player.setProps(new int[]{1,0,1});
        commandOperation.sellTool(2);
    }

    @Test  (expected = CommandException.class)
    public void shouldGetRightDataWhenHaveNoRobotToSell()
    {
        player.setProps(new int[]{1,0,1});
        commandOperation.sellTool(2);
    }


}
