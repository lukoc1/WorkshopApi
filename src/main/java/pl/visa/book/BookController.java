package pl.visa.book;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public void add(@RequestBody Book book) {
        bookService.add(book);
    }

    @GetMapping("/get/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        return bookService.get(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Book book) {

        bookService.update(book);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

}
