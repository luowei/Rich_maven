package Game;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-23
 * Time: 下午7:34
 * To change this template use File | Settings | File Templates.
 */
public class UserInput {
    //private Scanner scanner=new Scanner(System.in);
    private SetColor setColor=new SetColor();
    public String readUserInput() {
        return   SetColor.readInput();
    }

    public void printMessage(String message) {
        SetColor.printColorStringln(message, Color.PINK);
       // System.out.println(message);
    }
}
