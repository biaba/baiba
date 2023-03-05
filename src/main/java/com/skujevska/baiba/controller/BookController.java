package com.skujevska.baiba.controller;

import com.skujevska.baiba.model.Book;
import com.skujevska.baiba.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    // adding book
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/books/form")
    public String makeRequest(Model model) {

        Book book = new Book();
        model.addAttribute("book", book);
        return "bookForm";
    }

    // submitting book
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value={"/books"})
    public String submitBook(@Valid @ModelAttribute("book") Book book, BindingResult br, Model model) {

        if (bookService.checkIfExists(book.getName()).isPresent()) {
            model.addAttribute("status", true);
            Book theBook = new Book();
            model.addAttribute("book", theBook);
            return "bookForm";
        }
        Book savedBook  = bookService.saveBook(book);
        model.addAttribute("status", true);
        model.addAttribute("bookName", savedBook.getName());
        addAttributesForBooks(model);
        return "books";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        addAttributesForBooks(model);
        return "books";
    }

    @GetMapping("/books/{offset}")
    public String paginate(@PathVariable int offset, Model model) {
        long total = bookService.getBookCount();

        List<Book> list = bookService.getAllBooksNewestFirst(50, offset);
        model.addAttribute("books", list);
        model.addAttribute("nextPage", total> (offset+1)*50 ? offset+1:999);
        model.addAttribute("prevPage", offset > 0? offset-1 : 999);
        model.addAttribute("total", total);
        return "books";
    }

    private void addAttributesForBooks(Model model) {
        List<Book> list = bookService.getAllBooksNewestFirst(50, 0);
        long total = bookService.getBookCount();
        model.addAttribute("books", list);
        model.addAttribute("nextPage", total>50? 1:999);
        model.addAttribute("prevPage", 999);
        model.addAttribute("total", total);
    }
}
