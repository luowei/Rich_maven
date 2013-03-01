package player;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午2:52
 * To change this template use File | Settings | File Templates.
 */
public class SunMeiMeiPlayer extends Player {
    @Override
    public String getDisplayName() {
        return "S";
    }

    public SunMeiMeiPlayer() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String getCharacterName() {
        return "孙美美";
    }

    public SunMeiMeiPlayer(int i) {
        super(i);
    }
}
