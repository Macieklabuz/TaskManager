import java.util.Date;
import java.io.Serializable;

public class Task implements Serializable {
    private String description;
    private Date dueDate;
    private TaskState state;
    private String priority;
    private String recurrencePattern;

    public Task(String description, Date dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.state = new NewTaskState(); // Początkowy stan to "Nowe"
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public void start() {
        state.start(this);
    }

    public void finish() {
        state.finish(this);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRecurrencePattern() {
        return recurrencePattern;
    }

    public void setRecurrencePattern(String recurrencePattern) {
        this.recurrencePattern = recurrencePattern;
    }
}
