import java.util.Date;

public class RecurringTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description, Date dueDate) {
        // Domyślnie ustawia puste wzorce cykliczności
        return new RecurringTask(description, dueDate, "");
    }

    @Override
    public RecurringTask createRecurringTask(String description, Date dueDate, String recurrencePattern) {
        RecurringTask recurringTask = new RecurringTask(description, dueDate, recurrencePattern);
        // Ustawienie wzorca cykliczności w klasie bazowej Task
        recurringTask.setRecurrencePattern(recurrencePattern);
        return recurringTask;
    }
}

