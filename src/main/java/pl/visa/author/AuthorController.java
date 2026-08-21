package pl.visa.author;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.visa.book.Book;
import pl.visa.book.BookService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    //////////////

    @GetMapping("")
    @ResponseBody
    public List<Author> allAuthors() {
        return authorService.getAuthors();
    }

    @PostMapping("")
    public void add(@RequestBody Author author) {
        authorService.add(author);
    }

    @GetMapping("/{id}")
    public Author getAuthor(@PathVariable Long id) {
        return authorService.get(id).
                orElseThrow(() ->
                new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Author not found in DB"
        ));
    }


    @PutMapping("")
    public void update(@RequestBody Author author) {

        authorService.update(author);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }

    @GetMapping("/{id}/books")
    public List<Book> getAuthorBooks(@PathVariable Long id) {
        return bookService.getBooks()
                .stream()
                .filter(a -> a.getAuthor().getId().equals(id))
                .collect(Collectors.toList());
    }
}
