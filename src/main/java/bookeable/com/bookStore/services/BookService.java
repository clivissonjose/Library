package bookeable.com.bookStore.services;

import bookeable.com.bookStore.dtos.BookRequestDTO;
import bookeable.com.bookStore.dtos.BookResponseDTO;
import bookeable.com.bookStore.models.Book;
import bookeable.com.bookStore.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO CreateBook(BookRequestDTO dto){

        Book book = returnEntity(dto);
        Book bookSaved = bookRepository.save(book);
        BookResponseDTO bookToBeReturned = toDto(bookSaved);

        return bookToBeReturned;
    }

    public List<Book> listAll(){

        List<Book> allBooks =  bookRepository.findAll();


        return allBooks;

    }


    public List<Book> findByAuthor(String authorName) {
        return  bookRepository.findByAuthorContainingIgnoreCase(authorName);
    }

    public void delete(Long id){

        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Livro com ID " + id + " não encontrado.");
        }

    }

    private  Book returnEntity(BookRequestDTO dto){
        Book book = new Book();

        book.setAuthor(dto.getAuthor());
        book.setGender(dto.getGender());
        book.setIsbn(dto.getIsbn());
        book.setPrice(dto.getPrice());
        book.setTitle(dto.getTitle());
        book.setPagesNumbers(dto.getPagesNumbers());

        return book;

    }

    private BookResponseDTO toDto(Book book){
         BookResponseDTO bookDTO = new BookResponseDTO();
         bookDTO.setAuthor(book.getAuthor());
         bookDTO.setGender(book.getGender());
         bookDTO.setIsbn(book.getIsbn());
         bookDTO.setTitle(book.getTitle());
         bookDTO.setPagesNumbers(book.getPagesNumbers());
         bookDTO.setPrice(book.getPrice());

         return bookDTO;
    }


}
