package com.hemanth.Roles.globalHandler;

import com.hemanth.Roles.exception.EntryInvalidException;
import com.hemanth.Roles.messageFormat.MessageFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.*;

@ControllerAdvice
public class GlobalExcpetionHandler
{

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }

        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler
    ResponseEntity<MessageFormat> handleException(EntryInvalidException en)
    {
        MessageFormat messageFormat=new MessageFormat();
        messageFormat.setStatusCode(HttpStatus.NOT_FOUND.value());
        messageFormat.setMessage(en.getMessage());
        messageFormat.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(messageFormat,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    ResponseEntity<MessageFormat> handleDateException(DateTimeParseException dt)
    {
        MessageFormat messageFormat=new MessageFormat();
        messageFormat.setStatusCode(HttpStatus.BAD_REQUEST.value());
        messageFormat.setMessage(dt.getMessage());
        messageFormat.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(messageFormat,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    ResponseEntity<MessageFormat> handleRoleIdException(NoSuchElementException dt)
    {
        MessageFormat messageFormat=new MessageFormat();
        messageFormat.setStatusCode(HttpStatus.BAD_REQUEST.value());
        messageFormat.setMessage("The RoleId Entered is Not Proper");
        messageFormat.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(messageFormat,HttpStatus.NOT_FOUND);

    }




}
