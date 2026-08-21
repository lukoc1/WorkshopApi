package pl.visa.book;

import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockBookService implements BookService{

    private final BookDao bookDao;
    private List<Book> bookList;


    public MockBookService(BookDao bookDao) {
        this.bookDao = bookDao;
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
        return bookDao.findAll();
    }

    @Override
    public void add(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public Optional<Book> get(Long id) {
        Book book = bookDao.findById(id);

        return Optional.ofNullable(book);
    }

    @Override
    public void update(Book book) {
        Book bookToUpdate = bookDao.findById(book.getId());

        int index = bookList.indexOf(bookToUpdate);

//        bookList.set(index, book);

        bookDao.update(book);
    }

    @Override
    public void delete(Long id) {
        Book book = bookDao.findById(id);

        bookDao.delete(book);
    }
}
