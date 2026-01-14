package Bank;

public class InsufficiantBalanceException extends Exception {
    InsufficiantBalanceException(String message){
        super(message);
    }
}
