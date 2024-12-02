package com.rga.clients.app.config;

import com.rga.clients.app.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
@Slf4j
public class RestResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError() || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is5xxServerError()) {
            if (HttpStatus.SERVICE_UNAVAILABLE == response.getStatusCode()) {
                throw new ServiceUnAvailableException("Service is currently unavailable");
            } else if (HttpStatus.INTERNAL_SERVER_ERROR == response.getStatusCode()){
                throw new UnexpectedErrorException("Internal server error");
            } else {
                log.error("Unexpected");
                throw new HttpClientErrorException(response.getStatusCode(), response.getStatusText());
            }
        } else if (response.getStatusCode().is4xxClientError()) {
            if (HttpStatus.UNAUTHORIZED == response.getStatusCode()) {
                throw new UnauthorizedAccessException("Unauthorized access");
            } else if (HttpStatus.NOT_FOUND == response.getStatusCode()) {
                throw new ClientNotFoundException(response.getStatusText());
            } else if (HttpStatus.BAD_REQUEST == response.getStatusCode()) {
                log.warn("BAD REQUEST:{}", response.getStatusText());
                throw new BadRequestException(response.getStatusText());
            } else {
                log.error("Unexpected client error.\n Code:{}\nMessage:{}",
                        response.getStatusCode(),
                        response.getStatusText());
                throw new UnexpectedErrorException(response.getStatusText());
            }
        }
    }
}
