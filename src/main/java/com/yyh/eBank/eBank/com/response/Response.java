package com.yyh.eBank.eBank.com.response;

import lombok.Data;

@Data
public class Response {

    private Boolean success;

    private String msg;

    public Response() {
        this.success = false;
        this.msg = "";
    }

    public Response(String msg) {
        this.success = false;
        this.msg = msg;
    }
}
