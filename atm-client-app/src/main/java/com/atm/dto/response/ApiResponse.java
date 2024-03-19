package com.atm.dto.response;

import com.atm.dto.response.enums.ApiResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private T entity;
    private String entityJson;
    private byte[] bytes;
    private ApiResponseCode apiResponseCode;
    private long count;
    private String exception;
    private String message;

    public boolean isSuccessful() {

        if (this.apiResponseCode == null) return false;

        return apiResponseCode.equals(ApiResponseCode.SUCCESSFUL);

    }

    public boolean isNotSuccessful() {

        if (this.apiResponseCode == null) return true;

        return !apiResponseCode.equals(ApiResponseCode.SUCCESSFUL);

    }

}

