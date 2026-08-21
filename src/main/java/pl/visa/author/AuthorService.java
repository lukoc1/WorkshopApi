package pl.visa.author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> getAuthors();

    Optional<Author> get(Long id);

    void add(Author author);

    void delete(Long id);

    void update(Author author);
}
