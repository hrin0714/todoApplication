package com.kakaoix.todo.app.rest.domain;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	private int todoId;
	
	@Valid
	@NotNull(message = "whatTodo가 명시되어야 합니다.")
	@NotBlank(message = "whatTodo가 명시되어야 합니다.")
	private String whatTodo;

	//@Builder.Default
	private String status = Status.Ing.getValue();

	private String insertDate;

	private String updateDate;

	//@Builder.Default
	private String dbsts = Status.Normal.getValue();

	private List<TodoReference> todoReferenceList;
	
	private List<String> parentTodoId;

	private String refYn;

	public String getRefYn() {
		return refYn;
	}

	public void setRefYn(String refYn) {
		this.refYn = refYn;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getDbsts() {
		return dbsts;
	}

	public void setDbsts(String dbsts) {
		this.dbsts = dbsts;
	}

	public List<TodoReference> getTodoReferenceList() {
		return todoReferenceList;
	}

	public void setTodoReferenceList(List<TodoReference> todoReferenceList) {
		this.todoReferenceList = todoReferenceList;
	}
	
	public List<String> getParentTodoId() {
		return parentTodoId;
	}

	public void setParentTodoId(List<String> parentTodoId) {
		this.parentTodoId = parentTodoId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}

}
