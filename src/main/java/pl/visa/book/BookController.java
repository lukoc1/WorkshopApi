package pl.visa.book;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //////////////

    @GetMapping("")
    @ResponseBody
    public List<Book> allBooks() {
        return bookService.getBooks();
    }

    @PostMapping("")
    public void add(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.get(id).
                orElseThrow(() ->
                new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Book not found in DB"
        ));
    }


    @PutMapping("")
    public void update(@RequestBody Book book) {

        bookService.update(book);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
