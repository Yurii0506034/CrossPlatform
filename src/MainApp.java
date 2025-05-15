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
            System.out.println("5. Вихід");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка

            switch (choice) {
                case 1 -> createTask();
                case 2 -> deleteTask();
                case 3 -> readTasks();
                case 4 -> updateTask();
                case 5 -> {
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
}
