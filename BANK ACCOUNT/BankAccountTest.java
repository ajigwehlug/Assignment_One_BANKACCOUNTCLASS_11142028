public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("-----------------BANK ACCOUNT------------------");
        // Created an object acct1 for the savings account
        BankAccount acct1 = new BankAccount(BankAccount.AccountType.SAVINGS, "S001", 500.0);

        //Created the second object acct2 for the current account
        BankAccount acct2 = new BankAccount(BankAccount.AccountType.CURRENT, "C001");
        
        // Create Current Account with zero balance
        System.out.println("---Create a current account");
        System.out.println("Account ID: " + acct2.getAccountID());
        System.out.println("Account Type: " + acct2.getAccountType());
        System.out.println("Balance: " + acct2.getBalance());
        System.out.println("Min Balance: " + acct2.getMinBalance() + "\n");
        
        // Create Savings Account with opening balance
        System.out.println("---Creating a savings account");
        System.out.println("Balance: " + acct1.getBalance() + "\n");
        
        // Depositing an amount to Current Account
        System.out.println("---Depositing money to current account");
        acct2.deposit(100.0);
        System.out.println();
        
        // Withdrawing money from Current Account
        System.out.println("---Withdraw from current account");
        acct2.withdraw(50.0);
        System.out.println();
        
        //Withdrawing an amount exceeding your balance
        System.out.println("---Withdraw exceeding balance in your account");
        acct2.withdraw(100.0);
        System.out.println();
        
        // Savings account withdrawal limit
        System.out.println("---savings acct withdrawal limit test");
        acct1.withdraw(50.0);
        acct1.withdraw(50.0);
        acct1.withdraw(50.0); 
        System.out.println();
        
        // Transfer between accounts
        System.out.println("---Transferring money from a savings acct to a current acct");
        acct1.transfer(true, acct2, 100.0);
        System.out.println();
        
        // Monthly maintenance
        System.out.println("---Monthly maintenance on a savings account");
        acct1.performMonthlyMaintenance();
        System.out.println();
        
        
        // Test when the account is in the red
        System.out.println("---Account in the red");
        BankAccount redAcct = new BankAccount(BankAccount.AccountType.CURRENT, "C002", 10.0);
        redAcct.withdraw(5.0);
        System.out.println();
        
        
    }
}
