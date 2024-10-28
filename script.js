let accounts = {};

class BankAccount 
{
    constructor(accountNumber, accountHolderName, initialBalance, accountType) 
    {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.transactionHistory = [`Account created with balance: ₹$(initialBalance}`];
    }

    deposit(amount) 
    {
        this.balance += amount;
        this.transactionHistory.push(`Deposited: ₹${amount}`);
        return `Deposited ₹${amount}. New Balance: ₹${this.balance}`;
    }

    withdraw(amount) 
    {
        if (amount > this.balance) 
        {
            return 'Insufficient funds!';
        }
        this.balance -= amount;
        this.transactionHistory.push(`Withdrew: ₹${amount}`);
        return `Withdrew ₹${amount}. New Balance: ₹${this.balance}`;
    }

    applyInterest() 
    {
        if (this.accountType === "Savings") 
        {
            const interest = this.balance * 0.03;
            this.balance += interest;
            this.transactionHistory.push(`Interest applied: ₹${interest}`);
            return `Interest of ₹${interest} applied. New Balance: ₹${this.balance}`;
        }
        return 'Interest is only applicable to Savings accounts.';
    }

    showTransactionHistory() 
    {
        return this.transactionHistory.join('<br>');
    }

    checkBalance() 
    {
        return `Current Balance: ₹${this.balance}`;
    }

    getDetails() 
    {
        return `Account Number: ${this.accountNumber}<br>Account Holder: ${this.accountHolderName}<br>Account Type: ${this.accountType}<br>Balance: ₹${this.balance}`;
    }
}

function createAccount()
{
    const accountNumber = document.getElementById('accountNumber').value;
    const accountHolderName = document.getElementById('accountHolderName').value;
    const initialBalance = parseFloat(document.getElementById('initialBalance').value);
    const accountType = document.getElementById('accountType').value;

    if (!accountNumber || !accountHolderName || isNaN(initialBalance)) 
    {
        displayOutput("Please fill in all fields correctly.");
        return;
    }

    if (accounts[accountNumber]) 
    {
        displayOutput("Account with this number already exists.");
        return;
    }

    const account = new BankAccount(accountNumber, accountHolderName, initialBalance, accountType);
    accounts[accountNumber] = account;

    displayOutput(`Account created successfully for ${accountHolderName}`);
    clearInputs();
}

function accessAccount() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];

    if (account) 
    {
        document.getElementById('account-actions').style.display = "block";
        document.getElementById('accountDetails').innerHTML = account.getDetails();
    } 
    else 
    {
        displayOutput("Account not found!");
    }
}

function deposit() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];
    const amount = parseFloat(document.getElementById('depositAmount').value);

    if (isNaN(amount) || amount <= 0) 
    {
        displayOutput("Please enter a valid deposit amount.");
        return;
    }

    const result = account.deposit(amount);
    displayOutput(result);
    document.getElementById('accountDetails').innerHTML = account.getDetails();
}

function withdraw() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];
    const amount = parseFloat(document.getElementById('withdrawAmount').value);

    if (isNaN(amount) || amount <= 0) 
        {
        displayOutput("Please enter a valid withdrawal amount.");
        return;
    }

    const result = account.withdraw(amount);
    displayOutput(result);
    document.getElementById('accountDetails').innerHTML = account.getDetails();
}

function applyInterest() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];

    const result = account.applyInterest();
    displayOutput(result);
    document.getElementById('accountDetails').innerHTML = account.getDetails();
}

function showTransactionHistory() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];

    const result = account.showTransactionHistory();
    displayOutput(result);
}

function checkBalance() 
{
    const accountNumber = document.getElementById('accountAccessNumber').value;
    const account = accounts[accountNumber];

    const result = account.checkBalance();
    displayOutput(result);
}

function displayOutput(message) 
{
    document.getElementById('output').innerHTML = message;
}

function clearInputs() 
{
    document.getElementById('accountNumber').value = '';
    document.getElementById('accountHolderName').value = '';
    document.getElementById('initialBalance').value = '';
    document.getElementById('accountType').value = 'Savings';
}