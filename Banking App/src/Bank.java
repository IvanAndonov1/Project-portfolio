import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, User> users;

    public Bank() {
        users = new HashMap<>();
    }

    public void createUser(String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    public void listUsers() {
        for (String username : users.keySet()) {
            System.out.println(username);
        }
    }
}
