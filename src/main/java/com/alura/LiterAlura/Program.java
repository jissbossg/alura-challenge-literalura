package com.alura.LiterAlura;

import com.alura.LiterAlura.View.Menu;
import com.alura.LiterAlura.Repository.IAuthorRepository;
import com.alura.LiterAlura.Repository.IbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Program implements CommandLineRunner {
	@Autowired
	private IbookRepository bookRepository;
	@Autowired
	private IAuthorRepository authorRepository;
	public static void main(String[] args) {
		SpringApplication.run(Program.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Menu main = new Menu(bookRepository, authorRepository);
		main.displayMenu();
	}
}