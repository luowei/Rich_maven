package CommandTest;

import Command.CommandInformation;
import Command.CommandType;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-24
 * Time: 下午9:39
 * To change this template use File | Settings | File Templates.
 */
public class CommandInformationTest {
    @Test
    public void getRightCommandInformation(){
        CommandInformation commandInformation=new CommandInformation(CommandType.HELP);
        assertThat(commandInformation.getCommandType(), Is.is(CommandType.HELP));
    }

    @Test
    public void getRightCommandInformationWhenHaveArg(){
        CommandInformation commandInformation=new CommandInformation(CommandType.BLOCK,7);
        assertThat(commandInformation.getCommandType(), Is.is(CommandType.BLOCK));
        assertThat(commandInformation.getArg(),is(7));
    }
}
