package com.example.ig_social_myproject.model.response;

import lombok.Data;

@Data
public class ResponseData {
    private int status = 200;
    private boolean success = true;
    private String message;
    private Object data;
}