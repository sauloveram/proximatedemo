package com.example.saulovera.proximatedemo.vo;

/**
 * Created by saulovera on 24/8/2017.
 */

public class LoginResponse {

    private String success = "";
    private String error = "";
    private String message = "";
    private String token = "";//""eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiMFVEb1pXOFdzVVlGenRXeSt0RUgxRHRSZVNNWU8xNDhETnVqQWw0Tmc2Q3E2Q0V6eDJMb2JmYUNiREkrUEtQMWExbzF2TUs5dW5mc0xVV0RZOEYxb3BrMkhHRGI1czl6YXBUeHhScFEwc2s9IiwiaWF0IjoxNTAzNTU5MjgwLCJleHAiOjE1MDM1NjI4ODB9.qgbf8jQEvJGTHtxomrRCUJPlJW-0FeUwMKUfWaMeHAs","id":1

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
