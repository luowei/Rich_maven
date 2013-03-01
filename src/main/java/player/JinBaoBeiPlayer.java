package player;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午2:56
 * To change this template use File | Settings | File Templates.
 */
public class JinBaoBeiPlayer extends Player {
    public JinBaoBeiPlayer(int i) {
        super(i);
    }

    @Override
    public String getDisplayName() {
        return "J";
    }

    @Override
    public String getCharacterName() {
        return "金宝贝";
    }

    public JinBaoBeiPlayer() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
