package com.spring.sboot.service.one2many;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.sboot.domains.one2many.Book;
import com.spring.sboot.domains.one2many.Page;
import com.spring.sboot.repositories.one2many.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * getting all users record by using the method findaAll() of CrudRepository
	 */

	@Transactional
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();

		final Session session = (Session) entityManager.unwrap(Session.class);

		bookRepository.findAll().forEach(book1 -> {

			books.add(book1);
//			book1.getPages();
			book1.getPages().size();
//			Hibernate.initialize(book1.getPages());

		});

		session.close();
		return books;
	}



	/**
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	public Book getBookById(Long bookID) {
		return bookRepository.findById(bookID).get();
	}

	/**
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public void saveOrUpdate(Book book) {
		bookRepository.save(book);
	}

	/**
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(Long bookID) {
		bookRepository.deleteById(bookID);
	}

	/**
	 * updating a record
	 */
	public void update(Book Book, Long bookID) {
		bookRepository.save(Book);
	}

}
