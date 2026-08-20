package pl.visa.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int ratingBook;
    private String description;

    public int getRatingBook() {
        return ratingBook;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRatingBook(int ratingBook) {
        this.ratingBook = ratingBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {

        String rank = this.ratingBook != 0 ? String.valueOf(this.ratingBook) : "";
        String desc = this.description != null ? this.description : "";

        return "[" + this.getId() + "] " + this.getTitle() + "<br>Opis: " + desc + "<br>Ocena: " + rank;
    }
}
