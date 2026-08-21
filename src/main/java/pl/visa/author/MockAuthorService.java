package pl.visa.author;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MockAuthorService implements AuthorService {

    private List<Author> authorList;
    private static Long nextId = 4L;

    public MockAuthorService() {
        authorList = new ArrayList<>();
        authorList.add(new Author(1L, "Tadzik", "Tadzikowski"));
        authorList.add(new Author(2L, "Robert", "Lewandowski"));
        authorList.add(new Author(3L, "Bajko", "Pisarz"));
    }

    //////////

    @Override
    public List<Author> getAuthors() {
        return authorList;
    }

    @Override
    public Optional<Author> get(Long id) {

        return authorList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public void add(Author author) {

        author.setId(nextId);
        nextId++;
        authorList.add(author);
    }

    @Override
    public void delete(Long id) {
        Optional<Author> authorToDelete = get(id);

        if (authorToDelete.isEmpty()) {
            return;
        }

        authorList.remove(authorToDelete.get());
    }

    @Override
    public void update(Author author) {

        Optional<Author> authorToUpdate = get(author.getId());

        if (authorToUpdate.isEmpty()) {
            return;
        }

        int index = authorList.indexOf(authorToUpdate.get());

        authorList.set(index, author);
    }
}
