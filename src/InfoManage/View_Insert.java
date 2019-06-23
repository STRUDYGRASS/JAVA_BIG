package InfoManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View_Insert extends View_Student implements ActionListener {

    private JButton btn_ins = null,btn_clean = null;

    public View_Insert(){
        btn_ins = new JButton("信息录入");
        btn_clean = new JButton("清空输入");
        Box box_temp1 = Box.createHorizontalBox();
        box_temp1.add(btn_ins);
        box_temp1.add(btn_clean);

        Box box_temp2 = Box.createHorizontalBox();
        box_temp2.add(new JLabel("输入信息以供录入。"),JLabel.CENTER);

        this.boxH.add(box_temp2);
        this.boxH.add(box_temp1);

        btn_ins.addActionListener(this);
        btn_clean.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_ins) {
            if (this.tx_account.getText().equals("") || this.tx_class.getText().equals("") || this.tx_major.getText().equals("") || this.tx_name.getText().equals("") || this.tx_date.getText().length() != 10){
                JOptionPane.showMessageDialog(null,"输入格式错误!");
            }
            else {
                //录入该信息
            }
        }
        else if (e.getSource() == btn_clean){
            this.tx_account.setText(null);
            this.tx_name.setText(null);
            this.sman.setSelected(false);
            this.swoman.setSelected(false);
            this.tx_major.setText(null);
            this.tx_class.setText(null);
            this.tx_date.setText(null);
        }
    }
}
