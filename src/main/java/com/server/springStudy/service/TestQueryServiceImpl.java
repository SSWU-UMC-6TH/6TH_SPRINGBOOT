package com.server.springStudy.service;

import com.server.springStudy.apiPayload.code.status.ErrorStatus;
import com.server.springStudy.apiPayload.exception.handler.TestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestQueryServiceImpl implements TestQueryService{

    @Override
    public void failedTest(Integer flag) {
        if (flag == 1)
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
    }
}
