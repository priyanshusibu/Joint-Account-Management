package management;
import java.util.ArrayList;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
class JointAccount implements Account {
    private long accountNumber;
    private double balance;
    private List<String> transactionHistory;

    public JointAccount(double initialBalance,long account_number) {
        this.accountNumber = account_number;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addTransaction("Initial deposit", initialBalance);
    }

    @Override
    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addTransaction("Withdrawal", -amount);
            System.out.printf("[Account %d] %s withdrew $%.2f. New balance: $%.2f%n",
                    accountNumber, Thread.currentThread().getName(), amount, balance);
        } else {
            System.out.printf("[Account %d] Insufficient funds for %s. Current balance: $%.2f%n",
                    accountNumber, Thread.currentThread().getName(), balance);
        }
    }

    @Override
    public synchronized void deposit(double amount) {
        balance += amount;
        addTransaction("Deposit", amount);
        System.out.printf("[Account %d] %s deposited $%.2f. New balance: $%.2f%n",
                accountNumber, Thread.currentThread().getName(), amount, balance);
    }

    @Override
    public synchronized double getBalance() {
        return balance;
    }

    @Override
    public long getAccountNumber() {
        return accountNumber;
    }

    @Override
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    private void addTransaction(String type, double amount) {
        LocalDateTime now = LocalDateTime.now();
        String transaction = String.format("%s | %s | $%.2f | Balance: $%.2f",
                now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                type, amount, balance);
        transactionHistory.add(transaction);
    }
}

