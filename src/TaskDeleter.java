import java.util.List;
import java.util.Scanner;

public class TaskDeleter {
    public static void deleteTask(TaskManager taskManager, Scanner scanner) {
        List<Task> tasks = taskManager.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("Brak zadań do usunięcia.");
        } else {
            System.out.println("Aktualne zadania:");

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(i + ". Opis: " + task.getDescription());
                System.out.println("   Termin: " + task.getDueDate());
                System.out.println("---------------------");
            }

            System.out.println("Podaj numer zadania do usunięcia:");
            int taskIndex = scanner.nextInt();

            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task taskToRemove = tasks.get(taskIndex);
                taskManager.deleteTask(taskToRemove);

                // Dodaj poniższą linię, aby poinformować obserwatorów po usunięciu zadania
                System.out.println("Usunięto zadanie:");
                taskManager.updateTask(taskToRemove);
            }
        }
    }
}
