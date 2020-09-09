package betsoccerlive.com.typingservice.exception.handler;

import betsoccerlive.com.typingservice.exception.*;
import betsoccerlive.com.typingservice.exception.response.ApiException;
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

    @ExceptionHandler(value = NotNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException notNullException(NotNullException e) {
        return new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException badRequestException(BadRequestException e) {
        return new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AlreadyUpdateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiException alreadyUpdateException(AlreadyUpdateException e) {
        return new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
