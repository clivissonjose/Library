package bookeable.com.bookStore.dtos;

import bookeable.com.bookStore.enums.BookGender;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BookRequestDTO {


    @NotNull(message = "O titulo do livro é obrigatório")
    @Size(min = 10, max = 100)
    private String title;

    @NotNull(message =  "O preço do livro é obrigatório")
    private float price;

    @NotNull(message = "O nome do autor é obrigatório")
    @Size(min = 10, max = 100)
    private String author;

    @NotNull(message = "o isbn é obrigatório")
    @Size(min = 10)
    private String isbn;

    @NotNull(message = "A quantidade de páginas do livro é obrigatória.")
    private int pagesNumbers;

    @NotNull(message = "O gênero do livro é obrigatório")
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
