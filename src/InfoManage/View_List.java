package InfoManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class View_List extends JPanel {

    JTable jt;
    ResultSet list;
    JScrollPane jsp;

    public View_List() {

        ListInit();

        this.add(jsp);
        this.setSize(400, 300);
        this.setVisible(true);  //是否可见

    }

    public void ListInit(){
        Vector info = new Vector();  //info存储学生学籍
        Vector tablehead = new Vector();   //存储表头信息

        tablehead.add("学号");
        tablehead.add("姓名");
        tablehead.add("性别");
        tablehead.add("专业");
        tablehead.add("班级");
        tablehead.add("入学时间");

        try {

            list = StudentInfo.select();
            Vector hang = new Vector();
            while (list.next()) {
                hang.add(list.getInt(1));
                hang.add(list.getString(2));
                hang.add(list.getString(3));
                hang.add(list.getString(4));
                hang.add(list.getInt(5));
                hang.add(list.getString(6));
                info.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jt = new JTable(info, tablehead); //建立学籍表
        jsp = new JScrollPane(jt);
    }

}
