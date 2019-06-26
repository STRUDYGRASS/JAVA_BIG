package InfoManage;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.Delayed;

public class InfoSystem extends JFrame implements ActionListener {
    private static int width = 600;
    private static int height = 505;

    private JMenuBar bar;
    private JMenu FileMenu,EditMenu,HelpMenu;
    private JMenuItem FM_File,FM_Exit,
            EM_Ins,EM__Mod,EM_Que,EM_Del,EM_List,
            HM_About;
    private Container container = null;
    public static CardLayout card = null;
    private View_List viewlist = null;


    public InfoSystem(){
        bar = new JMenuBar();

        FileMenu = new JMenu("文件（F）");
        FileMenu.setMnemonic(KeyEvent.VK_F); //快捷键
        FM_File = new JMenuItem("选择数据库文件位置");
        FM_Exit = new JMenuItem("退出");
        FileMenu.add(FM_File);
        FileMenu.add(FM_Exit);

        EditMenu = new JMenu("编辑（E）");
        EditMenu.setMnemonic(KeyEvent.VK_E);
        EM_Ins = new JMenuItem("学籍信息录入");
        EM__Mod = new JMenuItem("学籍信息修改");
        EM_Que = new JMenuItem("学籍信息查询");
        EM_Del = new JMenuItem("学籍信息删除");
        EM_List = new JMenuItem("学籍信息浏览");
        EditMenu.add(EM_Ins);
        EditMenu.add(EM__Mod);
        EditMenu.add(EM_Del);
        EditMenu.add(EM_Que);
        EditMenu.add(EM_List);

        HelpMenu = new JMenu("帮助（H）");
        HelpMenu.setMnemonic(KeyEvent.VK_H);
        HM_About = new JMenuItem("关于");
        HelpMenu.add(HM_About);

        bar.add(FileMenu);
        bar.add(EditMenu);
        bar.add(HelpMenu);

        this.setJMenuBar(bar);

        FM_File.addActionListener(this);
        FM_Exit.addActionListener(this);

        EM_List.addActionListener(this);
        EM_Del.addActionListener(this);
        EM_Que.addActionListener(this);
        EM__Mod.addActionListener(this);
        EM_Ins.addActionListener(this);

        HM_About.addActionListener(this);

        this.setTitle("学籍管理系统");
        this.setSize(InfoSystem.width, InfoSystem.height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        container=this.getContentPane();
        card = new CardLayout();
        container.setLayout(card);

        container.add(new JPanel(),"main"); //背景照片jpanel设置地点

        viewlist = new View_List();
        container.add(new View_Insert(),"ins");
        container.add(new View_Modify(),"mod");
        container.add(new View_Delete(),"del");
        container.add(viewlist,"lis");
        container.add(new View_Query(),"que");
        //container中add jpanel，然后用card show切换

    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == FM_File){
            //切换读取文件地址,需要的话设置成全局静态变量
            //还没有写数据的存储等等，拟就用全局变量，类中静态函数比较浪费
        }
        else if (e.getSource() == FM_Exit){
            int OK = JOptionPane.showConfirmDialog(this,"确认退出？","退出",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if (OK == JOptionPane.YES_OPTION){
                this.dispose();
            }
        }
        else if (e.getSource() == EM_Ins){
            card.show(container,"ins");
        }
        else if (e.getSource() == EM__Mod){
            card.show(container,"mod");
        }
        else if (e.getSource() == EM_Que){
            card.show(container,"que");
        }
        else if (e.getSource() == EM_Del){
            card.show(container,"del");
        }
        else if (e.getSource() == EM_List){
            card.show(container,"lis");

        }
        else if (e.getSource() == HM_About){
            JOptionPane.showMessageDialog(null,"学籍管理系统v1.0");
        }
    }
}
