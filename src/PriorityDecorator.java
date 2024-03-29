import java.util.Date;

public class PriorityDecorator implements TaskDecorator {
    private Task task;
    private String priority;

    public PriorityDecorator(Task task, String priority) {
        this.task = task;
        this.priority = priority;
    }

    @Override
    public void decorate() {
        // Implementacja dodawania priorytetu
        System.out.println("Dodano priorytet do zadania: " + task.getDescription());
    }

    // Implementacje metod z klasy Task
    public String getDescription() {
        return task.getDescription();
    }

    public Date getDueDate() {
        return task.getDueDate();
    }

    public String getPriority() {
        return priority;
    }
    // ...
}
