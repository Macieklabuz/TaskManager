import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TaskManagerCLI {
    public static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();
        taskManager.setTaskFactory(new SimpleTaskFactory());
        TaskListObserver taskListObserver = new TaskListObserver();
        taskManager.addObserver(taskListObserver);

        // Dodaj przykładowe jednorazowe zadania
        taskManager.addTask("Wyjscie do sklepu", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)); // za 1 dzień
        taskManager.addTask("Dokonczenie projektu", new Date(System.currentTimeMillis() + 1000 * 32 * 60 * 48)); // za 2 dni
        taskManager.addTask("Kolokwium", new Date(System.currentTimeMillis() + 1000 * 16 * 60 * 72)); // za 3 dni
        taskManager.addTask("Spotkanie z klientem", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)); // za 1 dzień
        taskManager.addTask("Przygotowanie raportu", new Date(System.currentTimeMillis() + 1000 * 34 * 60 * 48)); // za 2 dni
        taskManager.addTask("Prezentacja projektu", new Date(System.currentTimeMillis() + 1000 * 10 * 60 * 72)); // za 3 dni


        // Dodaj przykładowe cykliczne zadania
        taskManager.addRecurringTask("Wyjscie z psem", new Date(System.currentTimeMillis() + 1000 * 45 * 60 * 24), "codziennie"); // za 1 dzień
        taskManager.addRecurringTask("Czytanie ksiazki", new Date(System.currentTimeMillis() + 1000 * 50 * 60 * 168), "co tydzień"); // za 1 tydzień
        taskManager.addRecurringTask("Trening", new Date(System.currentTimeMillis() + 1000 * 40 * 60 * 720), "co miesiąc"); // za 1 miesiąc

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=============================================");
            System.out.println("1. Dodaj nowe zadanie");
            System.out.println("2. Pokaż zadania");
            System.out.println("3. Usuń zadanie");
            System.out.println("4. Edytuj zadanie");
            System.out.println("5. Dodaj priorytet do tasku");
            //System.out.println(". Wyświetl drzewo zadań");
            System.out.println("6. Sortuj po dacie");
            System.out.println("7. Sortuj po priorytecie");
            System.out.println("8. Zarządzaj zadaniami (start/zakończ)");
            System.out.println("9. Usun zakonczone zadania");
            System.out.println("10. Zapisz zadania");
            System.out.println("11. Wczytaj zadania");
            System.out.println("12. Zakończ");
            System.out.print("Wybierz opcję: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addNewTaskFromCLI(taskManager, scanner);
                    break;
                case 2:
                    showTasks(taskManager);
                    break;
                case 3:
                    TaskDeleter.deleteTask(taskManager, scanner);
                    break;
                case 4:
                    TaskEditor.editTask(taskManager, scanner);
                    break;
                case 5:
                    addPriorityToTask(taskManager, scanner);
                    break;
                case 6:
                    sortByDueDate(taskManager);
                    break;
                case 7:
                    filterByPriority(taskManager);
                    break;
                case 8:
                    manageTask(taskManager, scanner);
                    break;
                case 9:
                    removeCompletedTasks(taskManager);
                    break;
                case 10:
                    saveTasksToFile(taskManager, scanner);
                    break;
                case 11:
                    loadTasksFromFile(taskManager, scanner);
                    break;
                case 12:
                    System.out.println("Koniec programu.");
                    System.exit(0);
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
    private static void addNewTaskFromCLI(TaskManager taskManager, Scanner scanner) {
        System.out.println("Wybierz typ zadania:");
        System.out.println("1. Proste zadanie");
        System.out.println("2. Cykliczne zadanie");
        System.out.print("Wybierz opcję: ");

        int taskTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Oczyszczamy bufor

        switch (taskTypeChoice) {
            case 1:
                addSimpleTaskFromCLI(taskManager, scanner);
                break;
            case 2:
                addRecurringTaskFromCLI(taskManager, scanner);
                break;
            default:
                System.out.println("Nieprawidłowy wybór typu zadania. Spróbuj ponownie.");
        }
    }
    private static void addSimpleTaskFromCLI(TaskManager taskManager, Scanner scanner) {
        System.out.println("Podaj opis zadania:");
        String description = scanner.nextLine();

        System.out.println("Podaj termin zadania (format: dd-MM-yyyy HH:mm):");
        String dateString = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date dueDate = dateFormat.parse(dateString);
            taskManager.addTask(description, dueDate);
            System.out.println("Nowe proste zadanie dodane pomyślnie.");
        } catch (ParseException e) {
            System.out.println("Błąd parsowania daty. Spróbuj ponownie.");
        }
    }
    private static void addPriorityToTask(TaskManager taskManager, Scanner scanner) {
        List<Task> tasks = taskManager.getTasks();

        if (!tasks.isEmpty()) {
            System.out.println("Wybierz zadanie do udekorowania priorytetem:");
            showTasks(taskManager);
            int taskIndex = scanner.nextInt();

            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task selectedTask = tasks.get(taskIndex);

                System.out.println("Podaj priorytet dla zadania:");
                scanner.nextLine(); // Oczyszczamy bufor
                String priority = scanner.nextLine();

                // Dodawanie priorytetu do wybranego zadania
                selectedTask.setPriority(priority);
                System.out.println("Priorytet '" + priority + "' dodany do zadania: " + selectedTask.getDescription());
            }
        } else {
            System.out.println("Brak zadań do udekorowania priorytetem.");
        }
    }
    private static void manageTasksWithStrategy(TaskManager taskManager, TaskManagementStrategy strategy) {
        List<Task> tasks = taskManager.getTasks();
        strategy.manageTasks(tasks);
        showTasks(taskManager);
    }


    private static void removeCompletedTasks(TaskManager taskManager) {
        taskManager.removeCompletedTasks();
        System.out.println("Usunięto zakończone zadania.");
    }

    private static void manageTask(TaskManager taskManager, Scanner scanner) {
        List<Task> tasks = taskManager.getTasks();

        if (!tasks.isEmpty()) {
            System.out.println("Wybierz zadanie do zarządzania:");
            showTasks(taskManager);
            int taskIndex = scanner.nextInt();

            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task selectedTask = tasks.get(taskIndex);

                System.out.println("1. Rozpocznij zadanie");
                System.out.println("2. Zakończ zadanie");
                System.out.print("Wybierz opcję: ");
                int taskAction = scanner.nextInt();

                switch (taskAction) {
                    case 1:
                        selectedTask.start();
                        System.out.println("Zadanie rozpoczęte.");
                        break;
                    case 2:
                        selectedTask.finish();
                        System.out.println("Zadanie zakończone.");
                        break;
                    default:
                        System.out.println("Nieprawidłowy wybór.");
                }
            }
        } else {
            System.out.println("Brak zadań do zarządzania.");
        }
    }
    // Przykład użycia strategii sortowania:
    private static void sortByDueDate(TaskManager taskManager) {
        manageTasksWithStrategy(taskManager, new SortByDueDateStrategy());
    }
    // Przykład użycia strategii filtrowania:
    private static void filterByPriority(TaskManager taskManager) {
        SortByPriorityManager.sortByPriority(taskManager.getTasks());
        showTasks(taskManager);
    }
    private static void addRecurringTaskFromCLI(TaskManager taskManager, Scanner scanner) {
        System.out.println("Podaj opis cyklicznego zadania:");
        String description = scanner.nextLine();

        System.out.println("Podaj termin cyklicznego zadania (format: dd-MM-yyyy HH:mm):");
        String dateString = scanner.nextLine();

        System.out.println("Podaj wzorzec cykliczności (np. 'codziennie', 'co tydzień', 'co miesiąc'):");
        String recurrencePattern = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        try {
            Date dueDate = dateFormat.parse(dateString);
            taskManager.addRecurringTask(description, dueDate, recurrencePattern);
            System.out.println("Nowe cykliczne zadanie dodane pomyślnie.");
        } catch (ParseException e) {
            System.out.println("Błąd parsowania daty. Spróbuj ponownie.");
        }
    }

        private static void showTasks(TaskManager taskManager) {
            List<Task> tasks = taskManager.getTasks();

            if (tasks.isEmpty()) {
                System.out.println("Brak zadań do wyświetlenia.");
            } else {
                System.out.println("Aktualne zadania:");

                for (Task task : tasks) {
                    System.out.println("Opis: " + task.getDescription());
                    System.out.println("Termin: " + task.getDueDate());

                    if (task instanceof RecurringTask) {
                        RecurringTask recurringTask = (RecurringTask) task;
                        System.out.println("Rodzaj zadania: Cykliczne");
                        System.out.println("Wzorzec cykliczności: codziennie" + recurringTask.getRecurrencePattern());
                        // System.out.println(recurringTask.toString());
                    } else {
                        System.out.println("Rodzaj zadania: Jednorazowe");
                    }

                    if (task.getPriority() != null) {
                        System.out.println("Priorytet: " + task.getPriority());
                    }

                    System.out.println("---------------------");
                }
            }
        }
    private static void saveTasksToFile(TaskManager taskManager, Scanner scanner) {
        System.out.println("Podaj nazwę pliku do zapisu:");
        String filename = scanner.next();
        taskManager.saveTasksToFile(filename);
    }

    private static void loadTasksFromFile(TaskManager taskManager, Scanner scanner) {
        System.out.println("Podaj nazwę pliku do wczytania:");
        String filename = scanner.next();
        taskManager.loadTasksFromFile(filename);
    }
    }
