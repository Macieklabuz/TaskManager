import java.util.List;

public class FilterByPriorityStrategy implements TaskManagementStrategy {
    @Override
    public void manageTasks(List<Task> tasks) {
        // Implementacja filtrowania zadań wg priorytetu
        tasks.removeIf(task -> !isPriorityTask(task));
        tasks.removeIf(task -> task instanceof RecurringTask);
    }

    private boolean isPriorityTask(Task task) {
        // Zawsze zwraca true, aby uwzględnić wszystkie zadania
        return true;
    }
}

