package RichMap;

import Game.SetColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private List<Ground> groundList = new ArrayList<Ground>();
    private int[] points = {20, 80, 100, 40, 80, 60};
    private String groundOwner = "0";
    private SetColor setColor=new SetColor();
    public GameMap() {
        initalizeMap();
    }

    public List<Ground> initalizeMap() {

        groundList.clear();

        initStartGround();
        initAreaOne();
        initHospitalGround();
        initAreaTwo();
        initToyGround();
        intiAreaThree();
        initGiftGround();
        intiAreaFour();
        initPrisonGround();
        intiAreaFive();
        initMagicGround();
        initMoneyGround();

        return groundList;

    }

    public List<Ground> getGroundList(){
        return groundList ;
    }

    private void initToyGround() {
        groundList.add(GroundFactory.buildToyGround());
    }

    private void initMoneyGround() {
        for(int i=0; i<points.length; i++){
            groundList.add(GroundFactory.buildMoneyGround(points[i]));
        }
    }

    private void initMagicGround() {
        groundList.add(GroundFactory.buildMagicGround());
    }

    private void intiAreaFive() {
        for (int i = 0; i < 13; i++) {
            groundList.add(GroundFactory.buildEmptyGroundWithPrice(300));
        }
    }

    private void initPrisonGround() {
        groundList.add(GroundFactory.buildPrisonGround());
    }

    private void intiAreaFour() {
        for (int i = 0; i < 13; i++) {
            groundList.add(GroundFactory.buildEmptyGroundWithPrice(300));
        }
    }

    private void initGiftGround() {
        groundList.add(GroundFactory.buildGiftGround());
    }

    private void intiAreaThree() {
        for (int i = 0; i < 6; i++) {
            groundList.add(GroundFactory.buildEmptyGroundWithPrice(500));
        }
    }

    private void initAreaTwo() {
        for (int i = 0; i < 13; i++) {
            groundList.add(GroundFactory.buildEmptyGroundWithPrice(200));
        }
    }

    private void initHospitalGround() {
        groundList.add(GroundFactory.buildHospitalGround());
    }

    private void initStartGround() {
        groundList.add(GroundFactory.buildStartGround());
    }

    private void initAreaOne() {
        for (int i = 0; i < 13; i++) {
            groundList.add(GroundFactory.buildEmptyGroundWithPrice(200));
        }
    }

    public void printMap() {
        for (int i = 0; i <= 28; i++) {
            //System.out.print(groundList.get(i).getDisplay());
            printColorGround(groundList.get(i));
        }
        //System.out.println();
        SetColor.printColorStringln("",Color.GRAY);
        int cha = 40;

        for (int i = 29; i <= 34; i++) {
            printLine(i + cha, i);
            cha = cha - 2;
        }
        for (int i = 63; i >= 35; i--) {
           //System.out.print(groundList.get(i).getDisplay());
            printColorGround(groundList.get(i));
        }
        System.out.println();
    }

    public void printLine(int firstGround, int lastGround) {
        printColorGround(groundList.get(firstGround)) ;
        for (int i = 1; i <= 27; i++)
            SetColor.printColorString(" ", Color.GRAY);
        printColorGround(groundList.get(lastGround)) ;
        SetColor.printColorStringln("",Color.GRAY);
    }
    public void printColorGround(Ground ground){
        if(ground.getOwners().equals("0")){
            SetColor.printColorString(ground.getDisplay(), Color.GRAY);
        }  else if(ground.getOwners().equals("Q")){
            SetColor.printColorString(ground.getDisplay(),Color.BLUE);
        }   else if(ground.getOwners().equals("S")){
            SetColor.printColorString(ground.getDisplay(),Color.RED);
        }   else if(ground.getOwners().equals("A")){
            SetColor.printColorString(ground.getDisplay(),Color.GREEN);
        }   else if(ground.getOwners().equals("J")){
            SetColor.printColorString(ground.getDisplay(),Color.YELLOW);
        }
    }

}
