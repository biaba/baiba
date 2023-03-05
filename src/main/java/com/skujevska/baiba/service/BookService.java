package com.skujevska.baiba.service;

import com.skujevska.baiba.model.Book;
import com.skujevska.baiba.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository repo;

    public List<Book> getAllBooksNewestFirst(Integer pageSize, Integer offset) {
        List<Book> result = new ArrayList<>();
        repo.findAll(PageRequest.of(offset, pageSize, Sort.by("createdAt").descending()))
                .forEach(result::add);
        return result;
    }

    public Book saveBook(Book book) {
        book.setCreatedAt(Instant.now());
        return repo.save(book);
    }

    public long getBookCount() {
        return repo.count();
    }

    public Optional<Book> checkIfExists(String name) {
        return repo.findByName(name);
    }
}
