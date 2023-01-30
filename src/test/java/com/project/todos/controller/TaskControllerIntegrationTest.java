package com.project.todos.controller;

import com.project.todos.TodosApplication;
import com.project.todos.entity.Task;
import com.project.todos.utils.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TodosApplication.class)
@AutoConfigureTestDatabase
public class TaskControllerIntegrationTest {

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    private boolean mockInitialized;

    @Before
    public void setup() {
        if (!mockInitialized) {
            mockMvc = MockMvcBuilders.webAppContextSetup(context)
                    .apply(springSecurity()).build();
            mockInitialized = true;
        }
    }

    @Test
    public void loginRedirectTest() throws Exception {
        mockMvc.perform(get("/todos/api/task").contentType(MediaType.APPLICATION_JSON))
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @WithMockUser()
    @Test
    public void createTask_thenStatus200() throws Exception {
        Task task = Task.builder().name("task1").description("task 1 description").status(Status.PENDING).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/todos/api/task/create")
                .flashAttr("taskToCreate", task).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("taskList"))
                .andExpect(model().attributeExists("tasks"));
    }

    @WithMockUser()
    @Test
    public void findAllTasks_thenStatus200() throws Exception {
        mockMvc.perform(get("/todos/api/task").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(view().name("taskList"))
                .andExpect(model().attributeExists("tasks"));
    }
}