package com.kakaoix.todo.app.rest.service;

import com.kakaoix.todo.app.rest.domain.Todo;
import com.kakaoix.todo.app.rest.exception.TodoException;

import java.util.List;

public interface TodoService {
	
	/**
	 * 할일 목록 Row Count 조회
	 * @return TodoList
	 */
	public Integer selectTodoListCount(); 
	
	/**
	 * 할일 목록 조회
	 * @return TodoList
	 */
	public List<Todo> selectTodoList(int pageNo);
	
	/**
	 * 참조하는 목록 조회(체크박스 제공용)
	 * @return TodoList
	 */
	public List<Todo> findReferenceListById(int todoId); 
	
	/**
	 * 목록 업데이트
	 * @return 
	 */
	public int modTodoItem(Todo todoItem) throws TodoException;
	
	/**
	 * todoItem Status 값 변경
	 * @return 
	 */
	public int modStatus(Todo todoItem) throws TodoException;
		
	/**
	 * todoItem 목록 삭제
	 * @return 
	 */
	public int removeTodoItem(int todoId) throws Exception; 
		
	/**
	 * todoItem 목록 등록
	 * @return 
	 */
	public int addTodoItem(Todo todoItem) throws TodoException; 
	
}
