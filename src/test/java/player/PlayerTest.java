package player;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-23
 * Time: 下午2:36
 * To change this template use File | Settings | File Templates.
 */
public class PlayerTest {

    @Test
    public void shouldGetCorrectInformationWhenIniQianfuren()
    {
         Player player = new QianfurenPlayer(10000);

        assertThat(player.getFunds(), is(10000));
        assertThat(player.getCharacterName(), is("钱夫人"));
        assertThat(player.getDisplayName(), is("Q"));
    }

    @Test
    public void shouldGetCorrectInformationWhenIniATuBo()
    {
        Player player = new ATuBoPlayer(10000);

        assertThat(player.getFunds(), is(10000));
        assertThat(player.getCharacterName(), is("阿土伯"));
        assertThat(player.getDisplayName(), is("A"));
    }

    @Test
    public void shouldGetCorrectInformationWhenSunMeiMei()
    {
        Player player = new SunMeiMeiPlayer(10000);

        assertThat(player.getFunds(), is(10000));
        assertThat(player.getCharacterName(), is("孙美美"));
        assertThat(player.getDisplayName(), is("S"));
    }

    @Test
     public void shouldGetCorrectInformationWhenJinBaoBei()
    {
        Player player = new JinBaoBeiPlayer(10000);

        assertThat(player.getFunds(), is(10000));
        assertThat(player.getCharacterName(), is("金宝贝"));
        assertThat(player.getDisplayName(), is("J"));
    }

    @Test
    public void shouldGetIntoHospitali()
    {
        Player player = new JinBaoBeiPlayer(10000);
        player.getIntoHospital();
        assertThat(player.getLocaion(),is(14));
        assertThat(player.getHospitalOrPrison(),is(1));
        assertThat(player.getLeftDays(),is(3));

    }

    @Test
    public void shouldGetIntoPrison()
    {
        Player player = new JinBaoBeiPlayer(10000);
        player.getIntoPrisson();
        assertThat(player.getLocaion(),is(49));
        assertThat(player.getHospitalOrPrison(),is(2));
        assertThat(player.getLeftDays(),is(2));

    }
}
