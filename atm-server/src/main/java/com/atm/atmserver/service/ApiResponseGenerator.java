package com.atm.atmserver.service;

import com.atm.atmserver.dto.ApiResponse;
import com.atm.atmserver.dto.ApiResponseCode;
import com.atm.atmserver.dto.DBResult;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ApiResponseGenerator<T> {


    public static <T> ApiResponse<T> ok(DBResult<T> result) {
        return ApiResponse.<T>builder()
                .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                .entity(result.getEntity())
                .message(result.getMessage())
                .build();

    }

    public static <T> ApiResponse<T> databaseWarning(String message) {
        return ApiResponse.<T>builder()
                .apiResponseCode(ApiResponseCode.WARNING)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> warning(String message) {
        return ApiResponse.<T>builder()
                .apiResponseCode(ApiResponseCode.WARNING)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> databaseError(String message) {
        return ApiResponse.<T>builder()
                .apiResponseCode(ApiResponseCode.ERROR)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> exception(Exception e) {
        return ApiResponse.<T>builder()
                .apiResponseCode(ApiResponseCode.ERROR)
                .message(e.getLocalizedMessage())
                .exception(ExceptionUtils.getStackTrace(e))
                .build();
    }

    public static <T> ApiResponse<T> generate(DBResult<T> result) {
        switch (result.getCode()) {
            case OK:
                return ok(result);
            case WARNING:
                return databaseWarning(result.getMessage());
            case ERROR:
                return databaseError(result.getMessage());
        }
        return databaseError(result.getMessage());
    }
}
