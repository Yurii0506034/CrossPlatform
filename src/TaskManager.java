import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;  // Імпортуємо LocalDate
import java.util.*;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "tasks.json";
    private final Gson gson = new Gson();

    public TaskManager() {
        loadFromFile();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveToFile();
    }

    public boolean deleteTask(int id) {
        boolean removed = tasks.removeIf(task -> task.getId() == id);
        if (removed) saveToFile();
        return removed;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(int id) {
        return tasks.stream().filter(task -> task.getId() == id).findFirst().orElse(null);
    }

    public boolean updateTask(int id, String newTitle, String newDescription) {
        Task task = getTaskById(id);
        if (task != null) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<Task> getTasksSortedByDate() {
        return tasks.stream().sorted(Comparator.comparing(Task::getDueDate)).toList();
    }

    public List<Task> getTasksSortedByTitle() {
        return tasks.stream().sorted(Comparator.comparing(Task::getTitle)).toList();
    }

    public List<Task> searchByTitle(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    public List<Task> searchByDate(LocalDate date) {
        return tasks.stream()
                .filter(task -> task.getDueDate().equals(date))  // Порівнюємо лише дату
                .toList();
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.out.println("Помилка при збереженні у файл: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type taskListType = new TypeToken<List<Task>>() {}.getType();
            List<Task> loadedTasks = gson.fromJson(reader, taskListType);
            if (loadedTasks != null) {
                tasks.clear();
                tasks.addAll(loadedTasks);
                // Відновлюємо лічильник ID
                int maxId = tasks.stream().mapToInt(Task::getId).max().orElse(0);
                Task.setCounter(maxId + 1);
            }
        } catch (IOException e) {
            System.out.println("Файл не знайдено або порожній. Створюємо новий список.");
        }
    }
}
