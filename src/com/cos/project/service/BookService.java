package com.cos.project.service;

import java.util.List;

import com.cos.project.domain.book.Book;
import com.cos.project.domain.book.BookDao;
import com.cos.project.domain.book.dto.SaveReqDto;

public class BookService {
	
	private BookDao bookDao;
	
	public BookService() {
		bookDao = new BookDao();
	}
	
	public int 예약하기(SaveReqDto dto) {
		return bookDao.save(dto);
	}
	
	public List<Book> 예약조회(int id){
		return bookDao.findById(id);
	}
}
