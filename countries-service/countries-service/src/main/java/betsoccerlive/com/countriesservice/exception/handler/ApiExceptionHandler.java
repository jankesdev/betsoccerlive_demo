package betsoccerlive.com.countriesservice.exception.handler;

import betsoccerlive.com.countriesservice.exception.*;
import betsoccerlive.com.countriesservice.exception.response.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiException notFoundException(NotFoundException e) {
        return new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException alreadyExistsException(AlreadyExistsException e) {
        return new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException badRequestException(BadRequestException e) {
        return new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
