package Command;
/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-24
 * Time: 下午11:35
 * To change this template use File | Settings | File Templates.
 */
public class CommandException extends IllegalArgumentException {
    public CommandException(String message) {
       super(message);
    }
}
