package bookeable.com.bookStore.dtos;

import bookeable.com.bookStore.enums.BookGender;

public class BookResponseDTO {

    private String title;
    private float price;
    private String author;
    private String isbn;
    private int pagesNumbers;
    private BookGender gender;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getPagesNumbers() {
        return pagesNumbers;
    }

    public void setPagesNumbers(int pagesNumbers) {
        this.pagesNumbers = pagesNumbers;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookGender getGender() {
        return gender;
    }

    public void setGender(BookGender gender) {
        this.gender = gender;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
