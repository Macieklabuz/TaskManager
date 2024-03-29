public class CompletedTaskState implements TaskState {
    @Override
    public void start(Task task) {
        System.out.println("Nie można rozpocząć zadania, które już zostało zakończone.");
    }

    @Override
    public void finish(Task task) {
        System.out.println("Zadanie jest już zakończone.");
    }
}
