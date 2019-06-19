import cn.goktech.ShootGame;

import javax.swing.*;

public class MAIN_VIEW {
    private JButton btn_ToInfo = null,btn_ToGame = null;
    public static void main(String[] args){
        JFrame jFrame = new JFrame("java大项目");
        MAIN_VIEW main_view = new MAIN_VIEW();
        ShootGame game = new ShootGame();

    }
    public MAIN_VIEW(){
        btn_ToInfo = new JButton("我要看学籍");
        btn_ToGame = new JButton("我要玩游戏");
    }
}
