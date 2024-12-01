import java.util.Scanner;

public class Authentication {
    private User currentUser;

    public boolean authenticate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        if ("user".equals(username) && "pass".equals(password)) {
            currentUser = new User(username, password);
            return true;
        } else {
            System.out.println("Неправильный пароль или логин.");
            return false;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }
}