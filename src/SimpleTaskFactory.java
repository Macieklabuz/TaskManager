import java.util.Date;

class SimpleTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String description, Date dueDate) {
        return new Task(description, dueDate);
    }

    @Override
    public RecurringTask createRecurringTask(String description, Date dueDate, String recurrencePattern) {
        // Zwracamy zwykłe zadanie, ignorując wzorzec cykliczności
        //System.out.println("Ignorowanie wzorca cykliczności dla SimpleTaskFactory");
        return new RecurringTask(description, dueDate, "");
    }
}
