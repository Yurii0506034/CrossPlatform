import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public boolean updateTask(int id, String newTitle, String newDescription) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            return true;
        }
        return false;
    }

    public List<Task> getTasksSortedByDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .toList();
    }

    public List<Task> getTasksSortedByTitle() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .toList();
    }

    // 🔍 Пошук за назвою
    public List<Task> searchByTitle(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    // 🔍 Пошук за датою (без часу)
    public List<Task> searchByDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.getDueDate().toLocalDate().equals(date))
                .toList();
    }
}
