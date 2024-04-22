package com.yedam.app.book.mapper;

import java.util.List;

import com.yedam.app.book.service.BookVO;

public interface RentMapper {
	public List<BookVO> selectRentInfo();
}
