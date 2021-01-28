package com.cos.project.service;

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
}
