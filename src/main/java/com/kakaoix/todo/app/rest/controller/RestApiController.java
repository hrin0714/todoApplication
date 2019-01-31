package com.kakaoix.todo.app.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.kakaoix.todo.app.rest.domain.Todo;
import com.kakaoix.todo.app.rest.exception.TodoException;
import com.kakaoix.todo.app.rest.service.TodoService;

@RestController
public class RestApiController {

	private static final Log logger = LogFactory.getLog(RestApiController.class);

	@ExceptionHandler
	public ResponseEntity<?> exceptionHndler(TodoException e){
		// 에러 메세지 중앙처리 - 가능하지만..
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("code", e.getCode());
		resultMap.put("message", e.getMessage());
		
		return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
	}

	private TodoService todoService;

	public RestApiController(TodoService todoService) {
		this.todoService = todoService;
	}

	/**
	 * Todo 리스트 조회
	 * @param pageNo
	 * @return 
	 */
	@GetMapping("/api/todos/{pageNo}")
	public ResponseEntity<?> getList(@PathVariable("pageNo") int pageNo) {
		try {
			// get Total count
			Integer listTotalCount = todoService.selectTodoListCount();
			
			// retrieve list
			List<Todo> todoList = todoService.selectTodoList(pageNo);
			
			// 헤더?
			//HttpHeaders headers = new HttpHeaders();
		    //headers.add("Content-Type", "application/json; charset=UTF-8");
		    //headers.add("X-list-total-count", listTotalCount.toString());

		    Map<String, Object> resultMap = new HashMap<>();
		    resultMap.put("totalCount", listTotalCount);
		    resultMap.put("currentListSize", todoList.size());
		    resultMap.put("lists", todoList);

		    return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 특정 todoId가 참조하고 있는 Todo 목록 조회(체크 박스 만들기 용)
	 * @param todoId
	 * @return
	 */
	@GetMapping("/api/todos/{todoId}/ref")
	public ResponseEntity<?> findReferenceListById(@PathVariable("todoId") int todoId) {
		try {
			List<Todo> todoList = todoService.findReferenceListById(todoId);

			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상입니다.");
			resultMap.put("currentListSize", todoList.size());
		    resultMap.put("lists", todoList);
		    
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Todo 등록하기
	 * @param todoItem
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/api/todos")
	public ResponseEntity<?> addTodoItem(Todo todoItem, BindingResult bindingResult) {
		try {
            if(bindingResult.hasErrors()) {
                new TodoException(bindingResult.getAllErrors().stream().toString(), "4001");
            }
			
			int result = todoService.addTodoItem(todoItem);
			
			if(result < 0) {
				return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
			}
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상 등록 되었습니다.");
			
			return new ResponseEntity<>(resultMap, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 수정하기
	 * @param todoItem
	 * @param bindingResult
	 * @return
	 */
	@PutMapping("/api/todos")
	public ResponseEntity<?> modTodoItem(Todo todoItem, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()) {
				new TodoException(bindingResult.getAllErrors().stream().toString(), "4001");
			}
			
			int result = todoService.modTodoItem(todoItem);
			
			if(result < 0) {
				// 업데이트 건수가 존재하지 않습니다.
				throw new TodoException("업데이트된 데이터가 없습니다.", "4001");
			}
			
			Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("code", "0000");
            resultMap.put("message", "정상적으로 수정 되었습니다.");

            return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);
		} catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}

    /**
     * Todo 상태값 변경하기
     * @param todoItem
     * @param bindingResult
     * @return
     */
	@PutMapping("/api/todos/status")
	public ResponseEntity<?> setStatus(Todo todoItem, BindingResult bindingResult) {
		try {
			if(bindingResult.hasErrors()) {
				return  new ResponseEntity<>("required", HttpStatus.BAD_REQUEST);
			}
			
			logger.warn("todoItem --> " + todoItem);
			
			int result = todoService.modStatus(todoItem);
			
			if(result < 0) {
				// 업데이트 건수가 존재하지 않습니다.
				throw new TodoException("없데이트된 데이터가 없습니다.", "4001");
			}
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "상태값이 정상적으로 수정 되었습니다.");
			
			return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Todo 삭제 하기
	 * @param todoId
	 * @return
	 */
	@DeleteMapping("/api/todos/{todoId}")
	public ResponseEntity<?> deleteTodoItem(@PathVariable("todoId") int todoId) {
		try {
			int result = todoService.removeTodoItem(todoId);
			
			if(result < 0) {
                throw new TodoException("삭제된 데이터가 없습니다.", "4001");
			}

			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("code", "0000");
			resultMap.put("message", "정상적으로 삭제 되었습니다.");

            return new ResponseEntity<>(resultMap, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
}
