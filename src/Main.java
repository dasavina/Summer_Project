import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        do {
            Scanner scanner = new Scanner(System.in);
            String command;

            System.out.println("""
                    ************************************
                    Введіть команду:
                    -- (1) додати книгу до бібліотеки
                    -- (2) редагувати інформацію про книгу
                    -- (3) видалити
                    -- (4) пошук
                    -- (5) показати все
                    -- (6) сортувати
                    -- (0) вийти
                    ************************************""");
            command = scanner.nextLine();
            switch (command) {
                case "1" -> {}
                case "2" -> {}
                case "3" -> {}
                case "4" -> {}
                case "5" -> {}
                case "6" -> {}
                case "0" -> System.exit(0);
                default -> System.out.println("Неправильно введено команду");
            }

        } while (true);
    }
}
