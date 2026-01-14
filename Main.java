package Bank;
import java.util.Scanner;
import java.sql.SQLException;

public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("1. Register\n2. Login\n3. Exit\nEnter option:");
                int ch = sc.nextInt();

                if (ch == 1) {
                    sc.nextLine();
                    System.out.print("Name: ");
                    String Name = sc.nextLine();
                    System.out.print("PIN: ");
                    int Pin = sc.nextInt();
                    Register.register(Name,Pin);
                }

                else if (ch == 2) {
                    System.out.print("User ID: ");
                    int User_ID = sc.nextInt();
                    System.out.print("PIN: ");
                    int Pin = sc.nextInt();
                    int Account_ID = Login.login(User_ID,Pin);
                    if (Account_ID == -1) {
                        System.out.println("Invalid login");
                        continue;
                    }

                    while (true) {
                        System.out.print("1 Deposit\n2 Withdraw\n3 Balance\n4 Logout\nEnter your Option: ");
                        int op = sc.nextInt();

                        if (op == 1) {
                            System.out.print("Enter Deposit Amount : ");
                            Bank.deposit(Account_ID, sc.nextDouble());
                        }
                        else if (op == 2) {
                            System.out.print("Enter Withdraw Amount: ");
                            Bank.withdraw(Account_ID, sc.nextDouble());
                        }
                        else if (op == 3) {
                            System.out.println("Your Bank Account Balance is : " + Bank.balance(Account_ID));
                        }
                        else break;
                    }
                }
                else break;

            } catch (SQLException e) {
                System.out.println("Error While Connecting to SQL Server!!");
            }catch (InsufficiantBalanceException | IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
