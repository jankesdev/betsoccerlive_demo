package betsoccerlive.com.countriesservice.exception.response;

import org.springframework.http.HttpStatus;

public class ApiException {

    private final String message;
    private final HttpStatus error;

    public ApiException(String message, HttpStatus error) {
        this.message = message;
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getError() {
        return error;
    }

}