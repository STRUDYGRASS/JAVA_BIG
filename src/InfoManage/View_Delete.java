package InfoManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class View_Delete extends View_Student implements ActionListener {
    private JButton btn_search = null,btn_delete = null;
    private ResultSet rs;

    public View_Delete(){
        btn_search = new JButton("查询");
        btn_delete = new JButton("删除");
        this.AccountLabel.setText("enter account to search,and delete it:");

        this.tx_class.setEditable(false);
        this.tx_date.setEditable(false);
        this.tx_major.setEditable(false);
        this.tx_name.setEditable(false);

        Box box_temp1 = Box.createHorizontalBox();
        box_temp1.add(btn_search);
        box_temp1.add(btn_delete);

        Box box_temp2 = Box.createHorizontalBox();
        box_temp2.add(new JLabel("输入信息以供查询，删除。"),JLabel.CENTER);

        this.boxH.add(box_temp2);
        this.boxH.add(box_temp1);

        btn_search.addActionListener(this);
        btn_delete.addActionListener(this);
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
            //如未找到
//            JOptionPane.showMessageDialog(null,"查无此人!");
        }
        else if (e.getSource() == btn_delete){
            int OK = JOptionPane.showConfirmDialog(this,"确认删除？","删除",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if (OK == JOptionPane.YES_OPTION){
                try {
                    StudentInfo.delete(Integer.parseInt(this.tx_account.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //删除操作
            }
        }
    }
}
