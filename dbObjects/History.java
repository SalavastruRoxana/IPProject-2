package dbObjects;

public class History {
    private double id;
    private Book visitedBook;
    private String visitationDate;

    public History(double id, Book visitedBook, String visitationDate) {
        this.id = id;
        this.visitedBook = visitedBook;
        this.visitationDate = visitationDate;
    }

    public History() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public Book getVisitedBook() {
        return visitedBook;
    }

    public void setVisitedBook(Book visitedBook) {
        this.visitedBook = visitedBook;
    }

    public String getVisitationDate() {
        return visitationDate;
    }

    public void setVisitationDate(String visitationDate) {
        this.visitationDate = visitationDate;
    }
}
