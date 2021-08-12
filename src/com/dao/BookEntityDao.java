package com.dao;

import java.util.List;

import com.model.BookEntity;

public interface BookEntityDao {
	public Integer addBook(BookEntity book);
	public Integer updateBook(BookEntity book);
	public Integer deleteBook(BookEntity book);
	public BookEntity getBookById(Integer id);
	public List<BookEntity> getAllBooks();
	public BookEntity getBookByName(String book);
}
