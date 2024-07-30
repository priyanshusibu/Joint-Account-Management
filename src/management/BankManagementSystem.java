package management;
class BankManagementSystem {
    public static void main(String[] args) {
        System.out.println("Welcome to the Joint Account Management System");
        System.out.println("----------------------------------------------");

        JointAccount jointAccount = new JointAccount(1000,987464889);
        
        System.out.printf("Joint Account created with number: %d and initial balance: $%.2f%n%n",
        jointAccount.getAccountNumber(), jointAccount.getBalance());

        Thread husband = new Thread(new AccountHolder(jointAccount, "Husband"), "Husband");
        Thread wife = new Thread(new AccountHolder(jointAccount, "Wife"), "Wife");

        System.out.println("Starting transactions...");
        husband.start();
        wife.start();

        try {
            husband.join();
            wife.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll transactions completed.");
        System.out.printf("Final balance for Account %d: $%.2f%n%n", jointAccount.getAccountNumber(), jointAccount.getBalance());

        System.out.println("Transaction History:");
        System.out.println("-------------------");
        for (String transaction : jointAccount.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}