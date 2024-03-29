import java.util.Date;

public interface TaskFactory {
    Task createTask(String description, Date dueDate);

    RecurringTask createRecurringTask(String description, Date dueDate, String recurrencePattern);
}
