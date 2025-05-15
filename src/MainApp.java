import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Створити завдання");
            System.out.println("2. Видалити завдання");
            System.out.println("3. Зчитати всі завдання");
            System.out.println("4. Оновити завдання");
            System.out.println("5. Повторити вивід завдань");
            System.out.println("6. Сортувати за датою");
            System.out.println("7. Сортувати за назвою");
            System.out.println("8. Пошук за назвою");
            System.out.println("9. Пошук за датою");
            System.out.println("10. Вихід");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення вводу

            switch (choice) {
                case 1 -> createTask();
                case 2 -> deleteTask();
                case 3, 5 -> readTasks();
                case 4 -> updateTask();
                case 6 -> sortByDate();
                case 7 -> sortByTitle();
                case 8 -> searchByTitle();
                case 9 -> searchByDate();
                case 10 -> {
                    System.out.println("Вихід...");
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private static void createTask() {
        System.out.print("Назва: ");
        String title = scanner.nextLine();

        System.out.print("Опис: ");
        String description = scanner.nextLine();

        System.out.print("Дата та час (yyyy-MM-dd HH:mm): ");
        String dateStr = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            Task task = new Task(title, description, dateTime);
            taskManager.addTask(task);
            System.out.println("Завдання створено!");
        } catch (Exception e) {
            System.out.println("Невірний формат дати.");
        }
    }

    private static void deleteTask() {
        System.out.print("ID завдання для видалення: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = taskManager.deleteTask(id);
        if (deleted) {
            System.out.println("Завдання видалено.");
        } else {
            System.out.println("Завдання з таким ID не знайдено.");
        }
    }

    private static void readTasks() {
        var tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Список порожній.");
        } else {
            System.out.println("Список завдань:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private static void updateTask() {
        System.out.print("ID завдання для оновлення: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Нова назва: ");
        String newTitle = scanner.nextLine();

        System.out.print("Новий опис: ");
        String newDesc = scanner.nextLine();

        boolean updated = taskManager.updateTask(id, newTitle, newDesc);
        if (updated) {
            System.out.println("Завдання оновлено.");
        } else {
            System.out.println("Завдання не знайдено.");
        }
    }

    private static void sortByDate() {
        System.out.println("Сортування за датою:");
        var sortedTasks = taskManager.getTasksSortedByDate();
        for (Task task : sortedTasks) {
            System.out.println(task);
        }
    }

    private static void sortByTitle() {
        System.out.println("Сортування за назвою:");
        var sortedTasks = taskManager.getTasksSortedByTitle();
        for (Task task : sortedTasks) {
            System.out.println(task);
        }
    }

    private static void searchByTitle() {
        System.out.print("Введіть слово для пошуку в назві: ");
        String keyword = scanner.nextLine();

        var results = taskManager.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("Нічого не знайдено.");
        } else {
            System.out.println("Результати пошуку:");
            results.forEach(System.out::println);
        }
    }

    private static void searchByDate() {
        System.out.print("Введіть дату для пошуку (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();

        try {
            var date = LocalDate.parse(dateStr);
            var results = taskManager.searchByDate(date);
            if (results.isEmpty()) {
                System.out.println("Завдань на цю дату не знайдено.");
            } else {
                System.out.println("Знайдені завдання:");
                results.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Невірний формат дати.");
        }
    }
}
