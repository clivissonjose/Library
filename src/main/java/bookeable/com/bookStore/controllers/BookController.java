package bookeable.com.bookStore.controllers;

import bookeable.com.bookStore.dtos.BookRequestDTO;
import bookeable.com.bookStore.dtos.BookResponseDTO;
import bookeable.com.bookStore.models.Book;
import bookeable.com.bookStore.services.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/books")
public class BookController {

    BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO dto){
        BookResponseDTO bookSaved = bookService.CreateBook(dto);

        return ResponseEntity.ok(bookSaved);

    }

    @GetMapping()
    public ResponseEntity<List<Book>> listAll(){
        List<Book> allBooks = bookService.listAll();

        return ResponseEntity.ok(allBooks);

    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> findByAuthor(@RequestParam("authorName") String authorName){

        List<Book> booksByAuthor =  bookService.findByAuthor(authorName);

        return ResponseEntity.ok(booksByAuthor);
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<String> delete(@PathVariable Long id ){
        try {
            bookService.delete(id);
            return ResponseEntity.ok("Livro deletado com sucesso!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
