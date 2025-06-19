package com.example.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.boot.json.JsonParseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandler {

    public void handleException(Exception e) throws SumanException {
        if (e instanceof NullPointerException) {
            throw new SumanException("Null value encountered", HttpStatus.BAD_REQUEST);
        } else if (e instanceof IllegalArgumentException) {
            throw new SumanException("Invalid argument provided", HttpStatus.BAD_REQUEST);
        } else if (e instanceof JsonParseException || e instanceof JsonMappingException) {
            throw new SumanException("Invalid JSON format", HttpStatus.BAD_REQUEST);
        } else if (e instanceof DataIntegrityViolationException) {
            throw new SumanException("Database constraint violated", HttpStatus.BAD_REQUEST);
        } else if (e instanceof NumberFormatException) {
            throw new SumanException("Invalid number format", HttpStatus.BAD_REQUEST);
        } else if (e instanceof UnsupportedOperationException) {
            throw new SumanException("Unsupported operation", HttpStatus.METHOD_NOT_ALLOWED);
        } else if (e instanceof SumanException annapurnaException) {
            throw new SumanException(annapurnaException.getMessage() , annapurnaException.getStatus());
        }  else {
            throw new SumanException("Some other error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}