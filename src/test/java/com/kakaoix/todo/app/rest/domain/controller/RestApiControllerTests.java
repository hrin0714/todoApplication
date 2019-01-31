package com.kakaoix.todo.app.rest.domain.controller;


import com.kakaoix.todo.app.rest.domain.Todo;
import com.kakaoix.todo.app.rest.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;


/**
 *  참고사이트 : http://www.springboottutorial.com/unit-testing-for-spring-boot-rest-services
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApiControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    Todo mockTodo = new Todo();

    @Test
    public void retrieveTodoList() throws Exception{
        Mockito.when(todoService.modTodoItem(mockTodo)).thenReturn(1);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/todos/").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        // TODO 코드 미완성... 결과예상값, 결과값 비교
    }

}
