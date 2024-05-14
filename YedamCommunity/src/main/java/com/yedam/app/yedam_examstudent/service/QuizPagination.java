package com.yedam.app.yedam_examstudent.service;

public class QuizPagination {
	private int first; //시작페이지 번호
	private int last; //마지막페이지 번호
	private int quizCnt; //전체문제 개수
	private int pageCnt; //전체페이지 개수
	private int quizSize = 1; //한페이지 문제 개수
	private boolean prev; //이전 이동
	private boolean next; //다음 이동
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getQuizCnt() {
		return quizCnt;
	}
	public void setQuizCnt(int quizCnt) {
		this.quizCnt = quizCnt;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}
	public int getQuizSize() {
		return quizSize;
	}
	public void setQuizSize(int quizSize) {
		this.quizSize = quizSize;
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
	//메서드
	public void pageInfo(int quizCnt) {
		//시작페이지
		this.first = 1;
		//전체문제 개수
		this.quizCnt = quizCnt;
		//한페이지 문제개수
		this.pageCnt = quizCnt;
		//마지막페이지 번호
		this.last = quizCnt;
		//이전 페이지
		this.prev = first == 1 ? false : true;
		//다음 페이지
		this.next = last > pageCnt ? false : true;
		if(this.last > this.pageCnt) {
			this.last = this.pageCnt;
			this.next = false;
		}
	}
}
