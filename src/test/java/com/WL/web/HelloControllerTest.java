package com.WL.web;


import com.WL.web.dto.Info;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void index가_리턴된다() throws Exception {
        String index = "index";

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(index));
    }

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //RestController에 의한 JSON응답값을 검사하기위해 사용
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    @Test
    public void RequestBody테스트() throws Exception {

        String content = objectMapper.writeValueAsString(new Info("준호", "wnsgh"));
       // String name = "준호";

       /*mvc.perform(post("/test").param("name", "준호")
                .param("id", "wnsgh"))
               .andExpect(status().isOk());*/
        mvc.perform(post("/test")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON) //application/x-www-form-urlencoded; charset=UTF-8;
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void 테스트_POST() throws Exception {

       // String content = objectMapper.writeValueAsString(new Info("stir", "123"));

        mvc.perform(post("/test2")
                .content("{'name':'stir', 'id':'123'}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("stir AND 123"))
                .andDo(print());
    }
}
