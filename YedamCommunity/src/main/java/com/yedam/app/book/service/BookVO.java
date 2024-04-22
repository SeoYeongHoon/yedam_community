package com.yedam.app.book.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BookVO {
	private int bookNo;
	private String bookName;
	private String bookCovering;
	// 기본포맷 : yyyy/MM/dd
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date bookDate;
	private int bookPrice;
	private String bookPublisher;
	private String bookInfo;
	
	private int rentCount; // rent_count
	private int rentTotalPrice; // rent_total_price
}
