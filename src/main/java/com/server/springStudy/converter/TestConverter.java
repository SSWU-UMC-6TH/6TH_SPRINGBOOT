package com.server.springStudy.converter;

import com.server.springStudy.web.dto.TestResponse;

public class TestConverter {
    public static TestResponse.TestDTO toTestDTO(){
        return TestResponse.TestDTO.builder()
                .testString("This is Test!")
                .build();
    }

    public static TestResponse.TestExceptionDTO toTestExceptionDTO(Integer flag){
        return TestResponse.TestExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
