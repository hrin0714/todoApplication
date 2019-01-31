package com.kakaoix.todo.app.rest.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Data;

@Data
public class PageBean {
	
	private static final Log logger = LogFactory.getLog(PageBean.class);

	private final int  PER_PAGE = 5;
	private final int  THOUSAND = 1000;
	
	private int pageNo;
	private int startRow;
	private int endRow;
	private int todoId;
	private String whatTodo;
	
	public PageBean(int pageNo) {
		this.pageNo = pageNo;
		this.startRow = (((pageNo - 1) * PER_PAGE));
		this.endRow = PER_PAGE;
		
		//default
		//this.todoId = 0;
		
		logger.warn(this.toString());
	}
	
	public PageBean(int pageNo, int todoId) {
		this.pageNo = pageNo;
		this.startRow = (((pageNo - 1) * PER_PAGE));
		this.endRow = THOUSAND;
		this.todoId = todoId;
		
		logger.warn(this.toString());
	}
	
	public PageBean(int pageNo, int todoId, String whatTodo) {
		this.pageNo = pageNo;
		this.startRow = (((pageNo - 1) * PER_PAGE));
		this.endRow = THOUSAND;
		this.todoId = todoId;
		this.whatTodo = whatTodo;
		
		logger.warn(this.toString());
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getWhatTodo() {
		return whatTodo;
	}

	public void setWhatTodo(String whatTodo) {
		this.whatTodo = whatTodo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
