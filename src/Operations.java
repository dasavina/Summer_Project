import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Operations {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(String name, String author, String description, LocalDate dateOfPublishing) {
        books.add(new Book(name, author, description, dateOfPublishing));
    }

    public ArrayList<Book> search(String parameter, String keywords) {
        ArrayList<Book> found = new ArrayList<>();
        switch (parameter) {
            case "1" -> {
                for (Book book : books) {
                    if (book.getAuthor().contains(keywords)) {
                        found.add(book);
                    }
                }
            }
            case "2" -> {
                for (Book book : books) {
                    if (book.getName().contains(keywords)) {
                        found.add(book);
                    }
                }

            }
            case "3" -> {
                for (Book book : books) {
                    if (book.getDescription().contains(keywords)) {
                        found.add(book);
                    }
                }
            }
            default -> throw new RuntimeException();
        }
        return found;
    }

    public ArrayList<Book> update(Book book, String what, String change) {

        int index = books.indexOf(book);
        switch (what) {
            case "1" -> book.setAuthor(change);
            case "2" -> book.setName(change);
            case "3" -> book.setDescription(change);
            case "4" -> {
                try {
                    Scanner scanner = new Scanner(change).useDelimiter("/");
                    int day = scanner.nextInt();
                    int month = scanner.nextInt();
                    int year = scanner.nextInt();
                    LocalDate newDate = LocalDate.of(year, month, day);
                    if (newDate.isAfter(LocalDate.now())) {
                        throw new RuntimeException("Неправильно введено значення. Спробуйте ще раз\n");
                    } else {
                        book.setDateOfPublishing(newDate);
                    }
                } catch (NumberFormatException | InputMismatchException | DateTimeException e) {
                    throw new RuntimeException("неправильно введено значення");
                }
            }
            default -> throw new RuntimeException("команди не існує");
        }
        books.set(index, book);
        return books;
    }

    public ArrayList<Book> delete(Book book) {
        books.remove(book);
        return books;
    }

    public ArrayList<Book> sort(String parameter) {

        switch (parameter){
            case "1" ->{
                Comparator<Book> byAuthor = Comparator.comparing(Book::getAuthor);
                books.sort(byAuthor);
            }
            case "2" -> {
                Comparator<Book> byName = Comparator.comparing(Book::getName);
                books.sort(byName);
            }
            case "3" -> {
                Comparator<Book> byDate = Comparator.comparing(Book::getDateOfPublishing);
                books.sort(byDate);
            }
            default -> throw new RuntimeException();
        }
        return books;
    }

    public void printLibrary() {
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public void Load() {
        GsonBuilder GB = new GsonBuilder();
        GB.registerTypeAdapter(LocalDate.class, new DataSer());
        GB.registerTypeAdapter(LocalDate.class, new DeSer());

        Path path = Paths.get("src/library.json");
        String str;
        Gson GSer = GB.setPrettyPrinting().create();
        try {
            str = Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        books = GSer.fromJson(str, new TypeToken<>() {
        });
    }

    public void Save() {
        GsonBuilder GB = new GsonBuilder();
        GB.registerTypeAdapter(LocalDate.class, new DataSer());
        GB.registerTypeAdapter(LocalDate.class, new DeSer());

        Path path = Paths.get("src/library.json");
        Gson GSer = GB.setPrettyPrinting().create();
        String data = GSer.toJson(books);
        try {
            Files.writeString(path, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}