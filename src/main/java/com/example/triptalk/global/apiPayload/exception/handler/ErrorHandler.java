package com.example.triptalk.global.apiPayload.exception.handler;

import com.example.triptalk.global.apiPayload.exception.GeneralException;

public class ErrorHandler extends GeneralException {
    public ErrorHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}

