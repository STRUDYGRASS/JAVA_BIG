package InfoManage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Mysql {
    private static String dbUrl="jdbc:mysql://rm-m5e862um91w6uv5j2bo.mysql.rds.aliyuncs.com:3306/test"; // 数据库连接地址
    private static String dbUserName="root";
    private static String dbPassword="Hqu88888";
    private static String jdbcName="com.mysql.jdbc.Driver";

    //获取数据库连接
    public static Connection getCon()throws Exception{
        Class.forName(jdbcName);
        Connection con= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    //关闭数据库连接
    public static void closeCon(Connection con)throws Exception{
        if(con!=null){
            con.close();
        }
    }

    public static void main(String[] args) {
        Mysql mysql=new Mysql();
        try {
            mysql.getCon();
            System.out.println("数据库连接成功！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败");
        }
    }

}
