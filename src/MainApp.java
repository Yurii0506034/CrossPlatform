import java.util.Scanner;
import java.time.LocalDateTime;  // Додайте цей імпорт

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Створити");
            System.out.println("2. Видалити");
            System.out.println("3. Зчитати");
            System.out.println("4. Оновити");
            System.out.println("5. Пошук");
            System.out.println("6. Сортування");
            System.out.println("7. Вихід");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Щоб захопити новий рядок після введення числа

            switch (choice) {
                case 1:
                    createItem(scanner);
                    break;
                case 2:
                    deleteItem(scanner);
                    break;
                case 3:
                    readItems();
                    break;
                case 4:
                    updateItem(scanner);
                    break;
                case 5:
                    searchItems(scanner);
                    break;
                case 6:
                    sortItems();
                    break;
                case 7:
                    System.out.println("Вихід з програми...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір.");
            }
        }
    }

    private static void createItem(Scanner scanner) {
        System.out.println("Введіть назву завдання:");
        String title = scanner.nextLine();
        System.out.println("Введіть опис завдання:");
        String description = scanner.nextLine();
        System.out.println("Введіть дату виконання (yyyy-MM-dd HH:mm):");
        String dueDateString = scanner.nextLine();
        // Створення завдання
        Task task = new Task(title, description, LocalDateTime.parse(dueDateString));  // Використовуємо LocalDateTime
        System.out.println("Завдання створено: " + task);
    }

    private static void deleteItem(Scanner scanner) {
        // Реалізація видалення завдання
        System.out.println("Введіть ID завдання для видалення:");
        int id = scanner.nextInt();
        System.out.println("Завдання з ID " + id + " видалено.");
    }

    private static void readItems() {
        // Реалізація зчитування завдань
        System.out.println("Зчитування всіх завдань...");
    }

    private static void updateItem(Scanner scanner) {
        // Реалізація оновлення завдання
        System.out.println("Введіть ID завдання для оновлення:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Споживаємо новий рядок
        System.out.println("Введіть нову назву завдання:");
        String title = scanner.nextLine();
        System.out.println("Завдання оновлено: " + title);
    }

    private static void searchItems(Scanner scanner) {
        // Реалізація пошуку завдання
        System.out.println("Введіть назву для пошуку:");
        String searchQuery = scanner.nextLine();
        System.out.println("Пошук за запитом: " + searchQuery);
    }

    private static void sortItems() {
        // Реалізація сортування
        System.out.println("Завдання відсортовані.");
    }
}
