package com.mylearnings.spring5webapp.bootstrap;

import com.mylearnings.spring5webapp.model.Author;
import com.mylearnings.spring5webapp.model.Book;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.mylearnings.spring5webapp.repositories.AuthorRepository;
import com.mylearnings.spring5webapp.repositories.BookRepository;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        //Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Drive Design",  "1234",  "Harper Colins");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        //Rod
        Author rod = new Author("Rod", "Johnson");
        Book notEJB = new Book("J2EE dev without EJB", "5678", "Worx");
        rod.getBooks().add(notEJB);
        notEJB.getAuthors().add(rod);
        authorRepository.save(rod);
        bookRepository.save(notEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
