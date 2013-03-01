package Game;

import enigma.console.Console;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

import java.awt.*;

public class SetColor {

	public static void printColorString(String s,Color color){
        TextAttributes attrs = new TextAttributes(color);
        console.setTextAttributes(attrs);
        System.out.print(s);
    }
    public static void printColorStringln(String s,Color color){
        TextAttributes attrs = new TextAttributes(color);
        console.setTextAttributes(attrs);
        System.out.println(s);
    }
    public static void printline(String s){
        TextAttributes attrs = new TextAttributes(Color.GRAY);
        console.setTextAttributes(attrs);
        System.out.println(s);
    }
    public static String readInput(){
		return console.readLine();
	}
    private static final Console console;
    static
    {
        console = Enigma.getConsole("Rich");
    }


    public static void print(String s) {
        TextAttributes attrs = new TextAttributes(Color.GRAY);
        console.setTextAttributes(attrs);
        System.out.print(s);
    }
}