package com.challenge.out;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

    private Object entity;

    private Integer statusCode;

    private String msg;

    private boolean error;

    private String detailedError;

    public Response(Object entity, ResponseCode code) {
        this.entity = entity;
        this.statusCode = code.getStatusCode();
        this.msg = code.getText();
        this.error = false;
        this.detailedError = null;
    }

    public Response(ResponseCode code, String detailedError) {
        this.statusCode = code.getStatusCode();
        this.msg = code.getText();
        this.error = true;
        this.detailedError = detailedError;
    }
}
