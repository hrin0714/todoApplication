package com.kakaoix.todo.app.rest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kakaoix.todo.app.rest.domain.PageBean;
import com.kakaoix.todo.app.rest.domain.Todo;
import com.kakaoix.todo.app.rest.domain.TodoReference;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TodoMapper {
	
	/**
	 * 
	 * 개요: Todo List 조회
	 * @Method Name : selectTodoList
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param pageBean
	 * @return
	 */
	List<Todo> selectTodoList(PageBean pageBean);
	
	/**
	 * 
	 * 개요: Todo List의 Rowcount 조회
	 * @Method Name : selectTodoListCount
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param PageBean
	 * @return
	 */
	int selectTodoListCount(PageBean PageBean);
	
	/**
	 * 
	 * 개요: Todo 조회 ( Id로 조회 )
	 * @Method Name : findTodoItemById
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	Todo findTodoItemById(int todoId);
	
	/**
	 * 
	 * 개요: 특정 todoItem이 참고하고 있는 Todo 리스트 조회(체크박스 용)
	 * @Method Name : selectTodoListForChcekBox
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	List<Todo> selectTodoListForChcekBox(int todoId);
	
	/**
	 * 
	 * 개요: Todo 등록
	 * @Method Name : insertTodoItem
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoItem
	 * @return
	 */
	int insertTodoItem(Todo todoItem);
	
	/**
	 * 
	 * 개요: Todo 수정
	 * @Method Name : updateTodoItem
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoItem
	 * @return
	 */
	int updateTodoItem(Todo todoItem);
	int updateTodoItem(Map<String, Object> todoItem);
	
	/**
	 * 
	 * 개요: 참조 목록 등록
	 * @Method Name : insertReferenceList
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param refList
	 * @return
	 */
	int insertReferenceList(List<TodoReference> refList);
	int insertReferenceList(Todo todoItem);
	
	

	/**
	 * 
	 * 개요: 특정 todoId가 참조하는 진행중인 todo의 갯수 조회( 완료처리시 확인용 )
	 * @Method Name : selectIngReferenceListByTodoId
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	public int selectProceedingReferenceListCount(int todoId);
	
	
	/**
	 * 
	 * 개요: 특정 todoId가 참조하는 목록 모두 삭제 
	 * @Method Name : deleteReferenceList
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	int deleteReferenceListByTodoId(int todoId);
	
	/**
	 * 
	 * 개요: 특정 todoId를 참조하는 목록 모두 삭제, todoId 삭제 시 삭제 처리. 
	 * @Method Name : deleteReferenceList
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	int deleteReferenceListByParentTodoId(int todoId);
	
	
	/**
	 * 
	 * 개요: TodoItem 완전 삭제(deprecated)
	 * @Method Name : deleteReferenceList
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoId
	 * @return
	 */
	@Deprecated
	int deleteTodoItem(int todoId);
	
	/**
	 * 
	 * 개요: 내가 등록 하려는 참조TodoId중 나를 등록하면서 진행중인 케이스( 참조 Todo등록시  확인용 )
	 * @Method Name : selectIngReferenceListByTodoId
	 * @history
	 * ---------------------------------------------------------------------------------
	 *  변경일                    작성자                    변경내용
	 * ---------------------------------------------------------------------------------
	 *  2019. 1. 29.  austin      최초 작성   
	 * ---------------------------------------------------------------------------------
	 * @param todoItem
	 * @return
	 */
	int selectReferenceMeCount(Todo todoItem);
	
}
