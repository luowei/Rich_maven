package player;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午2:42
 * To change this template use File | Settings | File Templates.
 */
public class QianfurenPlayer extends Player {
    public QianfurenPlayer() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public QianfurenPlayer(int i) {
        super(i);
    }

    @Override
    public String getDisplayName() {
        return "Q";
    }

    @Override
    public String getCharacterName() {
        return "钱夫人";
    }
}
