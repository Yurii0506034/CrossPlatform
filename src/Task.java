import java.time.LocalDate;  // Імпортуємо LocalDate
import java.time.LocalDateTime;

public class Task {
    private static int counter = 1;
    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;  // Замість LocalDateTime використовуємо LocalDate

    public Task(String title, String description, LocalDate dueDate) {
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task(String title, String description, LocalDateTime of) {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public static void setCounter(int counter) {
        Task.counter = counter;
    }
}
