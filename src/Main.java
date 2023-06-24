import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        do {
            Scanner scanner = new Scanner(System.in);
            String command;

            System.out.println("""
                    ************************************
                    Введіть команду:
                    -- + (1) додати книгу до бібліотеки
                    -- + (2) редагувати інформацію про книгу
                    -- + (3) видалити
                    -- + (4) пошук
                    -- + (5) показати все
                    -- + (6) сортувати
                    -- + (0) вийти
                    ************************************""");
            command = scanner.nextLine();
            switch (command) {
                case "1" -> {
                    boolean OkNum = false;
                    Scanner scan = new Scanner(System.in);

                    System.out.println("Введіть назву книги:\n");
                    String name  = scan.nextLine();
                    System.out.println("Введіть автора:\n");
                    String author = scan.nextLine();
                    System.out.println("Введіть опис книги:\n");
                    String description = scan.nextLine();
                    System.out.println("Введіть рік видання");
                    int year;

                    while (!OkNum) {
                        year = getInt();
                        OkNum = true;
                        if ((year < 0)||(year > LocalDate.now().getYear())) {
                            System.out.println("Неправильно введено значення. Спробуйте ще раз\n");
                            OkNum = false;
                        }
                    }

                    //addition operation

                }

                case "2" -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Пошук книги для редагування");
                    System.out.println("Пошук книги за автором (1) чи за назвою (2) ?");
                    String parameter = scan.nextLine();
                    System.out.println("Введіть запит");
                    String keywords = scan.nextLine();
                    //searching operation
                    ArrayList<Book> foundBooks = new ArrayList<>();
                    //get results from the operation in list
                    boolean checkCommand = false;
                    Book toEdit;
                    boolean exit = false;
                    for (Book book: foundBooks)
                    {
                        if (!exit) {
                            System.out.println(book.toString());
                            while (!checkCommand) {
                                System.out.println("Редагувати цю книгу? так(1)/ні(2)/вийти з редагування(0)");
                                switch (scan.nextLine()) {
                                    case "1" -> {
                                        Scanner scannerForEdition = new Scanner(System.in);
                                        checkCommand = true;
                                        toEdit = book;
                                        System.out.println("Введіть що потрібно змінити: автор(1), назва(2), опис(3), рік видання(4)");
                                        String what = scannerForEdition.nextLine();
                                        System.out.println("Введіть оновлену інформацію");
                                        String changes = scannerForEdition.nextLine();
                                        //update operation

                                    }
                                    case "2" -> checkCommand = true;
                                    case "0" -> exit = true;
                                    default -> System.out.println("неправильна команда");
                                }
                            }
                        }
                        else break;
                    }

                }

                case "3" -> {

                    Scanner scan = new Scanner(System.in);
                    System.out.println("Пошук книги для видалення");
                    System.out.println("Пошук книги за автором (1) чи за назвою (2) ?");
                    String parameter = scan.nextLine();
                    System.out.println("Введіть запит");
                    String keywords = scan.nextLine();
                    //searching operation
                    ArrayList<Book> foundBooks = new ArrayList<>();
                    //get results from the operation in list
                    boolean checkCommand = false;
                    Book toDelete;
                    boolean exit = false;
                    for (Book book: foundBooks)
                    {
                        if (!exit) {
                            System.out.println(book.toString());
                            while (!checkCommand) {
                                Scanner scannerForDeletion = new Scanner(System.in);
                                System.out.println("видалити цю книгу? так(1)/ні(2)/вийти з видалення(0)");
                                switch (scannerForDeletion.nextLine()) {
                                    case "1" -> {
                                        checkCommand = true;
                                        toDelete = book;
                                        //delete operation

                                    }
                                    case "2" -> checkCommand = true;
                                    case "0" -> exit = true;
                                    default -> System.out.println("неправильна команда");
                                }
                            }
                        }
                        else break;
                    }
                }

                case "4" -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("Пошук книги за автором (1) чи за назвою (2) ?");
                    String parameter = scan.nextLine();
                    System.out.println("Введіть запит");
                    String keywords = scan.nextLine();
                    //searching operation
                }

                case "5" -> {
                    //showAll operation
                }

                case "6" -> {
                    Scanner scan = new Scanner(System.in);
                    System.out.println("За чим сортувати книги? автор(1)/назва(2)/рік видання(3)");
                    String how = scan.nextLine();
                    //sorting operation
                }

                case "0" -> System.exit(0);

                default -> System.out.println("Неправильно введено команду");
            }

        } while (true);
    }

    public static int getInt() {
        int N = 0;
        boolean checkE = false;
        while (!checkE) {
            Scanner scanner = new Scanner(System.in);
            try {
                N = scanner.nextInt();
                checkE = true;
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Неправильне значення");
                checkE = false;
            }
        }
        return N;
    }


}
