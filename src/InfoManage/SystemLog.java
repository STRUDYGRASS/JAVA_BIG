package InfoManage;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SystemLog extends JFrame {
    private HashSet<User> list= new HashSet<>();
    private JPasswordField passwd;
    private JTextField usname;
    private JButton login,reset;
    private SystemLog systemLog = this;

    private static int View_long = 300,View_height = 260;


    public SystemLog(){
        this.setTitle("系统登陆");
        //在开始时直接添加用户和对应密码，以后可以改为数据库或者文件读取
        list.add(new User("admin","12345"));
        Container LogContainer = this.getContentPane();
        LogContainer.setLayout(new FlowLayout());
        usname = new JTextField(10);
        passwd = new JPasswordField(10);
        login = new JButton("登陆");
        reset = new JButton("重置");

        login.addActionListener(actionListener);
        reset.addActionListener(actionListener);

        JLabel a = new JLabel("用户:");
        JLabel b = new JLabel("密码:");
        JLabel log_image = new JLabel("");
        JPanel admin = new JPanel(),pass = new JPanel(),butt = new JPanel();
        admin.add(a);
        admin.add(usname);
        pass.add(b);
        pass.add(passwd);
        butt.add(login);
        butt.add(reset);
//        LogContainer.add(log_image,BorderLayout.NORTH);
        LogContainer.add(admin);
        LogContainer.add(pass);
        LogContainer.add(butt,BorderLayout.SOUTH);

        this.setSize(this.View_long,this.View_height);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jb = (JButton) e.getSource();
            char[] password = passwd.getPassword();
            String ps = new String(password);
            String user = usname.getText();
            if (jb == login){
                if ( list.contains(new User(user,ps))){
                    JOptionPane.showMessageDialog(null,"登陆成功!");
                    systemLog.setVisible(false);
                    new InfoSystem();
                }
                else if (user.equals("") || password.equals((""))){
                    JOptionPane.showMessageDialog(null,"请输入用户名和密码!");
                }
                else{
                    JOptionPane.showMessageDialog(null,"用户名和密码错误!");
                }
            }
            else if (jb == reset){
                passwd.setText(null);
                usname.setText(null);
            }
        }
    };



    class User{
        private String us;
        private String passwd;

        public User(String us,String passwd){
            this.us = us;
            this.passwd = passwd;
        }

        public String getUs(){
            return us;
        }

        public String getPasswd(){
            return passwd;
        }

        @Override
        public boolean equals(Object obj){
            User user1 = (User)obj;
            if (user1.getUs().equals(this.getUs()) && user1.getPasswd().equals(this.getPasswd())){
                return  true;
            }
            return false;
        }

        @Override
        public int hashCode(){return this.getUs().length();} //简单判断用户名长度作为哈希code
    }
}
