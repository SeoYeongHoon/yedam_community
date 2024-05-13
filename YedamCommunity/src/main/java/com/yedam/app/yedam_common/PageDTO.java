package com.yedam.app.yedam_common;

import lombok.Data;

public class PageDTO {
	private int page;
	private int startPage, endPage;
	private boolean prev, next;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getStarPage() {
		return startPage;
	}

	public void setStarPage(int starPage) {
		this.startPage = starPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	// 현재 페이지, 목록 총 개수, 한 페이징 당 보여줄 목록 개수 5 5.0 => 5 * 1.0
	public PageDTO(int _page, int _totalCnt, int _pageForPaging) {
		this.page = _page;
		int realEnd = (int) Math.ceil(_totalCnt * 1.0/ _pageForPaging);
		
		this.endPage = (int) (Math.ceil(page * 1.0/ _pageForPaging) * _pageForPaging);
		this.startPage = this.endPage - (_pageForPaging - 1);
		
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	@Override
	public String toString() {
		return "PageDTO [page=" + page + ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + "]";
	}
	
	
}
