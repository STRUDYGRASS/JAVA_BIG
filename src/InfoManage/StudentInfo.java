package InfoManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentInfo {
    //信息插入
    public static int insert(Student student)throws Exception{
        Connection con=Mysql.getCon();
        String sql_insert="insert into test3 values(?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql_insert);
        pstmt.setInt(1, student.getAccount());
        pstmt.setString(2, student.getName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, student.getMajor());
        pstmt.setInt(5,student.getCLass());
        pstmt.setString(6, student.getDate());
        return pstmt.executeUpdate();
    }

    //信息查询
    public static ResultSet select(int Account)throws Exception{
        Connection con=Mysql.getCon();
        String sql_select="select * from test3 where 学号=?";
        PreparedStatement pstmt=con.prepareStatement(sql_select);
        pstmt.setInt(1,Account);
        return pstmt.executeQuery();
    }
    public static ResultSet select()throws Exception{
        Connection con=Mysql.getCon();
        String sql_select="select * from test3 ";
        Statement pstmt=con.createStatement();
        return pstmt.executeQuery(sql_select);
    }

    //信息删除
    public static int delete(int Account)throws Exception{
        Connection con=Mysql.getCon();
        String sql_delete="delete from test3 where 学号=?";
        PreparedStatement pstmt=con.prepareStatement(sql_delete);
        pstmt.setInt(1, Account);
        return pstmt.executeUpdate();
    }

    //信息修改
    public static int update(Student student)throws Exception{
        Connection con=Mysql.getCon();
        String sql="update test3 set 姓名=?,性别=?,专业=?,班级=?,入学时间=? where 学号=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, student.getName());
        pstmt.setString(2, student.getSex());
        pstmt.setString(3, student.getMajor());
        pstmt.setInt(4, student.getCLass());
        pstmt.setString(5, student.getDate());
        pstmt.setInt(6, student.getAccount());
        return pstmt.executeUpdate();
    }

}
