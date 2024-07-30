package management;
import java.util.*;
interface Account {
    void withdraw(double amount);
    void deposit(double amount);
    double getBalance();
    long getAccountNumber();
    List<String> getTransactionHistory();
}