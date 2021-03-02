package com.god.economics.instagram.exceptions;

import com.github.instagram4j.instagram4j.utils.IGUtils;
import com.god.economics.instagram.IGResponse;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;

@Getter
public class IGResponseException extends IOException {
    @NonNull
    private IGResponse response;

    public IGResponseException(IGResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    @Data
    public static class IGFailedResponse extends IGResponse {
        private String status = "fail";
        private final String message;

        public static IGResponse of(Throwable throwable) {
            if (throwable instanceof IGResponseException)
                return ((IGResponseException) throwable).getResponse();
            return new IGFailedResponse(throwable.toString());
        }

        public static <T> T of(Throwable throwable, Class<T> clazz) {
            return IGUtils.convertToView(of(throwable), clazz);
        }
    }

}
