import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortByPriorityManager {

    public static void sortByPriority(List<Task> tasks) {
        // Implementacja sortowania zadań wg priorytetu
        Collections.sort(tasks, Comparator.comparing(Task::getPriority, Comparator.nullsLast(String::compareTo)));
    }
}



