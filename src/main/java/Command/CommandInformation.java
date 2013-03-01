package Command;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-24
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public class CommandInformation {
    private CommandType commandType;
    private int arg;
    public CommandInformation(){
    }
    public CommandInformation(CommandType commandType){
        setCommandType(commandType) ;
    }

    public CommandInformation(CommandType commandType, int arg) {
        setCommandType(commandType) ;
        setArg(arg) ;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    private void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public int getArg() {
        return arg;
    }

    public void setArg(int arg) {
        this.arg = arg;
    }
}
