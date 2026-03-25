package com.back.code_brew.domain.home.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@Tag(name = "Home", description = "메인 페이지")
public class HomeController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @Operation(summary = "메인 페이지", description = "서버 정보 및 주요 페이지 이동")
    public String home() throws UnknownHostException {

        InetAddress localhost = InetAddress.getLocalHost();

        return """
                <h1>Welcome to Code Brew</h1>
                <p>Server IP Address: %s</p>
                <p>Server Host Name: %s</p>
                
                <div>
                    <h3>📦 주문 기능</h3>
                    <a href="/orders/create">주문 생성 페이지</a><br/>
                </div>
                
                <div>
                    <h3>📄 API 문서</h3>
                    <a href="/swagger-ui/index.html">Swagger 문서 보기</a>
                </div>
                """.formatted(localhost.getHostAddress(), localhost.getHostName());
    }

    @GetMapping(value = "/test/fetchData", produces = MediaType.TEXT_HTML_VALUE)
    public String testFetch() {

        return """
                <script>
                    console.clear();
                    
                    fetch("http://localhost:8080/orders")
                    .then(response => console.log("orders 요청 결과:", response));
                    
                </script>
                """;
    }
}