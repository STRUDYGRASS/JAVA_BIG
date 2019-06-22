import InfoManage.InfoSystem;
import InfoManage.SystemLog;
import cn.goktech.ShootGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MAIN_VIEW {
    private static int View_long = 500,View_height = 500;
    private JButton btn_ToInfo = null,btn_ToGame = null;
    private JFrame jFrame = null;
    public static void main(String[] args){

        MAIN_VIEW main_view = new MAIN_VIEW();
//        ShootGame game = new ShootGame();

    }
    public MAIN_VIEW(){
        jFrame = new JFrame("java大项目");
        btn_ToInfo = new JButton("我要看学籍");
        btn_ToGame = new JButton("我要玩游戏");
        jFrame.setSize(MAIN_VIEW.View_long,MAIN_VIEW.View_height);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(new GridLayout(2,1));
        jFrame.getContentPane().add(btn_ToInfo);
        jFrame.getContentPane().add(btn_ToGame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jb = (JButton) e.getSource();
                if (jb == btn_ToGame){
                    new ShootGame();
                }
                else if (jb == btn_ToInfo){
                    new SystemLog();
                }
            }
        };
        btn_ToGame.addActionListener(actionListener);
        btn_ToInfo.addActionListener(actionListener);
        jFrame.setVisible(true);
    }
}
