import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByDueDateStrategy implements TaskManagementStrategy {
    @Override
    public void manageTasks(List<Task> tasks) {
        // Implementacja sortowania zadań wg terminu
        Collections.sort(tasks, Comparator.comparing(Task::getDueDate));
    }
}
