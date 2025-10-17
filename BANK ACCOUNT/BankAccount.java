

class BankAccount {
    public enum AccountType{
        CURRENT, SAVINGS;
    }
    //declare the constants given to us in the prompt and assign realistic values to each of them
    private static final double CURRENT_ACCT_MIN_BALANCE = 20;
    private static final double CURRENT_ACCT_MAINTENANCE_FEE = 10;
    private static final double SAVINGS_ACCT_MIN_BALANCE = 100;
    private static final double SAVINGS_ACCT_INTEREST_RATE = 2;
    private static final int SAVINGS_WITHDRAWAL_LIMIT = 2;

    //next we declare our variables
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;
    private double interestRate;
    private double maintenanceFee;
    private int withdrawalLimit;

// We initiliaze the first of our constructors, which is ther bank account
// This constructor specifies the account type and the ID, with the opening balances and number of withdrawals
    public BankAccount(AccountType acctType, String acctID){
        this.acctType = acctType;
        this.acctID = acctID;
        this.balance = 0.00;
        this.numWithdrawals = 0;
        
        // We use an if statement to set the different constants we defined if it is either a current or savings account
            if (acctType == AccountType.CURRENT){
                this.minBalance = CURRENT_ACCT_MIN_BALANCE;
                this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
                this.interestRate = 0;
                this.withdrawalLimit = -1;
            }
            else{
                this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
                this.maintenanceFee = 0;
                this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
                this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            }
            // For the in the red, the balance of the account must be less than the minimum balance
            //Since it is boolean, it will be true if the condition is met
            this.inTheRed = (this.balance < this.minBalance);
    }
// For the second constructor, the only difference is that the balance is set to opening balance instead of 0
    public BankAccount (AccountType acctType, String acctID, double openingBalance){
        this.acctType = acctType;
        this.acctID = acctID;
        this.balance = openingBalance;
        this.numWithdrawals = 0;

        // We use an if statement to set the different constants we defined if it is either a current or savings account
        if (acctType == AccountType.CURRENT){
            this.minBalance = CURRENT_ACCT_MIN_BALANCE;
            this.maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            this.interestRate = 0;
            this.withdrawalLimit = -1;
        }
        else{
            this.minBalance = SAVINGS_ACCT_MIN_BALANCE;
            this.maintenanceFee = 0;
            this.interestRate = SAVINGS_ACCT_INTEREST_RATE;
            this.withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
        }
        this.inTheRed = (this.balance < this.minBalance);
    }
    // We now initialize our getters

    //This is to get the balance of the account
    public double getBalance(){
        return balance;
    }
    // This returns the account type (current or savings)
    public AccountType getAccountType(){
        return acctType;
    }
    //This returns the account ID
    public String getAccountID(){
        return acctID;
    }
    //This returns the minimum balance
    public double getMinBalance(){
        return minBalance;
    }
    // This method is to withdraw money from the account
    public boolean withdraw(double amount){
        // We first check if the client has exceeded the withdrawal limit
        if (withdrawalLimit != -1 && numWithdrawals >= withdrawalLimit){
            System.out.println("WITHDRAWAL LIMIT EXCEEDED");
            return false;
        }
        // Then we check if the account is in the red, if it is there can be no withdrawals
        if (inTheRed){
            System.out.println("ACCOUNT IN THE RED. WITHDRAWAL UNSUCCESSFUL");
            return false;
        }
        //Then we check if the account is empty
        if (amount <= 0){
            System.out.println("INSUFFICIENT BALANCE");
            return false;
        }
        //Finally, we check to see if the withdrawal would empty the account. If so, the transaction cannot be performed
        if (balance - amount < minBalance){
            System.out.println("INSUFFICIENT BALANCE");
            return false;
        }
        // Then for the actual operations, if all the conditions for withdrawal are met, the transaction takes place
        balance -= amount;
        System.out.println(amount+" WITHDRAWN || CURRENT BALANCE: "+balance);
        inTheRed = (balance < minBalance);
        return true;
    }
    // Before we deposit, we must check if there's actually any money in the account
    public void deposit (double amount){
        if (amount <= 0){
            System.out.println("BALANCE CANNOT BE NEGATIVE");}
            else{
                balance += amount;
                System.out.println(amount+" DEPOSITED || CURRENT BALANCE: "+balance);
            }
        }
    
        public void performMonthlyMaintenance(){
        // Calculate and add monthly interest (Annual rate)
        double earnedInterest = balance * (interestRate / 12);
        balance += earnedInterest;
        
        // Subtract the maintenance fee
        balance -= maintenanceFee;
        
        // Check if account is in the red
        inTheRed = (balance < minBalance);
        
        // Reset monthly withdrawals
        numWithdrawals = 0;
        
        // Print summary
        System.out.println("Earned interest: " + earnedInterest);
        System.out.println("Maintenance fee: " + maintenanceFee);
        System.out.println("Updated balance: " + balance);
        
        if (inTheRed) {
            System.out.println("WARNING: This account is in the red!");
        }
    }
    
    public boolean transfer(boolean transferTo, BankAccount otherAccount, double amount){
        if (transferTo) {
            // Transfer from the user account to the other account
            if (withdraw(amount)) {
                otherAccount.deposit(amount);
                return true;
            } else {
                return false;
            }
        } else {
            // Transfer other account to the user
            if (otherAccount.withdraw(amount)) {
                deposit(amount);
                return true;
            } else {
                return false;
            }
        }
    }
}
