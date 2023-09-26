import java.util.*;

class Transaction {
    private String type;
    private double amount;
    private Date date;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
        this.date = new Date();
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}

class Account {
    private int accountNumber;
    private int pin;
    private double balance;
    private List<Transaction> transactions;

    public Account(int accountNumber, int pin) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transaction("Deposit", amount));
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            transactions.add(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public void transfer(Account toAccount, double amount) {
        if (withdraw(amount)) {
            toAccount.deposit(amount);
            transactions.add(new Transaction("Transfer to " + toAccount.getAccountNumber(), amount));
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}

class Bank {
    private Map<Integer, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(int accountNumber, int pin) {
        accounts.put(accountNumber, new Account(accountNumber, pin));
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }
}

public class ATM {
    private Bank bank;
    private Account currentAccount;

    public ATM(Bank bank) {
        this.bank = bank;
        this.currentAccount = null;
    }

    public boolean login(int accountNumber, int pin) {
        Account account = bank.getAccount(accountNumber);
        if (account != null && account.validatePin(pin)) {
            currentAccount = account;
            return true;
        }
        return false;
    }

    public void logout() {
        currentAccount = null;
    }

    public boolean isUserLoggedIn() {
        return currentAccount != null;
    }

    public double checkBalance() {
        return currentAccount.getBalance();
    }

    public void deposit(double amount) {
        currentAccount.deposit(amount);
    }

    public boolean withdraw(double amount) {
        return currentAccount.withdraw(amount);
    }

    public void transfer(int toAccountNumber, double amount) {
        Account toAccount = bank.getAccount(toAccountNumber);
        if (toAccount != null) {
            currentAccount.transfer(toAccount, amount);
        }
    }

    public List<Transaction> getTransactionHistory() {
        return currentAccount.getTransactionHistory();
    }
}

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(12345, 1234); // Add accounts with account number and PIN
        bank.addAccount(56789, 4321);

        ATM atm = new ATM(bank);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ATM System\n");

            if (!atm.isUserLoggedIn()) {
                System.out.print("Enter Account Number: ");
                int accountNumber = scanner.nextInt();
                System.out.print("Enter PIN: ");
                int pin = scanner.nextInt();

                if (atm.login(accountNumber, pin)) {
                    System.out.println("\nLogin Successful!\n");
                } else {
                    System.out.println("\nLogin Failed. Please try again.\n");
                    continue;
                }
            }

            System.out.println("Select Operation:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nBalance: $" + atm.checkBalance() + "\n");
                    break;
                case 2:
                    System.out.print("Enter Deposit Amount: $");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    System.out.println("\nDeposit Successful!\n");
                    break;
                case 3:
                    System.out.print("Enter Withdrawal Amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (atm.withdraw(withdrawAmount)) {
                        System.out.println("\nWithdrawal Successful!\n");
                    } else {
                        System.out.println("\nInsufficient Balance or Invalid Amount. Please try again.\n");
                    }
                    break;
                case 4:
                    System.out.print("Enter Transfer Account Number: ");
                    int toAccountNumber = scanner.nextInt();
                    System.out.print("Enter Transfer Amount: $");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(toAccountNumber, transferAmount);
                    System.out.println("\nTransfer Successful!\n");
                    break;
                case 5:
                    List<Transaction> transactions = atm.getTransactionHistory();
                    System.out.println("\nTransaction History:");
                    for (Transaction transaction : transactions) {
                        System.out.println(transaction.getType() + " $" + transaction.getAmount() + " on " + transaction.getDate());
                    }
                    System.out.println();
                    break;
                case 6:
                    atm.logout();
                    System.out.println("\nThank you for using the ATM. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
            }
        }
    }
}
