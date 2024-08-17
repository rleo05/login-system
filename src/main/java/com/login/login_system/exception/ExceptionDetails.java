package com.login.login_system.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Data
public class ExceptionDetails {
    private String title;
    private LocalDate timestamp;
    private int status;
    private String details;
}
