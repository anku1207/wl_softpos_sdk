package com.example.wl_softpos_sdk.common.repository.network;

import retrofit2.HttpException;
import retrofit2.Response;

public class HttpErrorException extends HttpException {

    public HttpErrorException(Response<?> response) {
        super(response);
    }
}
