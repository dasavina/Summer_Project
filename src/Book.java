public class Book {
    private String name;
    private String author;
    private String description;
    private int yearOfPublishing;

    public Book(String name, String author, String description, int yearOfPublishing) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.yearOfPublishing = yearOfPublishing;
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

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
    @Override
    public String toString()
    {
        return (
                "************************************" +
                        "\nНазва: " + getName() +
                        "\nАвтор: " + getAuthor() +
                        "\nОпис: " + getDescription() +
                        "\nРік видання: " + getYearOfPublishing() +
                        "************************************\n"
                );
    }
}
