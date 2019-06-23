package InfoManage;

import javax.swing.*;
import java.awt.*;

//为了节约一些代码量，在查询，删除，修改的地方
// 能够重用的代码抽象出来，写成单独的一个类
public class View_Student extends JPanel {
    protected JTextField tx_account,tx_name,tx_major,tx_class,tx_date;
    protected JRadioButton sman,swoman;
    protected Label AccountLabel = null;

    protected Box boxH = null;

    public View_Student(){
        tx_account =  new JTextField(10);
        tx_name = new JTextField(10);
        tx_major = new JTextField(15);
        tx_class = new JTextField(10);
        tx_date = new JTextField(15);
        sman = new JRadioButton("男",false);
        swoman = new JRadioButton("女",false);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(sman);
        buttonGroup.add(swoman);

        AccountLabel = new Label("account:",JLabel.CENTER);

        Box box1 = Box.createHorizontalBox();
        box1.add(AccountLabel);
        box1.add((tx_account));

        Box box2 = Box.createHorizontalBox();
        box2.add(new JLabel("姓名:", JLabel.CENTER));
        box2.add(tx_name);

        Box box3 = Box.createHorizontalBox();
        box3.add(new JLabel("性别:", JLabel.CENTER));
        box3.add(sman);
        box3.add(swoman);

        Box box4 = Box.createHorizontalBox();
        box4.add(new JLabel("专业:", JLabel.CENTER));
        box4.add(tx_major);

        Box box5 = Box.createHorizontalBox();
        box5.add(new JLabel("班级:", JLabel.CENTER));
        box5.add(tx_class);

        Box box6 = Box.createHorizontalBox();
        box6.add(new JLabel("入学时间(xxxx-xx-xx):", JLabel.CENTER));
        box6.add(tx_date);

        boxH = Box.createVerticalBox();
        boxH.add(box1);
        boxH.add(box2);
        boxH.add(box3);
        boxH.add(box4);
        boxH.add(box5);
        boxH.add(box6);
        boxH.add(Box.createVerticalGlue());

        this.add(boxH);
    }
}
