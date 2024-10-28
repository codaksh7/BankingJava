import java.util.*;

public class BankingSystem 
{

    static class BankAccount 
    {
        private final String accountNumber;
        private final String accountHolderName;
        private double balance;
        private final String accountType;
        private final List<String> transactionHistory;
        private final String branch;
        private String cardDetails;
        private double fixedDeposit;
        private double recurringDeposit;
        private double investments;

        public BankAccount(String accountNumber, String accountHolderName, double initialBalance, String accountType, String branch) 
        {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
            this.accountType = accountType;
            this.branch = branch;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add("Account created with balance: ₹" + initialBalance);
            this.cardDetails = "No card issued";
            this.fixedDeposit = 0;
            
            this.recurringDeposit = 0;
            this.investments = 0;
        }

        public void deposit(double amount) 
        {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
        }

        public void withdraw(double amount) 
        {
            if (amount <= balance) 
            {
                balance -= amount;
                transactionHistory.add("Withdrawn: ₹" + amount);
            } 
            else 
            {
                System.out.println("Insufficient funds!");
            }
        }

        public void checkBalance()
        {
            System.out.println("Balance: ₹" + balance);
        }

        public void applyInterest() 
        {
            if (accountType.equalsIgnoreCase("Savings")) 
            {
                double interest = balance * 0.03;
                balance += interest;
                transactionHistory.add("Interest applied: ₹" + interest);
            }
        }

        public void showTransactionHistory() 
        {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) 
            {
                System.out.println(transaction);
            }
        }

        public void displayAccountDetails() 
        {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Type: " + accountType);
            System.out.println("Branch: " + branch);
            System.out.println("Balance: ₹" + balance);
            System.out.println("Card Details: " + cardDetails);
        }

        public void setFD(double amount)
        {
            fixedDeposit = amount;
            transactionHistory.add("Fixed Deposit: ₹" + amount);
        }

        public void setRD(double amount) 
        {
            recurringDeposit = amount;
            transactionHistory.add("Recurring Deposit: ₹" + amount);
        }

        public void addInvestment(double amount) 
        {
            investments += amount;
            transactionHistory.add("Invested: ₹" + amount);
        }

        public void issueCard(String cardType) 
        {
            cardDetails = cardType + " card issued";
            transactionHistory.add(cardType + " card issued");
        }

        public void upiPayment(double amount) 
        {
            if (amount <= balance) {
                balance -= amount;
                transactionHistory.add("UPI Payment of ₹" + amount);
            } else {
                System.out.println("Insufficient balance for UPI payment!");
            }
        }

        public double calculateInterest(double principal, double rate, int years) 
        {
            return principal * rate * years / 100;
        }

        public double getFixedDeposit() 
        {
            return fixedDeposit;
        }

        public double getRecurringDeposit() 
        {
            return recurringDeposit;
        }

        public double getInvestments() 
        {
            return investments;
        }
    }

    private static final Map<String, BankAccount> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) 
    {
        try (scanner) 
        {
            int choice;
            do 
            {
                System.out.println("\nBanking System Menu");
                System.out.println("1. Create Account");
                System.out.println("2. Login to Account");
                System.out.println("3. Customer Support");
                System.out.println("4. Branch Locations");
                System.out.println("5. Financial Calculator");
                System.out.println("6. Exit");
                choice = scanner.nextInt();
                switch (choice)
                {
                    case 1 -> createAccount();
                    case 2 -> accessAccount();
                    case 3 -> customerSupport();
                    case 4 -> showBranchLocations();
                    case 5 -> financialCalculator();
                    case 6 -> System.out.println("Exiting system...");
                    default -> System.out.println("Invalid choice, try again.");
                }
            }
            while (choice != 6);
        }
    }

    public static void createAccount() 
    {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        System.out.print("Enter Account Type (Savings/Current): ");
        String accountType = scanner.next();
        System.out.print("Enter Branch: ");
        String branch = scanner.next();
        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialBalance, accountType, branch);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    public static void accessAccount() 

    {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.next();
        BankAccount account = accounts.get(accountNumber);
        if (account != null) 
        {
            int action;
            do 
            {
                System.out.println("\nAccount Menu");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Apply Interest");
                System.out.println("5. Fixed Deposit");
                System.out.println("6. Recurring Deposit");
                System.out.println("7. Investment");
                System.out.println("8. UPI Payment");
                System.out.println("9. Issue Card");
                System.out.println("10. Show Transaction History");
                System.out.println("11. Show Account Details");
                System.out.println("12. Exit Account Menu");
                action = scanner.nextInt();
                switch (action) 
                {
                    case 1 -> account.checkBalance();
                    case 2 -> {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        account.deposit(depositAmount);
                    }
                    case 3 -> {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        account.withdraw(withdrawAmount);
                    }
                    case 4 -> account.applyInterest();
                    case 5 -> {
                        System.out.print("Enter Fixed Deposit amount: ");
                        double fdAmount = scanner.nextDouble();
                        account.setFD(fdAmount);
                    }
                    case 6 -> {
                        System.out.print("Enter Recurring Deposit amount: ");
                        double rdAmount = scanner.nextDouble();
                        account.setRD(rdAmount);
                    }
                    case 7 -> {
                        System.out.print("Enter investment amount: ");
                        double invAmount = scanner.nextDouble();
                        account.addInvestment(invAmount);
                    }
                    case 8 -> {
                        System.out.print("Enter UPI payment amount: ");
                        double upiAmount = scanner.nextDouble();
                        account.upiPayment(upiAmount);
                    }
                    case 9 -> {
                        System.out.print("Enter card type (Debit/Credit): ");
                        String cardType = scanner.next();
                        account.issueCard(cardType);
                    }
                    case 10 -> account.showTransactionHistory();
                    case 11 -> account.displayAccountDetails();
                    case 12 -> System.out.println("Exiting account menu...");
                    default -> System.out.println("Invalid choice.");
                }
            } 
            while (action != 12);
        } 
        else 
        {
            System.out.println("Account not found!");
        }
    }

    public static void customerSupport() 
    {
        System.out.println("Customer Support: Call 1800-123-4567 or email support@bank.com");
    }

    public static void showBranchLocations() 
    {
        System.out.println("Branch Locations: New York, San Francisco, Chicago, Dallas.");
    }

    public static void financialCalculator() 
    {
        System.out.print("Enter Principal Amount: ");
        double principal = scanner.nextDouble();
        System.out.print("Enter Interest Rate (%): ");
        double rate = scanner.nextDouble();
        System.out.print("Enter Time Period (years): ");
        int years = scanner.nextInt();
        double interest = principal * rate * years / 100;
        System.out.println("Calculated Interest: ₹" + interest);
    }
}