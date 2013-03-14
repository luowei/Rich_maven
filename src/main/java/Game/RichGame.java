package Game;

import RichMap.GameMap;
import RichMap.GroundFactory;
import player.Player;

import java.util.HashMap;
import java.util.List;


public class RichGame {

	private int funds=10000;
	private String playersTypes;
    private List<Player> players;
    private GameMap gameMap;
    private HashMap props;
    private boolean exitGameFlag=false;
    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }


	public Integer getFunds() {
		return funds;
	}

	public void setFunds(int funds) {

        this.funds=funds;
	}

	public List<Player> getPlayers(){
         return  players;
    }

    public void initialPlayers(List<Player> playerList) {
            this.players=playerList;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public void setProps(HashMap props) {
            this.props= props;
    }
    public HashMap getProps() {
        return props;
    }


    public boolean isExitGameFlag() {
        return exitGameFlag;
    }

    public void setExitGameFlag(boolean exitGameFlag) {
        this.exitGameFlag = exitGameFlag;
    }

    public void refreshMap(String playType) {
        for(int i=0;i<gameMap.getGroundList().size();i++) {
            if(gameMap.getGroundList().get(i).getOwners().equals(playType)) {
                gameMap.getGroundList().set(i, GroundFactory.buildEmptyGroundWithPrice(gameMap.getGroundList().get(i).getPrice()));
            }

        }
    }

    public void changeMapDisplay(){
        resetGameMapDisplay();
        resetGameMapDisplayWithProps();
        reershGroundDiaplayWithPlayers();
    }

    private void reershGroundDiaplayWithPlayers() {
        for(int i=0;i<getPlayerCount();i++) {
            gameMap.getGroundList().get(players.get(i).getLocaion()).setDisplay(players.get(i).getDisplayName());
        }
    }

    private void resetGameMapDisplayWithProps() {
        for(int i=0;i<70;i++) {
            if(props.containsKey(i)) {
                refreshGroundDisplayWithProp(i);
            }
        }
    }

    private void refreshGroundDisplayWithProp(int i) {
        if(props.get(i).equals("BLOCK")) {
            gameMap.getGroundList().get(i).setDisplay("#");
        }  else if(props.get(i).equals("BOMB"))  {
            gameMap.getGroundList().get(i).setDisplay("@");
        }
    }

    private void resetGameMapDisplay() {
        resetGroundWhichCanBeSoldDisplay();
        resetSpecialLandDisplay();
        resetPointsLandDisplay();
    }

    private void resetPointsLandDisplay() {
        for(int i=64;i<70;i++) {
            gameMap.getGroundList().get(i).setDisplay("$");
        }
    }

    private void resetSpecialLandDisplay() {
        gameMap.getGroundList().get(0).setDisplay("S");
        gameMap.getGroundList().get(14).setDisplay("H");
        gameMap.getGroundList().get(28).setDisplay("T");
        gameMap.getGroundList().get(35).setDisplay("G");
        gameMap.getGroundList().get(49).setDisplay("P");
        gameMap.getGroundList().get(63).setDisplay("M");
    }

    private void resetGroundWhichCanBeSoldDisplay() {
        for(int i=1;i<64;i++) {
            gameMap.getGroundList().get(i).setDisplay(String.valueOf(gameMap.getGroundList().get(i).getGroundType()));
        }
    }

    public Player getPlayer(int i) {
        return getPlayers().get(i);
    }
}
