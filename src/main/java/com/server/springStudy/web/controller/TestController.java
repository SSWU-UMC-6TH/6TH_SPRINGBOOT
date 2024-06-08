package com.server.springStudy.web.controller;

import com.server.springStudy.apiPayload.BaseResponse;
import com.server.springStudy.converter.TestConverter;
import com.server.springStudy.service.TestQueryServiceImpl;
import com.server.springStudy.web.dto.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestQueryServiceImpl testQueryService;

    @GetMapping("/")
    public BaseResponse<String> test() {
        // 현재는 <String> 으로 뒀지만 onSuccess 타입을 제네릭스로 했으므로 DTO나 다른 클래스도 가능
        return BaseResponse.onSuccess("성공!");
    }

    @GetMapping("/test")
    public BaseResponse<TestResponse.TestDTO> testAPI(){

        return BaseResponse.onSuccess(TestConverter.toTestDTO());
    }

    @GetMapping("/exception")
    public BaseResponse<TestResponse.TestExceptionDTO> failedTest(@RequestParam Integer flag) {
        testQueryService.failedTest(flag);
        return BaseResponse.onSuccess(TestConverter.toTestExceptionDTO(flag));
    }
}
