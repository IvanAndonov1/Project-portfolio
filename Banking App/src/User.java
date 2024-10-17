import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private List<Account> accounts;

    private List<Account> transactions;



    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }



}
