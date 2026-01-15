package Bank;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bank{


     public static void deposit(int Account_ID, double Amount) throws SQLException,IllegalArgumentException {
         Connection con=SQL.getconnection();

          if(Amount<0){
              throw new IllegalArgumentException("Amount can't be Negative !!");
              }
          else {
              PreparedStatement ps = con.prepareStatement("UPDATE account SET Balance=Balance+? WHERE Account_ID=? ;");
              ps.setDouble(1, Amount);
              ps.setInt(2, Account_ID);
              ps.executeUpdate();
              PreparedStatement ps2= con.prepareStatement("INSERT INTO transactions (Account_ID,Tx_Type,Amount) values (?,?,?)");
              ps2.setInt(1,Account_ID);
              ps2.setString(2,"Deposit");
              ps2.setDouble(3,Amount);
              ps2.executeUpdate();
          }

     }
     public static double balance(int Account_ID) throws SQLException{
         Connection con=SQL.getconnection();
         PreparedStatement ps= con.prepareStatement("SELECT Balance from account where Account_ID=?;");
         ps.setInt(1,Account_ID);
         ResultSet rs= ps.executeQuery();
         rs.next();
         double Balance=rs.getDouble("Balance");
         return Balance;
     }
     public static void withdraw(int Account_ID,double Amount)throws SQLException,InsufficiantBalanceException {
         Connection con = SQL.getconnection();
         double Balance = Bank.balance(Account_ID);
         if (Amount < 0) {
             throw new IllegalArgumentException("Amount can't be Negative !!");
         } else if (Amount > Balance) {
             throw new InsufficiantBalanceException("InSufficient Funds in Your Account!!");
         }
         else {
             PreparedStatement ps = con.prepareStatement("UPDATE account SET Balance=Balance-? WHERE Account_ID=? ;");
             ps.setDouble(1, Amount);
             ps.setInt(2, Account_ID);
             ps.executeUpdate();
             PreparedStatement ps2 = con.prepareStatement("INSERT INTO transactions (Account_ID,Tx_Type,Amount) values (?,?,?)");
             ps2.setInt(1, Account_ID);
             ps2.setString(2, "Withdraw");
             ps2.setDouble(3, Amount);
             ps2.executeUpdate();
         }

     }


}
