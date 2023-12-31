import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Book {
    private String name;
    private String author;
    private String description;
    private LocalDate dateOfPublishing; //change to date of publishing

    public Book(String name, String author, String description, LocalDate dateOfPublishing) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.dateOfPublishing = dateOfPublishing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfPublishing() {
        return dateOfPublishing;
    }

    public void setDateOfPublishing(LocalDate dateOfPublishing) {
        this.dateOfPublishing = dateOfPublishing;
    }

    @Override
    public String toString() {
        return (
                "************************************" +
                        "\nНазва: " + getName() +
                        "\nАвтор: " + getAuthor() +
                        "\nОпис: " + getDescription() +
                        "\nРік видання: " + getDateOfPublishing().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        "\n************************************\n"
        );
    }
}
