package com.server.springStudy.apiPayload.exception.handler;

import com.server.springStudy.apiPayload.exception.GeneralException;
import com.server.springStudy.apiPayload.code.BaseErrorCode;

public class TestHandler extends GeneralException {

    public TestHandler(BaseErrorCode code) {
        super(code);
    }
}
