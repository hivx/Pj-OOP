package game;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDataBase {
    Connection cnn=null;
    public Connection getConnectdatabase() {
        try {
            // path DB D:\gamehoanchinh\CSDL_TLMN
            //  String uRL = "jdbc:derby:D:\\gamehoanchinh\\CSDL_TLMN\\DB";
            // path DB C:\Users\Admin\Documents\NetBeansProjects\gamehoanchinh\CSDL_TLMN services
            String uRL = "jdbc:derby:CSDL\\DB";String user = "ttaht"; String pass = "qaz@123";
            // String uRL="jdbc:sqlserver://localhost;databasename=dataTLMN"; String user="sa";String pass="ssl";
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cnn = DriverManager.getConnection(uRL,user,pass);
            cnn=DriverManager.getConnection(uRL, user,pass);
            // System.out.println("Thanh cong");
            return cnn;
        }
        catch(Exception e) {
            System.out.println("That bai");
        }
        return null;
    }
    public static void main(String[] args) {
        ConnectDataBase cn=new ConnectDataBase();
        cn.getConnectdatabase();
    }
}
