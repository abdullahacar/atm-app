package com.atm.service;

import com.atm.config.AppSettings;
import com.atm.config.ApplicationBean;
import com.atm.dto.response.ApiResponse;
import com.atm.dto.response.enums.ApiResponseCode;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.ConnectException;
import java.util.Objects;

public class ApiService {

    private final Client client = ClientBuilder.newClient();
    private final String host = AppSettings.URL();
    protected ApplicationBean applicationBean;
    protected ObjectMapper mapper;

    public ApiService(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;
        mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public <T> ApiResponse<T> post(String url, Object body) {
        try {
            WebTarget postLoginTarget = client.target(host).path(url);
            Invocation.Builder invocationBuilder = postLoginTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON));
            return processResponse(response, url);

        } catch (Exception e) {
            return ApiResponse.<T>builder()
                    .message("Local Exception on post operation {} " + e.getMessage())
                    .apiResponseCode(ApiResponseCode.EXCEPTION)
                    .build();
        }

    }

    private <T> ApiResponse<T> processResponse(Response response, String url) {
        Response.Status status = Response.Status.fromStatusCode(response.getStatus());
        try {

            if (Objects.requireNonNull(status) == Response.Status.OK) {

                String s = response.readEntity(String.class);

                return ApiResponse.<T>builder()
                        .apiResponseCode(ApiResponseCode.SUCCESSFUL)
                        .entityJson(s)
                        .message((status.name()))
                        .build();
            }

            return ApiResponse.<T>builder()
                    .apiResponseCode(ApiResponseCode.ERROR)
                    .message((status.name()))
                    .build();
        } catch (Exception e) {
            return ApiResponse.<T>builder()
                    .apiResponseCode(ApiResponseCode.ERROR)
                    .message((status.name()))
                    .exception(e.getMessage())
                    .build();
        }
    }

}

