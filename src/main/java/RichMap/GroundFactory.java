package RichMap;

public class GroundFactory {

    public static Ground buildPrisonGround() {
        Ground ground = new Ground();
        ground.initalizeGround("P");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildEmptyGroundWithPrice(int price) {
        Ground ground = new Ground();
        ground.initalizeGround("0");
        ground.setPrice(price);
        return ground;
    }

    public static Ground buildStartGround() {
        Ground ground = new Ground();
        ground.initalizeGround("S");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildHospitalGround() {
        Ground ground = new Ground();
        ground.initalizeGround("H");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildToyGround() {
        Ground ground = new Ground();
        ground.initalizeGround("T");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildGiftGround() {
        Ground ground = new Ground();
        ground.initalizeGround("G");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildMagicGround() {
        Ground ground = new Ground();
        ground.initalizeGround("M");
        ground.setIsSystemLand(true);
        return ground;
    }

    public static Ground buildMoneyGround(int point) {
        Ground ground = new Ground();
        ground.initalizeGround("$");
        ground.setPoint(point);
        ground.setIsSystemLand(true);
        return ground;
    }
}
