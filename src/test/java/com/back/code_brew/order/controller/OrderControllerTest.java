package com.back.code_brew.order.controller;

import com.back.code_brew.domain.order.repository.OrderRepository;
import com.back.code_brew.domain.order.service.OrderService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class OrderControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("주문 생성 성공")
    void test1() throws Exception{
        ResultActions resultActions = mvc
                .perform(
                        post("/orders")
                                .param("name","홍길동")
                                .param("email","test@test.com")
                                .param("address","서울")
                )
                .andDo(print());
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", startsWith("/orders/")));
    }

    @Test
    @DisplayName("실패 - 이름 없음")
    void test2() throws Exception{
        ResultActions resultActions = mvc
                .perform(
                        post("/orders")
                                .param("name","")
                                .param("email","test@test.com")
                                .param("address","서울")
                )
                .andDo(print());
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("create"));
    }

    @Test
    @DisplayName("실패 - 이메일 없음")
    void test3() throws Exception{
        ResultActions resultActions = mvc
                .perform(
                        post("/orders")
                                .param("name","홍길동")
                                .param("email","wrong-email")
                                .param("address","서울")
                )
                .andDo(print());
        resultActions
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("실패 - 주소 없음")
    void test4() throws Exception{
        ResultActions resultActions = mvc
                .perform(
                        post("/orders")
                                .param("name","홍길동")
                                .param("email","test@test.com")
                                .param("address","")
                )
                .andDo(print());
        resultActions
                .andExpect(status().isOk());
    }


}
