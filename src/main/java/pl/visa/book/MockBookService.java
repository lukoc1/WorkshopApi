package pl.visa.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService{

    private List<Book> bookList;
    private static Long nextId = 4L;

    public MockBookService() {
        bookList = new ArrayList<>();
        bookList.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion", "programming"));
        bookList.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        bookList.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
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
