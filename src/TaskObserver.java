import java.util.List;

public interface TaskObserver {
    void updateTasks(List<Task> tasks);
}