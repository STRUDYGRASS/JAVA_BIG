package InfoManage;

import javax.swing.*;
import java.awt.*;

public class InfoSystem extends JFrame {
    public static int width = 600;
    public static int height = 505;


    public InfoSystem(){
        this.setTitle("学籍管理系统");
        this.setSize(InfoSystem.width, InfoSystem.height);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
