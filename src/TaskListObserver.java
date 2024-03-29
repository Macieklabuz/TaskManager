import java.util.List;

public class TaskListObserver implements TaskObserver {
    @Override
    public void updateTasks(List<Task> tasks) {
        // Tutaj można zaimplementować logikę reakcji na zmiany w zadaniach
        System.out.println("Lista zadań została zaktualizowana:");
        for (Task task : tasks) {
            System.out.println("Opis: " + task.getDescription());
            System.out.println("Termin: " + task.getDueDate());
            System.out.println("---------------------");
        }
    }
}
