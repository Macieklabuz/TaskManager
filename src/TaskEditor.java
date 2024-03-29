import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskEditor {
    public static void editTask(TaskManager taskManager, Scanner scanner) {
        List<Task> tasks = taskManager.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("Brak zadań do edycji.");
        } else {
            System.out.println("Aktualne zadania:");

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println(i + ". Opis: " + task.getDescription());
                System.out.println("   Termin: " + task.getDueDate());
                System.out.println("---------------------");
            }

            System.out.println("Podaj numer zadania do edycji:");
            int taskIndex = scanner.nextInt();

            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task taskToEdit = tasks.get(taskIndex);

                System.out.println("Edycja zadania o indeksie " + taskIndex);
                System.out.println("1. Zmień opis zadania");
                System.out.println("2. Zmień termin zadania");

                int editChoice = scanner.nextInt();

                switch (editChoice) {
                    case 1:
                        System.out.println("Nowy opis zadania:");
                        scanner.nextLine(); // Oczyszczamy bufor
                        String newDescription = scanner.nextLine();

                        if (taskToEdit instanceof RecurringTask) {
                            ((RecurringTask) taskToEdit).setDescription(newDescription);
                        } else {
                            taskToEdit.setDescription(newDescription);
                        }

                        System.out.println("Opis zadania zmieniony pomyślnie.");
                        break;
                    case 2:
                        System.out.println("Nowy termin zadania (format: dd-MM-yyyy HH:mm):");
                        scanner.nextLine(); // Oczyszczamy bufor
                        String newDateString = scanner.nextLine();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                        try {
                            Date newDueDate = dateFormat.parse(newDateString);
                            taskToEdit.setDueDate(newDueDate);
                            System.out.println("Termin zadania zmieniony pomyślnie.");
                        } catch (ParseException e) {
                            System.out.println("Błąd parsowania daty. Anulowano edycję terminu zadania.");
                        }
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór. Anulowano edycję.");
                }

                // Dodaj poniższe linie, aby poinformować obserwatorów po edycji zadania
                System.out.println("Zaktualizowano zadanie:");
                taskManager.updateTask(taskToEdit);
            } else {
                System.out.println("Nieprawidłowy numer zadania. Anulowano edycję.");
            }
        }
    }
}
