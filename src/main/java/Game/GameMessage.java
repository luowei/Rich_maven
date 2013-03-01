package Game;

/**
 * Created with IntelliJ IDEA.
 * User: shmilyhoney
 * Date: 13-2-22
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
public class GameMessage {

    public void  GameBeginWelcomeMessage(){
         SetColor.printline("欢迎开始大富翁游戏");
    }
    public void  setFundsPromptMessage(){
        SetColor.printline("设置玩家初始资金，范围1000～50000（默认10000）");
}
    public void  choosePlayersPromptMessage(){
        SetColor.printline("请选择2~4位不重复玩家，输入编号即可.(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):   ");
    }
    public void propHouseWelcomeMessage()   {
        SetColor.printline("欢迎光临道具屋，请选择一件您想要的道具：");
        SetColor.printline("道具        编号    价值（点数）    显示方式");
        SetColor.printline("路障         1         50          ＃");
        SetColor.printline("机器娃娃     2         30");
        SetColor.printline("炸 弹        3         50            @");
        SetColor.printline("输入道具编号选择道具，每次只能选择一件道具，按F退出道具屋");
    }

    public void GiftHouseWelcomeMessage()   {
        SetColor.printline("欢迎光临礼品屋，请选择一件您 喜欢的礼品：");
        SetColor.printline("礼    品  编号:");
        SetColor.printline("奖    金    1");
        SetColor.printline("点数卡   2");
        SetColor.printline("福     神   3");
        SetColor.printline("输入礼品编号选择礼品，只能选择一件礼品，选择后，自动退出礼品屋（输入错误视为放弃此次机会）");
    }
}
