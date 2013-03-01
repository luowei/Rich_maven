package player;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午2:49
 * To change this template use File | Settings | File Templates.
 */
public class ATuBoPlayer extends Player {
    @Override
    public String getDisplayName() {
        return "A";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCharacterName() {
        return "阿土伯";  //To change body of implemented methods use File | Settings | File Templates.
    }

    public ATuBoPlayer() {
        super();
    }

    public ATuBoPlayer(int i) {
        super(i);
    }
}
