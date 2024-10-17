import java.util.List;
import java.util.Scanner;

public class userMenu {
    private Scanner scanner = new Scanner(System.in);
    private User user;

    public userMenu(User user) {
        this.user = user;
    }

    public void showMenu() {
        while (true) {
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. View Account Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. View Transaction History");
            System.out.println("6. Change Password");
            System.out.println("7. Create Bank Account");
            System.out.println("8. View Accounts");
            System.out.println("9. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAccountBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    transferMoney();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    changePassword();
                    break;
                case 7:
                    createBankAccount();
                    break;
                case 8:
                    viewAccounts();
                    break;
                case 9:
                    return; // Logout
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewAccountBalance() {
        for (Account account : user.getAccounts()) {
            System.out.println("Account: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
    }

    private void depositMoney() {
        System.out.print("Enter account number to deposit into: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.deposit(amount);
            System.out.println("Deposited $" + amount + " to account " + accountNumber);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter account number to withdraw from: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (account.withdraw(amount)) {
                System.out.println("Withdrew $" + amount + " from account " + accountNumber);
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transferMoney() {
        System.out.print("Enter your account number: ");
        String fromAccountNumber = scanner.nextLine();
        Account fromAccount = findAccount(fromAccountNumber);
        if (fromAccount != null) {
            System.out.print("Enter the account number to transfer to: ");
            String toAccountNumber = scanner.nextLine();
            Account toAccount = findAccount(toAccountNumber);
            if (toAccount != null) {
                System.out.print("Enter amount to transfer: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                if (fromAccount.transfer(toAccount, amount)) {
                    System.out.println("Transferred $" + amount + " from " + fromAccountNumber + " to " + toAccountNumber);
                } else {
                    System.out.println("Transfer failed. Check funds and try again.");
                }
            } else {
                System.out.println("Recipient account not found.");
            }
        } else {
            System.out.println("Your account not found.");
        }
    }

    private void viewTransactionHistory() {
        System.out.print("Enter account number to view transaction history: ");
        String accountNumber = scanner.nextLine();
        Account account = findAccount(accountNumber);
        if (account != null) {
            List<Transaction> transactions = account.getTransactions();
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                for (Transaction transaction : transactions) {
                    System.out.println("Type: " + transaction.getType() + ", Amount: $" + transaction.getAmount() + ", Date: " + transaction.getTimestamp());
                }
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    private void changePassword() {
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        user.setPassword(newPassword);
        System.out.println("Password changed successfully!");
    }

    private void createBankAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = new Account(accountNumber);
        user.addAccount(account);
        System.out.println("Bank account created successfully!");
    }

    private void viewAccounts() {
        List<Account> accounts = user.getAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                System.out.println("Account: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
            }
        }
    }

    private Account findAccount(String accountNumber) {
        for (Account account : user.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
