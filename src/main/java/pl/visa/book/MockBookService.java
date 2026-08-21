package pl.visa.book;

import org.springframework.stereotype.Service;
import pl.visa.author.Author;
import pl.visa.author.AuthorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService{

    private final AuthorService authorService;
    private List<Book> bookList;
    private static Long nextId = 4L;

    public MockBookService(AuthorService authorService) {
        this.authorService = authorService;

        Author author1 = authorService.get(1L).orElseThrow();
        Author author2 = authorService.get(2L).orElseThrow();

        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "9788324631766", "Thinking in Java", author1, "Helion", "programming"));
        bookList.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", author2, "Helion",
                "programming"));
        bookList.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", author1, "Helion",
                "programming"));
    }

    //////////

    @Override
    public List<Book> getBooks() {
        return bookList;
    }

    @Override
    public Optional<Book> get(Long id) {

        return bookList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public void add(Book book) {

        book.setId(nextId);
        nextId++;
        bookList.add(book);
    }

    @Override
    public void delete(Long id) {
        Optional<Book> bookToDelete = get(id);

        if (bookToDelete.isEmpty()) {
            return;
        }

        bookList.remove(bookToDelete.get());
    }

    @Override
    public void update(Book book) {

        Optional<Book> bookToUpdate = get(book.getId());

        if (bookToUpdate.isEmpty()) {
            return;
        }

        int index = bookList.indexOf(bookToUpdate.get());

        bookList.set(index, book);
    }
}
