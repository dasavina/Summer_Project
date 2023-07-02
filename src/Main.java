import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Operations act = new Operations();
        act.Load();
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
                    -- (7) про додаток
                    -- (0) вийти
                    ************************************""");
            command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    boolean OkNum = false;
                    Scanner scan = new Scanner(System.in);

                    System.out.println("Введіть назву книги:\n");
                    String name = scan.nextLine();
                    System.out.println("Введіть автора:\n");
                    String author = scan.nextLine();
                    System.out.println("Введіть опис книги:\n");
                    String description = scan.nextLine();
                    System.out.println("Введіть дату видання dd/MM/yyyy");
                    LocalDate dateOfPublishing = null;

                    while (!OkNum) {
                        try {
                            String date = scan.nextLine();
                            Scanner forDate = new Scanner(date).useDelimiter("/");
                            int day = forDate.nextInt();
                            int month = forDate.nextInt();
                            int year = forDate.nextInt();
                            dateOfPublishing = LocalDate.of(year, month, day);
                            OkNum = true;
                            if (dateOfPublishing.isAfter(LocalDate.now())) {
                                System.out.println("Неправильно введено значення. Спробуйте ще раз\n");
                                OkNum = false;
                            }
                        } catch (DateTimeException | NumberFormatException e) {
                            System.out.println("Неправильно введено значення! Спробуйте ще раз\n");
                        }
                    }

                    act.addBook(name, author, description, dateOfPublishing);

                }

                case "2" -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Пошук книги для редагування");
                    ArrayList<Book> foundBooks;
                    try {
                        System.out.println("Пошук книги за автором (1), за назвою (2) чи за описом(3) ?");
                        String parameter = scan.nextLine();
                        System.out.println("Введіть запит");
                        String keywords = scan.nextLine();
                        foundBooks = act.search(parameter, keywords);


                        Book toEdit;
                        boolean exit = false;
                        for (Book book : foundBooks) {
                            if (!exit) {
                                System.out.println(book.toString());
                                boolean checkCommand = false;
                                while (!checkCommand) {
                                    System.out.println("Редагувати цю книгу? так(1)/ні(2)/вийти з редагування(0)");
                                    switch (scan.nextLine()) {
                                        case "1" -> {
                                            boolean checkInfo = false;
                                            while (!checkInfo) {
                                                try {
                                                    Scanner scannerForEdition = new Scanner(System.in);
                                                    checkCommand = true;
                                                    toEdit = book;
                                                    System.out.println("Введіть що потрібно змінити: автор(1), назва(2), опис(3), дату видання(4)");
                                                    String what = scannerForEdition.nextLine();
                                                    System.out.println("Введіть оновлену інформацію");
                                                    String changes = scannerForEdition.nextLine();
                                                    checkInfo = true;
                                                    act.update(toEdit, what, changes);
                                                } catch (RuntimeException e) {
                                                    checkInfo = false;
                                                }
                                            }

                                        }
                                        case "2" -> checkCommand = true;
                                        case "0" -> exit = true;
                                        default -> System.out.println("неправильна команда");
                                    }
                                }
                            } else break;
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Неправильно заданий параметр або запит");
                    }

                }

                case "3" -> {

                    Scanner scan = new Scanner(System.in);
                    System.out.println("Пошук книги для видалення");
                    System.out.println("Пошук книги за автором (1), за назвою (2) чи за описом(3) ?");
                    String parameter = scan.nextLine();
                    System.out.println("Введіть запит");
                    String keywords = scan.nextLine();
                    //searching operation
                    ArrayList<Book> foundBooks = act.search(parameter, keywords);
                    //get results from the operation in list
                    boolean checkCommand = false;
                    Book toDelete;
                    boolean exit = false;
                    for (Book book : foundBooks) {
                        if (!exit) {
                            System.out.println(book.toString());
                            while (!checkCommand) {
                                Scanner scannerForDeletion = new Scanner(System.in);
                                System.out.println("видалити цю книгу? так(1)/ні(2)/вийти з видалення(0)");
                                switch (scannerForDeletion.nextLine()) {
                                    case "1" -> {
                                        checkCommand = true;
                                        toDelete = book;
                                        act.delete(toDelete);

                                    }
                                    case "2" -> checkCommand = true;
                                    case "0" -> exit = true;
                                    default -> System.out.println("неправильна команда");
                                }
                            }
                        } else break;
                    }
                }

                case "4" -> {
                    Scanner scan = new Scanner(System.in);
                    try {
                        System.out.println("Пошук книги за автором (1), за назвою (2) чи за описом(3) ?");
                        String parameter = scan.nextLine();
                        System.out.println("Введіть запит");
                        String keywords = scan.nextLine();
                        ArrayList<Book> foundBooks = act.search(parameter, keywords);
                        for (Book book : foundBooks) {
                            System.out.println(book.toString());
                        }
                    } catch (RuntimeException e) {
                        System.out.println("команди не існує");
                    }
                }

                case "5" -> act.printLibrary();

                case "6" -> {
                    Scanner scan = new Scanner(System.in);
                    try {
                        System.out.println("За чим сортувати книги? автор(1)/назва(2)/дата видання(3)");
                        String how = scan.nextLine();
                        act.sort(how);
                    } catch (RuntimeException e) {
                        System.out.println("команди не існує");
                    }
                    act.printLibrary();
                }

                case "7" -> System.out.println("""
                Консольний додаток призначений для обліку книг бібліотеки користувача.
                Існують наступні команди:
                1)	Додати книгу.\s
                Необхідно ввести інформацію про книгу по запропонованим полям, книгу буде збережено у список
                2)	редагувати інформацію про книгу
                Спочатку пропонується знайти книгу, яку необхідно редагувати. Потім оберіть, що саме ви бажаєте редагувати та введіть нову інформацію. Попередні дані буде замінено на введені
                3)	видалити
                Спочатку пропонується знайти книгу, яку необхідно видалити, після підтвердження операції книгу буде остаточно видалено зі списку
                4)	пошук
                Пошук книги може бути здійснено за кількома параметрами. Оберіть параметр, введіть запит та перегляньте результати
                5)	показати все
                Виводить весь список на екран
                6)	сортувати
                Сортування проводиться за різними критеріями. Оберіть, за чим сортувати список та перегляньте результат
                7)	вийти
                Програма завершує роботу тільки після введення даної команди, зберігши всі зміни
                Збереження відбувається автоматично після кожної виконаної команди. Завантаження даних з файлу збереження відбувається автоматично з початком роботи програми
                                
                """);

                case "0" -> {
                    act.Save();
                    System.exit(0);
                }

                default -> System.out.println("Неправильно введено команду");
            }

            act.Save();

        } while (true);
    }
}
