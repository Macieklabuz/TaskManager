import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class TaskManager {
    private static TaskManager instance;
    List<Task> tasks;
    private List<TaskObserver> observers = new ArrayList<>();
    private TaskFactory taskFactory;
    private List<Task> archivedTasks;

    private TaskManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
        taskFactory = new SimpleTaskFactory(); // Domyślnie używana jest fabryka prostych zadań
        archivedTasks = new ArrayList<>();

    }

    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
        }
        return instance;
    }

    public void setTaskFactory(TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.updateTasks(tasks);
        }
    }


    public void addTask(String description, Date dueDate) {
        Task newTask = taskFactory.createTask(description, dueDate);
        tasks.add(newTask);
        notifyObservers();
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers();
    }

    public void deleteTask(Task task) {
        Task taskMemento = task;
        tasks.remove(task);
        archivedTasks.add(taskMemento);
        notifyObservers();
    }


    public void addRecurringTask(String description, Date dueDate, String recurrencePattern) {
        RecurringTask newRecurringTask = taskFactory.createRecurringTask(description, dueDate, recurrencePattern);
        tasks.add(newRecurringTask);
        notifyObservers();
    }


    public void removeCompletedTasks() {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getState() instanceof CompletedTaskState) {
                iterator.remove();
            }
        }
    }

    public void updateTask(Task task) {
        // Logika aktualizacji zadania
        notifyObservers();
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public List<Task> getArchivedTasks() {
        return archivedTasks;
    }

    public void saveTasksToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
            System.out.println("Zadania zostały zapisane do pliku: " + filename);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisywania zadań do pliku: " + e.getMessage());
        }
    }

    public void loadTasksFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
            System.out.println("Zadania zostały wczytane z pliku: " + filename);
            notifyObservers();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Błąd podczas wczytywania zadań z pliku: " + e.getMessage());
        }
    }
}
