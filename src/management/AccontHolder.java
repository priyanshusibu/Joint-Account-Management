package management;
class AccountHolder implements Runnable {
    private Account account;
    private String name;

    public AccountHolder(Account account, String name) {
        this.account = account;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            account.withdraw(100);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.deposit(50);
        }
    }
}