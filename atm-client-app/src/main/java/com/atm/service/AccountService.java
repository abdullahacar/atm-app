package com.atm.service;

import com.atm.config.ApplicationBean;
import com.atm.dto.CardAndUserDTO;
import com.atm.dto.CardDTO;
import com.atm.dto.CardQueryModel;
import com.atm.dto.response.ApiResponse;
import com.atm.dto.response.enums.ApiResponseCode;
import com.fasterxml.jackson.databind.JavaType;

public final class AccountService extends ApiService {

    public AccountService(ApplicationBean applicationBean) {
        super(applicationBean);
        System.out.println("Card Service Bean initialized...");
    }

    public ApiResponse<CardAndUserDTO> getCardAndUser(CardQueryModel queryModel) {

        try {
            ApiResponse<CardAndUserDTO> response = this.post("/account/getCardAndUser", queryModel);

            JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, CardAndUserDTO.class);

            return mapper.readValue(response.getEntityJson(), type);

        } catch (Exception e) {
            return ApiResponse.<CardAndUserDTO>builder()
                    .apiResponseCode(ApiResponseCode.EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }


    }

    public ApiResponse<CardDTO> updateBalance(CardQueryModel queryModel) {

        try {
            ApiResponse<CardAndUserDTO> response = this.post("/account/updateBalance", queryModel);

            JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, CardAndUserDTO.class);

            return mapper.readValue(response.getEntityJson(), type);

        } catch (Exception e) {
            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }


    }

    public ApiResponse<CardDTO> updatePin(CardQueryModel queryModel) {

        try {
            ApiResponse<CardAndUserDTO> response = this.post("/account/updatePin", queryModel);

            JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, CardAndUserDTO.class);

            return mapper.readValue(response.getEntityJson(), type);

        } catch (Exception e) {
            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }


    }

    public ApiResponse<CardDTO> validatePin(CardQueryModel queryModel) {

        try {
            ApiResponse<CardAndUserDTO> response = this.post("/account/validatePin", queryModel);

            JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, CardAndUserDTO.class);

            return mapper.readValue(response.getEntityJson(), type);

        } catch (Exception e) {
            return ApiResponse.<CardDTO>builder()
                    .apiResponseCode(ApiResponseCode.EXCEPTION)
                    .message(e.getMessage())
                    .build();
        }

    }

}
