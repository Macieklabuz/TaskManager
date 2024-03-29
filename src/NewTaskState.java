import java.io.Serializable;
public class NewTaskState implements TaskState, Serializable {
    @Override
    public void start(Task task) {
        System.out.println("Rozpoczęto zadanie: " + task.getDescription());
        // Zmiana stanu zadania na "W trakcie"
        task.setState(new InProgressTaskState());
    }

    @Override
    public void finish(Task task) {
        System.out.println("Nie można zakończyć zadania, które nie zostało jeszcze rozpoczęte.");
    }
}
