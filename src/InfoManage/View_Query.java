package InfoManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Query extends View_Student implements ActionListener {

    private JButton btn_search = null;
    public View_Query(){
        this.AccountLabel.setText("enter account to search:");
        btn_search = new JButton("查询");

        btn_search.addActionListener(this);
        this.tx_class.setEditable(false);
        this.tx_date.setEditable(false);
        this.tx_major.setEditable(false);
        this.tx_name.setEditable(false);

        Box box_temp1 = Box.createHorizontalBox();
        box_temp1.add(btn_search);

        Box box_temp2 = Box.createHorizontalBox();
        box_temp2.add(new JLabel("输入学号以供查询。"),JLabel.CENTER);

        this.boxH.add(box_temp2);
        this.boxH.add(box_temp1);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {
            //查询操作，并创建一个Student类把数值依次赋值到各个text中
            //如未找到
//            JOptionPane.showMessageDialog(null,"查无此人!");
        }
    }
}
