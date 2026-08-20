package pl.visa.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/add")
    public String add(@RequestParam String title) {
        Book book = new Book();
        book.setTitle(title);
        bookDao.saveBook(book);

        return "New book added";
    }

    @GetMapping("/find")
    public String find(@RequestParam Long id) {
        Book byId = bookDao.findById(id);

        if (byId == null) {
            return "Book not found";
        }
        return byId.toString();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, @RequestParam(required = false) String title, @RequestParam(required = false) Integer rating, @RequestParam(required = false) String description){
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Book not found";
        }

        if (title != null)
            book.setTitle(title);

        if (rating != null)
            book.setRatingBook(rating);

        if (description != null)
            book.setDescription(description);

        bookDao.update(book);
        return book.toString();
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Book book = bookDao.findById(id);
        if (book == null) {
            return "Book not found";
        }

        bookDao.delete(book);
        return "Book: " + book + " - deleted from DB";
    }


    @GetMapping("/get/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookDao.findById(id);
    }

    @GetMapping("/get-new")
    public Book getNewBook() {
        return new Book();
    }

    @GetMapping("/all")
    public List<Book> allBooks() {
        return bookDao.findAll();
    }

}
