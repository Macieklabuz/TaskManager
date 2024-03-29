public class InProgressTaskState implements TaskState {
    @Override
    public void start(Task task) {
        System.out.println("Zadanie jest już w trakcie.");
    }

    @Override
    public void finish(Task task) {
        System.out.println("Zakończono zadanie: " + task.getDescription());
        // Zmiana stanu zadania na "Zakończone"
        task.setState(new CompletedTaskState());
    }
}
