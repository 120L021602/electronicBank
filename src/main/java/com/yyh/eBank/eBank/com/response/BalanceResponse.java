package com.yyh.eBank.eBank.com.response;

public class BalanceResponse {

    private Boolean success;//表示登录是否成功

    private String msg = "";//错误信息

    private Integer cents;//余额（多少分）

    public Integer getCents() {
        return cents;
    }

    public void setCents(Integer cents) {
        this.cents = cents;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }







}
