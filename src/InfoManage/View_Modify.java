package InfoManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Modify extends View_Student implements ActionListener {

    private JButton btn_search = null,btn_modify = null;
    private boolean searched = false;
    public  View_Modify(){
        btn_modify = new JButton("修改");
        btn_search = new JButton("查询");
        this.AccountLabel.setText("enter account to search,and modify it:");
        searched = false;

        Box box_temp1 = Box.createHorizontalBox();
        box_temp1.add(btn_search);
        box_temp1.add(btn_modify);

        Box box_temp2 = Box.createHorizontalBox();
        box_temp2.add(new JLabel("输入学号以供查询，修改。"),JLabel.CENTER);

        this.boxH.add(box_temp2);
        this.boxH.add(box_temp1);

        btn_search.addActionListener(this);
        btn_modify.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {
            //查询操作，并创建一个Student类把数值依次赋值到各个text中
            searched = true;
            //如未找到
//            JOptionPane.showMessageDialog(null,"查无此人!");
        }
        else if (e.getSource() == btn_modify){
            if (searched){
                //做修改，再存储操作
                searched = false;
            }
            else{
                JOptionPane.showMessageDialog(null,"修改无效！!");
            }
        }
    }
}
