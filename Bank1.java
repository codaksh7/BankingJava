import java.util.*;

public class BankingSystem
{
    
    static class BankAccount
    {
        private final String accountNumber;
        private final String accountHolderName;
        private double balance;
        private String accountType;
        private List<String> transactionHistory;

        public BankAccount(String accountNumber, String accountHolderName, double initialBalance, String accountType)
        {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
            this.accountType = accountType;
            this.transactionHistory = new ArrayList<>();
            transactionHistory.add("Account created with balance: $" + initialBalance);
        }

        public BankAccount(String accountHolderName, String accountNumber)
        {
            this.accountHolderName = accountHolderName;
            this.accountNumber = accountNumber;
        }

        public void deposit(double amount)
        {
            if (amount > 0)
            {
                balance += amount;
                System.out.println("Deposited: $" + amount);
                transactionHistory.add("Deposited: $" + amount);
            }
            else
            {
                System.out.println("Invalid deposit amount!");
            }
        }

        public void withdraw(double amount)
        {
            if (amount > 0 && amount <= balance)
            {
                balance -= amount;
                System.out.println("Withdrawn: $" + amount);
                transactionHistory.add("Withdrawn: $" + amount);
            }
            else
            {
                System.out.println("Insufficient funds or invalid amount!");
            }
        }

        public void checkBalance()
        {
            System.out.println("Current balance: $" + balance);
        }

        public void displayAccountDetails()
        {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Account Holder: " + accountHolderName);
            System.out.println("Account Type: " + accountType);
            System.out.println("Balance: $" + balance);
        }

        public void applyInterest()
        {
            if (accountType.equalsIgnoreCase("Savings"))
            {
                double interestRate = 0.03;
                double interest = balance * interestRate;
                balance += interest;
                System.out.println("Interest of $" + interest + " applied.");
                transactionHistory.add("Interest of $" + interest + " applied.");
            }
            else
            {
                System.out.println("Interest is only applicable to Savings accounts.");
            }
        }

        public void showTransactionHistory()
        {
            System.out.println("Transaction History for Account: " + accountNumber);
            for (String transaction : transactionHistory)
            {
                System.out.println(transaction);
            }
        }

        public String getAccountNumber()
        {
            return accountNumber;
        }
    }

    private static final Map<String, BankAccount> accounts = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        try (scanner) {
            int choice;
            do
            {
                System.out.println("\nBanking System Menu");
                System.out.println("1. Create Account");
                System.out.println("2. Access Account");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                
                switch (choice)
                {
                    case 1 -> createAccount();
                    case 2 -> accessAccount();
                    case 3 -> System.out.println("Exiting system...");
                    default -> System.out.println("Invalid choice, please try again.");
                }
            }
            while (choice != 3);
        }
    }

    public static void createAccount()
    {
        scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Account Type (Savings/Current): ");
        String accountType = scanner.nextLine();

        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, initialBalance, accountType);
        accounts.put(accountNumber, newAccount);
        System.out.println("Account created successfully!");
    }

    public static void accessAccount()
    {
        scanner.nextLine();
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accounts.get(accountNumber);

        if (account != null)
        {
            int action;
            do {
                System.out.println("\nAccount Menu for " + accountNumber);
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Display Account Details");
                System.out.println("5. Apply Interest (Savings Only)");
                System.out.println("6. Show Transaction History");
                System.out.println("7. Exit Account Menu");
                System.out.print("Enter your choice: ");
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
                    case 4 -> account.displayAccountDetails();
                    case 5 -> account.applyInterest();
                    case 6 -> account.showTransactionHistory();
                    case 7 -> System.out.println("Exiting account menu...");
                    default -> System.out.println("Invalid choice, please try again.");
                }
            } while (action != 7);
        } else {
            System.out.println("Account not found!");
        }
    }
}