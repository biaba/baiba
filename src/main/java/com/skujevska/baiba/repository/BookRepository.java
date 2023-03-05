package com.skujevska.baiba.repository;

import com.skujevska.baiba.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book,Long> {
    Optional<Book> findByName(String name);
}
