package com.example.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseMessage {

    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

}
