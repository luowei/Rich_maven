package Game;

import Command.CommandException;
import Command.CommandOperation;
import player.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-24
 * Time: 下午4:51
 * To change this template use File | Settings | File Templates.
 */
public class Rich {
    private RichPreparation richPreparation=new RichPreparation(new UserInput())  ;
    private UserInput userInput=new UserInput();
    RichGame richGame;
    int start=0;
    Player player;

    public void startRichGame() {
        richPreparation.prepareRichGame();
        richGame=  richPreparation.getRichGame();
    }
    public void riching() {

        while(richGame.getPlayerCount()>=2) {
            start= start%richGame.getPlayerCount() ;
            player= richGame.getPlayers().get(start) ;
            CommandOperation  commandOperation =new CommandOperation(player,richGame);
            APlayerRound  APlayerRound=new  APlayerRound(commandOperation);
            if(player.getLeftDays()==0){
                 while(APlayerRound.isGoing()){
                     try{
                        APlayerRound.receiveCommand(userInput);
                     }catch(CommandException e){
                         userInput.printMessage(e.getMessage());
                     }
                 }
                richGame =  commandOperation.getRichGame();
                if(richGame.isExitGameFlag()){
                    System.exit(0);
                }
                player= commandOperation.getPlayer();

                richGame.initialPlayers(putPlayer(start, player));
                if(player.getBankrupt()){
                    String bankruptMessage=player.getDisplayName()+"破产啦！！！！" ;
                    SetColor.printline(bankruptMessage);
                    richGame.getPlayers().remove(start);
                    richGame.refreshMap(player.getDisplayName());
                    start--;
                }
             }  else{
                player.statusRefresh();
                String message=commandOperation.getPlayer().getCharacterName()+"> 休息中,还剩" +player.getLeftDays()+"天";
                SetColor.printColorStringln(message, Color.GRAY);
                richGame.initialPlayers(putPlayer(start, player));
            }

            richGame.changeMapDisplay();
            richGame.getGameMap().printMap();
            start++;
        }
        String gameEndMessage= "游戏结束"+ richGame.getPlayers().get(0).getDisplayName()+"赢了"  ;
        SetColor.printline(gameEndMessage);
    }



    private List<Player> putPlayer(int start, Player player) {

        List<Player> players=new ArrayList<Player>();
            for(int i=0;i<richGame.getPlayerCount();i++) {
                 if(i!=start){
                     players.add(i,richGame.getPlayers().get(i) );
                 } else{
                         players.add(i,player);
                 }
            }
         return players;
    }


}
