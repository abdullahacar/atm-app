package com.atm.atmserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ApiResponse<T> {

    private T entity;
    private String entityJson;
    private ApiResponseCode apiResponseCode;
    private String message;
    private String exception;


    public boolean isSuccessful() {
        return apiResponseCode.equals(ApiResponseCode.SUCCESSFUL);
    }

}
