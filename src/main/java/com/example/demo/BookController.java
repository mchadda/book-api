
package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping
	public List<Book> findAll() {
		return bookService.findAll();
	}

	// Fetch a book
	@GetMapping("/{id}")
	public Optional<Book> findById(@PathVariable Long id) {
		return bookService.findById(id);
	}

	// create a book
	@ResponseStatus(HttpStatus.CREATED) // 201
	@PostMapping
	public Book create(@RequestBody Book book) {
		return bookService.save(book);
	}

	// delete a book
	@ResponseStatus(HttpStatus.NO_CONTENT) // 204
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		bookService.deleteById(id);
	}

}
