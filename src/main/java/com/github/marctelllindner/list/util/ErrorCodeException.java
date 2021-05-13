package com.github.marctelllindner.list.util;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@EqualsAndHashCode(callSuper = true)
public class ErrorCodeException extends Exception {

    HttpStatus status;
    Object body;

}
