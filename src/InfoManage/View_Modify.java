package InfoManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class View_Modify extends View_Student implements ActionListener {

    private JButton btn_search = null,btn_modify = null;
    private boolean searched = false;
    private ResultSet rs;
    private Student student;
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
            try {
                rs=StudentInfo.select(Integer.parseInt(this.tx_account.getText()));
                this.tx_name.setText(rs.getString(2));
                this.tx_major.setText(rs.getString(4));
                this.tx_class.setText(String.valueOf(rs.getInt(5)));
                this.tx_date.setText(rs.getString(6));
                if(rs.getString(3)=="男"){
                    this.sman.setSelected(true);
                }else {
                    this.swoman.setSelected(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //查询操作，并创建一个Student类把数值依次赋值到各个text中
            searched = true;
            //如未找到
//            JOptionPane.showMessageDialog(null,"查无此人!");
        }
        else if (e.getSource() == btn_modify){
            if (searched){
                Student student = new Student();
                student.setAccount(Integer.parseInt(this.tx_account.getText()));
                student.setName(this.tx_name.getText());
                student.setMajor(this.tx_major.getText());
                student.setCLass(Integer.parseInt(this.tx_class.getText()));
                student.setDate(this.tx_date.getText());
                if(this.sman.isSelected()){
                    student.setSex("男");
                }else{
                    student.setSex("女");
                }
                try {
                    StudentInfo.update(student);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //做修改，再存储操作
                searched = false;
            }
            else{
                JOptionPane.showMessageDialog(null,"修改无效！!");
            }
        }
    }
}
