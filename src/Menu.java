import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Authentication authentication;
    private List<Catalog> catalogs;
    private ShoppingCart cart;

    public Menu() {
        this.authentication = new Authentication();
        this.catalogs = new ArrayList<>();
        this.cart = new ShoppingCart();
        initializeCatalogs();
    }

    private void initializeCatalogs() {
        Catalog blocknotes = new Catalog(Category.BLOCKNOTES);
        blocknotes.addProduct(new Product("Большой блокнот", 130));
        blocknotes.addProduct(new Product("Деловой блокнот", 500));
        blocknotes.addProduct(new Product("Сувенирный блокнот", 200));

        Catalog books = new Catalog(Category.BOOKS);
        books.addProduct(new Product("Азбука", 89));
        books.addProduct(new Product("Сказки", 150));
        books.addProduct(new Product("География 5 класс", 340));
        books.addProduct(new Product("Сборник рецепов", 420));

        Catalog pens = new Catalog(Category.PENS);
        pens.addProduct(new Product("Красивое перо", 1800));
        pens.addProduct(new Product("Ручка BIC", 29));

        catalogs.add(blocknotes);
        catalogs.add(books);
        catalogs.add(pens);
    }

    public void start() {
        if (authentication.authenticate()) {
            showMainMenu();
        }
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nГлавнео меню:");
            System.out.println("1. Смотреть каталоги");
            System.out.println("2. Корзина");
            System.out.println("3. Оплата");
            System.out.println("4. Выход");
            System.out.print("Выберите: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    viewCatalogs();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    checkout();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неправильнный ввод.");
            }
        }
    }

    private void viewCatalogs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nКаталоги:");
        for (int i = 0; i < catalogs.size(); i++) {
            System.out.println((i + 1) + ". " + catalogs.get(i));
        }
        System.out.print("Выберите каталог: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice > 0 && choice <= catalogs.size()) {
            viewProducts(catalogs.get(choice - 1));
        } else {
            System.out.println("Неправильный ввод.");
        }
    }

    private void viewProducts(Catalog catalog) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nТовары категории " + catalog.getCategory().getDisplayName() + ":");
        List<Product> products = catalog.getProducts();
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
        System.out.print("Выберите продукт чтобы добавить в корзину (0 назад): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        if (choice > 0 && choice <= products.size()) {
            cart.addProduct(products.get(choice - 1));
            System.out.println(products.get(choice - 1).getName() + " добавлен в корзину.");
        } else if (choice != 0) {
            System.out.println("Неправильный ввод.");
        }
    }

    private void viewCart() {
        System.out.println("\nКорзина:");
        List<Product> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Ваша корзина пуста.");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
            System.out.println("К оплате: ₽" + cart.getTotalPrice());
        }
    }

    private void checkout() {
        if (cart.getItems().isEmpty()) {
            System.out.println("Ваша корзина пуста.");
        } else {
            System.out.println("\nК оплате:");
            viewCart();
            System.out.println("Спасибо за покупку!");
            cart.clear();
        }
    }
}