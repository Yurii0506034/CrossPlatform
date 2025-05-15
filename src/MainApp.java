import java.util.List;
import java.time.LocalDate;  // Імпортуємо LocalDate

public class MainApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        // Створюємо задачі з LocalDate (без часу)
        Task task1 = new Task("Задача 1", "Опис задачі 1", LocalDate.of(2025, 5, 15));  // Створення задачі з конкретною датою
        taskManager.addTask(task1);

        // Пошук задач за датою
        List<Task> tasksOnDate = taskManager.searchByDate(LocalDate.of(2025, 5, 15));  // Пошук задач, які відповідають даті
        tasksOnDate.forEach(task -> System.out.println(task.getTitle()));  // Виведення результатів пошуку
    }
}
