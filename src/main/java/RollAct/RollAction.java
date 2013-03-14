package RollAct;

import Game.GameMessage;
import Game.RichGame;
import Game.SetColor;
import Game.UserInput;
import RichMap.Ground;
import player.Player;

import java.awt.*;
import java.security.InvalidParameterException;

/**
 * Created with IntelliJ IDEA.
 * User: cherish
 * Date: 13-2-26
 * Time: 上午2:39
 * To change this template use File | Settings | File Templates.
 */
public class RollAction {
    private Player player;
    private RichGame richGame ;
    private UserInput userInput;
    public RollAction(Player player,RichGame richGame,UserInput userInput){
        this.player=player;
        this.richGame=richGame;
        this.userInput=userInput;
    }
    public Player getPlayer(){
        return player;
    }

    public RichGame getRichGame(){
        return richGame;
    }

    public void executeRoll(int rollResult){
        int propFirstMeet=meetPropLocation(rollResult);
        if(propFirstMeet>0){
            propAction(propFirstMeet) ;
        }  else{
            player.setLocaion((player.getLocaion()+rollResult)%70);
        }
        groundAction();
    }

    private void groundAction() {
        switch(player.getLocaion()){
            case 0:break;
            case 14:player.getIntoHospital();break;
            case 28:buyPropHouse();break;
            case 35:chooseGiftHouse();break;
            case 49:player.getIntoPrisson();break;
            case 63:useMagic();break;
            default:otherLocationPerformance(player.getLocaion());
        }
    }

    private void otherLocationPerformance(int location) {
        if(isPointsArea(location)) {
            player.setPoints(player.getPoints() + richGame.getGameMap().getGroundList().get(location).getPoint());
        } else{
            estateOperation(location);
        }
    }

    private void estateOperation(int location) {
        Ground ground=richGame.getGameMap().getGroundList().get(location);
        if(isAreaHaveNoOwner(ground)){
            sellSpacePromt(location, ground);
        } else if(isThePlayerLandProperty(ground)) {
            if( richGame.getGameMap().getGroundList().get(location).getGroundType()<3){
                String updateEstateMessage="是否升级该处地产，" + ground.getPrice() + "元（Y/N）?";
                SetColor.printline(updateEstateMessage);
                String result=getResult();
                if(result.equals("y")&&player.getFunds()>=ground.getPrice()) {
                    richGame.getGameMap().getGroundList().get(location).setGroundType(ground.getGroundType()+1);
                    player.minusFunds(ground.getPrice());
                    player.getLandedProperty()[ground.getGroundType()-1]-=1;
                    player.getLandedProperty()[ground.getGroundType()]+=1;
                } else if(player.getFunds()<ground.getPrice()){
                    SetColor.printline("资金不足");
                }
            }
        }   else{
            int ownerIndex = 0;
            for(int index=0;index<richGame.getPlayerCount();index++){
                if(richGame.getPlayer(index).getDisplayName().equals(ground.getOwners())){
                    ownerIndex = index;
                    break;
                }
            }
            int i= ownerIndex;
            if(player.getMascotLeftDays()>0){
                SetColor.printline("福神在身，免收过路费");
            }  else if(richGame.getPlayer(i).getHospitalOrPrison()>0){
                SetColor.printline("主人不在，免收过路费");
            } else{
                int tolls = ground.calculateTolls();
                payTolls(tolls, ground);
            }
        }
    }

    private void payTolls(int tolls, Ground ground) {
        Player groundOwner = ground.getGroundOwner(richGame);
        if(player.getFunds()<tolls) {
              player.setBankrupt(true);
        }   else{
            player.minusFunds(tolls);
            groundOwner.addFunds(tolls);
            SetColor.printline(groundOwner.getCharacterName() + "的地盘,交过路费" + tolls + "元");
        }
    }

    private void sellSpacePromt(int location, Ground ground) {
        String updateEstateMessage="是否升级该处地产，" + ground.getPrice() + "元（Y/N）?";
        SetColor.printline(updateEstateMessage);
        String result=getResult().toLowerCase();
        if(result.equals("y")&&player.getFunds()>=ground.getPrice()) {
            buySpace(location, ground);
        } else if(player.getFunds()<ground.getPrice()){
            SetColor.printline("资金不足");
        }
    }

    private void buySpace(int location, Ground ground) {
        richGame.getGameMap().getGroundList().get(location).setOwners(player.getDisplayName());
        player.minusFunds(ground.getPrice());
        player.getLandedProperty()[0]+=1;
    }

    private boolean isThePlayerLandProperty(Ground ground) {
        return ground.getOwners().equals(player.getDisplayName());
    }

    private boolean isAreaHaveNoOwner(Ground ground) {
        return ground.getOwners().equals("0");
    }


    private String getResult() {
        String result="";
        boolean firstRead= true;
        while (isNotYOrN(result)) {
            if(!firstRead) {
                SetColor.printColorStringln("请输入Y或N",Color.PINK);
            }
            result=userInput.readUserInput();

            firstRead=false;
        }
        return result.toLowerCase();
    }

    private boolean isNotYOrN(String result) {
        if(result.toLowerCase().equals("y"))
            return false;
        else if(result.toLowerCase().equals("n"))
            return false;
        return true;
    }

    private boolean isPointsArea(int location) {
        return location>63&&location<=69;
    }


    private void buyPropHouse() {
        GameMessage gameMessage=new GameMessage();
        gameMessage.propHouseWelcomeMessage();
        if(player.getPoints()<30) {
            SetColor.printColorStringln("点数不够,退出道具屋",Color.PINK);
            return;
        } else{
            int propType=-1 ;
            while(propType!=0) {
                propType=getPropType();
                propType=ToolAction(propType);
            }
        }
    }

    private int getPropType(){
        int giftType=-1;
        while(giftType<0){
            try{
                giftType=choose();
            }  catch(InvalidParameterException e){
                userInput.printMessage(e.getMessage());
            }
        }
        return giftType;
    }
    private int ToolAction(int giftType) {
         if(player.propBoxIsHaveSpace()) {
             switch(giftType) {
                 case 1:return buyBlock();
                 case 2:return buyRobot();
                 case 3:return buyBomb();
             }
         }   else{
             SetColor.printColorStringln("prop box is full",Color.PINK);
         }
        return 0;
    }

    private int buyBomb() {
        if(player.getPoints()>=50) {
            player.getProps()[2]+=1;
            player.setPoints(player.getPoints()-50);
            return 1 ;
        }  else{
            SetColor.printColorStringln("点数不够,退出道具屋", Color.PINK);
            return 0;
        }
    }

    private int buyRobot() {
        if(player.getPoints()>=30) {
            player.getProps()[1]+=1;
            player.setPoints(player.getPoints()-30);
            return 1;
        }  else{
            SetColor.printColorStringln("点数不够,退出道具屋",Color.PINK);
            return 0;
        }
    }

    private int buyBlock() {
        if(player.getPoints()>=50) {
            player.getProps()[0]+=1;
            player.setPoints(player.getPoints()-50);
            return 1;
        }  else{
            SetColor.printColorStringln("点数不够,退出道具屋",Color.PINK);
            return 0;
        }
    }

    private void chooseGiftHouse() {
       GameMessage gameMessage=new GameMessage();
       gameMessage.GiftHouseWelcomeMessage();
        int giftType=0;
        try{
           giftType=choose();
        } catch(InvalidParameterException e) {
            SetColor.printColorStringln("输入错误，退出礼品屋",Color.PINK);
        }
        GiftAction(giftType);
   }

    private void GiftAction(int giftType) {
        switch(giftType) {
            case 0: SetColor.printline("退出礼品屋");break;
            case 1:
                player.addFunds(2000);
                break;
            case 2:player.setPoints(player.getPoints()+200);break;
            case 3:player.setMascotLeftDays(5);break;
        }
    }




    private int choose() {
       String command= userInput.readUserInput() ;
       int propType=0;
       if(command.toUpperCase().equals("F")) {
           SetColor.printline("已退出道具屋");
           return propType;
       }else{
            try{
                propType=Integer.parseInt(command);
            } catch(Exception e)  {
                throw new InvalidParameterException("prop type should be 1 or 2 or 3");
            }
       }
       return   propType;
    }

    private void useMagic() {

    }

    private void propAction(int propFirstMeet) {
       String propName= (String)richGame.getProps().get(propFirstMeet);
       if(propName.equals("BOMB")){
              bombAction();
       } else if(propName.equals("BLOCK")) {
              blockAction(propFirstMeet);
       }
       richGame.getProps().remove(propFirstMeet) ;
    }

    private void blockAction(int propFirstMeet) {
        player.stopInBlock(propFirstMeet) ;
    }

    private void bombAction() {
        player.setLocaion(14);
    }

    private int meetPropLocation(int rollResult) {
        int CheckLocation=(player.getLocaion()+1)%70;
        int propLocation=-1;
        while(rollResult>0){
            if(richGame.getProps().containsKey(CheckLocation)){
                propLocation=CheckLocation;
                return propLocation;
            }
            CheckLocation++;
            rollResult--;
        }
        return propLocation;
}
}
