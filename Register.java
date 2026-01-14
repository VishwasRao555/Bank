package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Register {
    public static void register(String Name,int Pin) throws SQLException {
        int User_ID;
        Connection con=SQL.getconnection();
        PreparedStatement ps1= con.prepareStatement("INSERT INTO user (Name,Pin) VALUES (?,?);");
        ps1.setString(1,Name);
        ps1.setInt(2,Pin);
        ps1.executeUpdate();
        PreparedStatement ps2=con.prepareStatement("SELECT User_ID FROM user WHERE Name=? ; ");
        ps2.setString(1,Name);
        ResultSet rs1=ps2.executeQuery();
        if(rs1.next()){
            User_ID=rs1.getInt("User_ID");
        }else {
            throw new SQLException("User ID not generated");
        }
        PreparedStatement ps3= con.prepareStatement("INSERT INTO account (User_ID) values(?);");
        ps3.setInt(1,User_ID);
        ps3.executeUpdate();
        con.close();
        System.out.println("Registration Successfully Completed");
        System.out.println("Your User_ID is : "+User_ID);
    }

}
