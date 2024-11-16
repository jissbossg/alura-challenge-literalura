package com.alura.LiterAlura.Entity;

import com.alura.LiterAlura.Dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book")
    private Long id;
    @Column(unique = true)
    private String title;

    private String language;

    @ManyToOne
    @JoinColumn(name = "id_author")
    private Author author;

    private Long downloads_count;

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        this.language = bookDTO.languages().get(0).toUpperCase();
        if (bookDTO.authors().size() > 0) {
            this.author = new Author(bookDTO.authors().get(0));
        }
        this.downloads_count = bookDTO.downloads();
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public Long getDownloads_count() {
        return downloads_count;
    }

    @Override
    public String toString() {
        return "\n Titulo: " + title +
                "\n Autor: " + author.getName() +
                "\n Idioma: " + language +
                "\n Número de descargas: " + downloads_count;
    }
}
