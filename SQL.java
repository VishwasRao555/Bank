package Bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQL{
    public static Connection getconnection()throws SQLException{
            String url = "jdbc:mysql://localhost:3306/bank";
            String user = "root";
            String password = "Vishwas@555";
            Connection con = DriverManager.getConnection(url, user, password);
            return con;
    }
}
