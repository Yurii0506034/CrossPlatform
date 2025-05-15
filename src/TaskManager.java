import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

    // 🔽 Додано сортування
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
}
