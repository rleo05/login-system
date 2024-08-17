package com.login.login_system.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class MethodArgumentNotValidDetails extends ExceptionDetails{
    private String fields;
    private String fieldsMessage;
}
