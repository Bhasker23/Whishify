package com.wishify.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetails {

    private String message;
    private String desc;
    private LocalDateTime date;
}
