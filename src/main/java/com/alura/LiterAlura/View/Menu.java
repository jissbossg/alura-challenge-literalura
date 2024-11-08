package com.alura.LiterAlura.View;

import com.alura.LiterAlura.Dto.AuthorDTO;
import com.alura.LiterAlura.Dto.JsonDTO;
import com.alura.LiterAlura.Entity.Author;
import com.alura.LiterAlura.Entity.Book;
import com.alura.LiterAlura.Entity.Language;
import com.alura.LiterAlura.Repository.IAuthorRepository;
import com.alura.LiterAlura.Repository.IbookRepository;
import com.alura.LiterAlura.Service.ConnectionAPI;
import com.alura.LiterAlura.Service.DataConvertion;
import com.alura.LiterAlura.Tools.Separator;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.*;
import java.util.stream.Collectors;


public class Menu {
    private Scanner sc = new Scanner(System.in);
    private ConnectionAPI cnx = new ConnectionAPI();
    private DataConvertion dataConvertion = new DataConvertion();
    private static final String API_URL = "https://gutendex.com/books/";

    private IbookRepository bookRepository;
    private IAuthorRepository authorRepository;

    public Menu(IbookRepository bookRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private int opc = -1;

    public void showWelcome() {
        System.out.println(Separator.lines());
        System.out.println("¡Bienvenidos a la aplicación LiterluAra!");
        System.out.println("Iniciando sistema......");
    }

    public void goOut() {
        System.out.println(Separator.lines());
        System.out.println("¡Gracias por usar LiterluAra!...");
        System.out.println("Saliendo...");
        System.out.println(Separator.lines());
        System.exit(0); // Termina el programa
    }

    public void showOptions() {
        System.out.println(Separator.lines());
        System.out.println("Menú");
        System.out.println("Escribe el número de la opción que quieres hacer.");
        System.out.println("""
                1). Buscar libro por título
                2). Listar libros registrados
                3). Buscar autor por nombre
                4). Listar autores registrados
                5). Listar autores vivos en un determinado año
                6). Listar libros por idioma
                7). Top 10 libros más descargados
                0). Salir
                """);
        System.out.println(Separator.lines());
    }

    public void displayMenu() {
        showWelcome();
        while (opc != 0) {
            showOptions();
            try {
                opc = Integer.parseInt(sc.nextLine());
                switch (opc) {
                    case 1:
                        getBook();
                        break;
                    case 2:
                        getAllListedBooks();
                        break;
                    case 3:
                        getAuthorByName();
                        break;
                    case 4:
                        getListedAuthors();
                        break;
                    case 5:
                        getAuthorBetweenYears();
                        break;
                    case 6:
                        getBooksByLanguage();
                        break;
                    case 7:
                        getTop10Books();
                        break;
                    case 0:
                        goOut();
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes seleccionar un número.");
            }
        }
    }

    private void getBook() {
        System.out.println(Separator.lines());
        System.out.println("Escriba el nombre del libro: ");
        String bookName = sc.nextLine();

        String json = cnx.getData(API_URL + "?search=" + bookName.replace(" ", "+"));
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);

        Optional<Book> books = results.bookResults().stream()
                .findFirst()
                .map(b -> new Book(b));

        if (books.isPresent()) {
            Book book = books.get();
            if (book.getAuthor() != null) {
                Author author = authorRepository.findAuthorsByName(book.getAuthor().getName());
                if (author == null) {
                    // Crear y guardar un nuevo autor si no existe
                    Author newAuthor = book.getAuthor();
                    author = authorRepository.save(newAuthor);
                }
                try {
                    // Asociar el autor existente con el libro
                    book.setAuthor(author);
                    bookRepository.save(book);
                    System.out.println(Separator.lines());
                    System.out.println(book);
                    System.out.println(Separator.lines());
                } catch (DataIntegrityViolationException e) {
                    System.out.println("El libro ya se encuentra registrado en la base de datos.");
                }
            }
        } else {
            System.out.println("No se encontró el libro: " + bookName);
        }
    }

    private void getAllListedBooks() {
        System.out.println(Separator.lines());
        System.out.println("Libros Registrados");
        System.out.println(Separator.lines());
        List<Book> books = bookRepository.findAll();
        //books.forEach(System.out::println);
        books.forEach(book -> {
            System.out.println(Separator.lines());
            System.out.println(book);
        });
    }

    private void getAuthorByName() {
        System.out.println(Separator.lines());
        System.out.println("Escribe el nombre del autor que deseas buscar: ");
        String authorName = sc.nextLine();
        if (isNumber(authorName)) {
            System.out.println(Separator.lines());
            System.out.println("Debes ingresar un nombre, no un número.");
        } else {
            String json = cnx.getData(API_URL + "?search=" + authorName.replace(" ", "+"));
            JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);

            Optional<AuthorDTO> author = results.bookResults().stream()
                    .findFirst()
                    .map(a -> new AuthorDTO(a.authors().get(0).authorName(), a.authors().get(0).birthYear(), a.authors().get(0).deathYear()));
            if (author.isPresent()) {
                System.out.println(Separator.lines());
                System.out.println(author.get());
            } else {
                System.out.println(Separator.lines());
                System.out.println("No se encontró autor con el nombre: " + authorName);
            }
        }
    }

    private boolean isNumber(String authorName) {
        try {
            Double.parseDouble(authorName);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void getListedAuthors() {
        System.out.println("Autores Registrados");
        System.out.println(Separator.lines());
        List<Author> authors = authorRepository.findAll();
        //authors.forEach(System.out::println);
        authors.forEach(author -> {
            System.out.println(Separator.lines());
            System.out.println(author);
        });
    }

    private void getAuthorBetweenYears() {
        System.out.println(Separator.lines());
        System.out.println("Ingrese el año vivo del autor(es) que desea buscar: ");
        try {
            int year = sc.nextInt();
            List<Author> authors = authorRepository.findAuthorBetweenYear(year);
            if (authors.isEmpty()) {
                System.out.println("No se encontraron registros de autores vivos durante ese año en la base de datos.");
            } else {
                //authors.forEach(System.out::println);
                System.out.println(Separator.lines());
                System.out.printf("Autores vivos para el año: %d\n",year);
                authors.forEach(author -> {
                    System.out.println(Separator.lines());
                    System.out.println(author);
                });
            }

        } catch (InputMismatchException e) {
            System.out.println(Separator.lines());
            System.out.println("Debes ingresar un año válido.");
        }
        sc.nextLine();
    }

    private String getCodeByLanguage(String language) {
        if (language.length() <= 2) {
            return language.toUpperCase();
        } else {
            String codigoInexistente = Language.getCodeForName(language.toUpperCase());
            if (codigoInexistente == null) {
                System.out.println(Separator.lines());
                System.out.println("Idioma no encontrado.");
            }
            return codigoInexistente;
        }
    }

    private void getBooksByLanguage() {
        System.out.println(Separator.lines());
        System.out.println("Ingrese el idioma que desea buscar: ");
        System.out.println("""
                es >> Español
                en >> Inglés
                fr >> Francés
                pt >> Portugés
                la >> Latín
                it >> Italiano
                """);
        System.out.println("Ingrese el idioma: ");
        String language = sc.nextLine();
        List<Book> books = bookRepository.findBookByLanguage(getCodeByLanguage(language));
        if (books.isEmpty()) {
            System.out.println(Separator.lines());
            System.out.println("No se encontraron libros en ese idioma");
        } else {
            //books.forEach(System.out::println);
            System.out.println(Separator.lines());
            System.out.println("Listado de libros por idioma: " + getCodeByLanguage(language) + " - " + Language.getNameForCode(getCodeByLanguage(language)));
            System.out.println(Separator.lines());
            books.forEach(book -> {
                System.out.println(Separator.lines());
                System.out.println(book);
            });
        }
    }

    private void getTop10Books() {
        System.out.println(Separator.lines());
        System.out.println("Top 10 libros más descargados");
        String json = cnx.getData(API_URL);
        //System.err.println(API_URL);
        JsonDTO results = dataConvertion.convertData(json, JsonDTO.class);
        /*System.err.println(results);*/
        // Verificar que bookResults no sea null o vacío
        if (results != null && results.bookResults() != null && !results.bookResults().isEmpty()) {
            List<Book> top10Books = results.bookResults().stream().map(b -> new Book(b))
                    .sorted(Comparator.comparingLong(Book::getDownloads_count).reversed())
                    .limit(10)
                    .collect(Collectors.toList());
            final int[] indice = {1};
            top10Books.forEach(book -> {
                System.out.println(Separator.lines());
                System.out.printf("%d. Titulo de libro: %s\n", indice[0]++, book.getTitle());
                System.out.printf("    Numero de descargas: %d\n",book.getDownloads_count());
            });
        } else {
            System.out.println(Separator.lines());
            System.out.println("No se encontraron libros.");
        }
    }
}
