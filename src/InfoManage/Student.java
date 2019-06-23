package InfoManage;

import javax.print.DocFlavor;
import java.awt.event.ActionEvent;

public class Student {
    private int Account;
    private String Name;
    private char Sex;
    private String Major;
    private int CLass;
    private String date; //用了string

    public int getAccount(){
        return Account;
    }

    public String getName(){
        return Name;
    }

    public char getSex(){
        return Sex;
    }

    public String getMajor(){
        return Major;
    }

    public int getCLass(){
        return CLass;
    }

    public String getDate(){
        return date;
    }

    public void setAccount(int account){
        this.Account = account;
    }

    public void setName(String name){
        this.Name = name;
    }

    public void setSex(char sex){
        this.Sex = sex;
    }

    public void setMajor(String major){
        this.Major = major;
    }

    public void setCLass(int cLass){
        this.CLass = cLass;
    }

    public void setDate(String date){
        this.date=date;
    }

}
