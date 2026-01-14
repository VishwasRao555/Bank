package Bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

        public static int login(int User_ID,int Pin) throws SQLException{
            Connection con =SQL.getconnection();
            PreparedStatement ps=con.prepareStatement
                    ("SELECT Account_ID FROM user u JOIN account a on u.User_ID=a.User_ID WHERE u.User_ID=? AND u.Pin=? ;");
            ps.setInt(1,User_ID);
            ps.setInt(2,Pin);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                return rs.getInt("Account_ID");
            }
            return -1;
        }

}
