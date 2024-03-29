import java.util.Date;

public class RecurringTask extends Task {
    private String recurrencePattern;
    private String priority;

    public RecurringTask(String description, Date dueDate, String recurrencePattern) {
        super(description, dueDate);
        this.recurrencePattern = recurrencePattern;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }

    @Override
    public String toString() {
        return super.toString() +
                " RecurringTask{" +
                "recurrencePattern='" + recurrencePattern + '\'' +
                '}';
    }

    public String getPriority() {
        return priority;
    }
}

