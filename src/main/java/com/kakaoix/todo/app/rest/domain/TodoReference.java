package com.kakaoix.todo.app.rest.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoReference {
	
	private int refSeq;
	
	private int todoId;
	
	private int parentTodoId;
	
	private String whatTodo;
	
	private String status;
	
	private String insertDate;
	
	public int getRefSeq() {
		return refSeq;
	}

	public void setRefSeq(int refSeq) {
		this.refSeq = refSeq;
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public int getParentTodoId() {
		return parentTodoId;
	}

	public void setParentTodoId(int parentTodoId) {
		this.parentTodoId = parentTodoId;
	}

	public String getWhatTodo() {
		return whatTodo;
	}

	public void setWhatTodo(String whatTodo) {
		this.whatTodo = whatTodo;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
