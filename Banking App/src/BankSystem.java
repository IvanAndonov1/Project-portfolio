import java.util.Scanner;

public class BankSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Bank System");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createAccount();
                    System.out.println("Account created.");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    User user = bank.login(username, password);
                    if (user != null) {
                        userMenu userMenu = new userMenu(user);
                        userMenu.showMenu();
                    } else {
                        System.out.println("Invalid credentials, please try again.");
                    }


                break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        bank.createUser(username, password);
        System.out.println("Account created successfully!");
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = bank.login(username, password);
        if (user != null) {
            userMenu(user);
        } else {
            System.out.println("Invalid credentials, please try again.");
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("Welcome, " + user.getUsername());
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createBankAccount(user);
                    break;
                case 2:
                    viewAccounts(user);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private static void createBankAccount(User user) {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        Account account = new Account(accountNumber);
        user.addAccount(account);
        System.out.println("Bank account created successfully!");
    }

    private static void viewAccounts(User user) {
        for (Account account : user.getAccounts()) {
            System.out.println("Account: " + account.getAccountNumber() + ", Balance: $" + account.getBalance());
        }
    }
}
