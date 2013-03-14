package Game;

import Command.CommandInformation;
import Command.CommandOperation;
import convertor.StringConvert;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-24
 * Time: 下午8:59
 * To change this template use File | Settings | File Templates.
 */
public class APlayerRound {
    private boolean isGoing = true;
    private CommandOperation commandOperation;
    StringConvert stringConvert = new StringConvert();

    public APlayerRound(CommandOperation commandOperation) {
        this.commandOperation = commandOperation;
    }

    public void receiveCommand(UserInput userInput) {
        String message = commandOperation.getPlayer().getCharacterName() + " >   ";
        SetColor.printColorString(message, Color.GRAY);
        int leftDays = commandOperation.getPlayer().checktMascot();
        CommandInformation commandInformation = new CommandInformation();
        int i = 0;
        while (i == 0) {
            try {
                i = 1;
                String command = userInput.readUserInput();
                commandInformation = stringConvert.convertCommand(command);
            } catch (Exception e) {
                i = 0;
                userInput.printMessage(e.getMessage());
                SetColor.printColorString(commandOperation.getPlayer().getCharacterName() + " >   ", Color.GRAY);
            }
        }
        switch (commandInformation.getCommandType()) {
            case ROBOT:
                commandOperation.robot();
                break;
            case BOMB:
                commandOperation.bomb(commandInformation.getArg());
                break;
            case BLOCK:
                commandOperation.block(commandInformation.getArg());
                break;
            case SELL:
                commandOperation.sell(commandInformation.getArg());
                break;
            case SELLTOOL:
                commandOperation.sellTool(commandInformation.getArg());
                break;
            case QUERY:
                commandOperation.query();
                break;
            case HELP:
                commandOperation.help();
                break;
            case QUIT:
                commandOperation.quit();
                setGoing(false);
                break;
            case ROLL:
                commandOperation.roll();
                commandOperation.getPlayer().setMascotLeftDays(leftDays);
                setGoing(false);
                break;
        }

    }

    public boolean isGoing() {
        return isGoing;
    }

    public void setGoing(boolean going) {
        isGoing = going;
    }

    public CommandOperation getCommandOperation() {
        return commandOperation;
    }
}
