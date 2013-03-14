package RichMap;

import Game.RichGame;
import player.Player;

public class Ground {
    private String groundName="0";
	private int groundType=0;
	private int price=0;
	private int point=0;
	private String owners="0";//0wei
    private String display="0";
    private boolean isSystemLand=false;
	public void initalizeGround(String groundName){
        this.groundName=groundName;
        this.display=groundName;
    }

	
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public int getGroundType() {
		return groundType;
	}

	public void setGroundType(int groundType) {
		this.groundType = groundType;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

    public boolean getIsSystemLand() {
        return isSystemLand;
    }

    public void setIsSystemLand(boolean systemLand) {
        isSystemLand = systemLand;
    }

    public boolean isOwnerOfThePlayer(String owner)  {
        return this.owners.equals(owner);
    }

    public int calculateTolls() {
        int times=(int)Math.pow(2, getGroundType());
        return (getPrice()/2) *times;
    }

    public Player getGroundOwner(RichGame richGame) {
        int ownerIndex = 0;
        for(int index=0;index< richGame.getPlayerCount();index++){
            if(richGame.getPlayer(index).getDisplayName().equals(getOwners())){
                ownerIndex = index;
                break;
            }
        }
        return richGame.getPlayer(ownerIndex);
    }
}