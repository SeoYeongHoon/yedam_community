package com.yedam.app.book.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.book.service.BookService;
import com.yedam.app.book.service.BookVO;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	// 도서전체조회 - Get
	@GetMapping("bookList")
	public String bookList(Model model) {
		List<BookVO> list = bookService.bookList();
		model.addAttribute("books", list);
		return "book/list";
		//classpath:/templates/book/list.html
	}
	
	// 도서등록 - 페이지 : GET
	@GetMapping("bookInsert")
	public String bookInsertForm(Model model) {
		BookVO bookVO = bookService.getBookNo();
		model.addAttribute("book", bookVO);
		return "book/insert";
		// classpath:/templates/book/insert.html
	}
	
	// 도서등록 - 처리 : POST
	@PostMapping("bookInsert")
	public String bookInsertProcess(BookVO bookVO) {
		bookService.bookInfoInsert(bookVO);
		return "redirect:bookList";
	}
	
	
	
	
	
	
	
}
