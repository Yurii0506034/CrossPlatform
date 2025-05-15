import java.time.LocalDateTime;

public class MainAppTest {
    public static void main(String[] args) {
        Task task1 = new Task("Завдання 1", "Опис 1", LocalDateTime.of(2025, 5, 15, 12, 0));
        Task task2 = new Task("Завдання 2", "Опис 2", LocalDateTime.of(2025, 5, 16, 14, 30));

        System.out.println(task1);
        System.out.println(task2);
    }
}
