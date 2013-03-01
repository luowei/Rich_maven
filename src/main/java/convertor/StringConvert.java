package convertor;

import Command.CommandException;
import Command.CommandInformation;
import Command.CommandType;
import player.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午3:23
 * To change this template use File | Settings | File Templates.
 */
public class StringConvert {

    public static final String FUNDS_SHOULD_BE_A_NUMBER = "Should be a number";
    public static final String Funds_SHOULD_NOT_EXCEED_THE_MAXIMUM = "Should not exceed the maximum";
    public static final String FUNDS_SHOULD_NOT_UNDER_THE_MINIMUM = "Funds Should not under the minimum";
    public static final String PLAYERS_NUMBER_SHOULD_BE_OVER_2 = "players number should be over 2";
    public static final String PLAYERS_NUMBER_SHOULD_BE_UNDER_5 = "players number should be under 5";
    public static final String PLAYERS_NUMBER_SHOULD_NOT_BE_REPEAT = "players number should not be repeat";
    public static final String PLAY_TYPE_SHOULD_BE_1_OR_2_OR_3_OR_4 = "play type should be 1 or 2 or 3 or 4";

    private int initalFunds;
    private String blockRegx = "block -?([1-9]{1}|10)";
    private String bombRegx = "bomb -?([1-9]{1}|10)";
    private String sellRegx ="sell ([0-9]{1}|[1-6]{1}[0-9]{1})";
    private String sellToolRegx ="selltool (1|2|3)";
    public Integer convertFunds(String s) {
         if(s.equals("")){
             initalFunds=10000;
         } else{
             try{
                 initalFunds = Integer.parseInt(s);
             } catch (Exception e) {
                 throw new InvalidParameterException(FUNDS_SHOULD_BE_A_NUMBER);
             }
             if(initalFunds>50000){
                 throw  new InvalidParameterException(Funds_SHOULD_NOT_EXCEED_THE_MAXIMUM);
             }  else if(initalFunds<1000)  {
                 throw  new InvalidParameterException(FUNDS_SHOULD_NOT_UNDER_THE_MINIMUM);
             }
         }
        return initalFunds;
    }

    public List<Player> convertPlayers(String playersInformation) {
        List<Player>   players=new ArrayList<Player>() ;
        if(playersInformation.length()<2) {
            throw new InvalidParameterException(PLAYERS_NUMBER_SHOULD_BE_OVER_2);
        } else if(playersInformation.length()>4){
            throw new InvalidParameterException(PLAYERS_NUMBER_SHOULD_BE_UNDER_5);
        }
        if(hasRepeatPlayersType(playersInformation)) {
            throw new InvalidParameterException(PLAYERS_NUMBER_SHOULD_NOT_BE_REPEAT);
        }
        for(int i=0;i<playersInformation.length();i++) {
            char playerType=playersInformation.charAt(i);
            switch(playerType) {
            case'1': players.add(new QianfurenPlayer(initalFunds));  break;
            case'2': players.add(new ATuBoPlayer(initalFunds));  break;
            case'3': players.add(new SunMeiMeiPlayer(initalFunds));  break;
            case'4': players.add(new JinBaoBeiPlayer(initalFunds));  break;
            default:throw  new InvalidParameterException(PLAY_TYPE_SHOULD_BE_1_OR_2_OR_3_OR_4);
            }
        }
        return players;
    }

    private boolean hasRepeatPlayersType(String playersInformation) {
            char[]  playTypes= playersInformation.toCharArray();
            HashMap  playTypeInformation=new HashMap();
            for(int i=0;i<playersInformation.length();i++){
                 if(playTypeInformation.containsKey(playTypes[i])){
                    return true;
                 } else {
                     playTypeInformation.put(playTypes[i],1);
                 }
            }
            return false;
    }


    public CommandInformation convertCommand(String command) {
        command=command.toLowerCase() ;
        if(isQuery(command)) {
            return new CommandInformation(CommandType.QUERY);
        }else if(isRoll(command)){
            return new CommandInformation(CommandType.ROLL);
        }else if(isRobot(command)) {
            return new CommandInformation(CommandType.ROBOT);
        } else if (isHelp(command)) {
            return new CommandInformation(CommandType.HELP);
        } else if (isQuit(command)) {
            return new CommandInformation(CommandType.QUIT);
        } else if(isBlock(command)) {
            return new CommandInformation(CommandType.BLOCK,Distance(command, "block "));
        } else if(isBomb(command)) {
            return new CommandInformation(CommandType.BOMB,Distance(command, "bomb "));
        } else if(isSell(command)) {
            return new CommandInformation(CommandType.SELL,Distance(command,"sell "));
        }  else if(isSellTool(command)) {
            return new CommandInformation(CommandType.SELLTOOL,Distance(command,"selltool "));
        }  else{
           throw new CommandException("Command error");
        }
    }

    private boolean isSellTool(String command) {
        Matcher matcher = getMatcher(command, sellToolRegx);
        return  matcher.matches();
    }

    private boolean isSell(String command) {
        Matcher matcher = getMatcher(command, sellRegx);
        return  matcher.matches();
    }

    private boolean isBomb(String command) {
        Matcher matcher = getMatcher(command, bombRegx);
        return  matcher.matches();
    }

    private int Distance(String command,String commandType) {
       String distance=command.replaceAll(commandType,"");
       int  blockDistance =Integer.parseInt(distance) ;
       return  blockDistance;
    }

    private boolean isBlock(String command) {
        Matcher matcher = getMatcher(command, blockRegx);
        return  matcher.matches();
    }

    private Matcher getMatcher(String command, String regx) {
        Pattern pattern=Pattern.compile(regx);
        return pattern.matcher(command);
    }

    private boolean isQuit(String command) {
        return command.equals("quit");
    }

    private boolean isHelp(String command) {
        return command.equals("help");
    }

    private boolean isRobot(String command) {
        return command.equals("robot");
    }

    private boolean isRoll(String command) {
        return command.equals("roll");
    }

    private boolean isQuery(String command) {
        return command.equals("query");
    }


}
