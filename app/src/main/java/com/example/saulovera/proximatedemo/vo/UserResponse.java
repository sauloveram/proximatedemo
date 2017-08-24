package com.example.saulovera.proximatedemo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saulovera on 24/8/2017.
 */

public class UserResponse {

    private String success = "";//true,
    private String error = "";//false,
    private String message = "";//"All OK",
    private List<ProfileData> data = new ArrayList<>();//[

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

    public List<ProfileData> getData() {
        return data;
    }

    public void setData(List<ProfileData> data) {
        this.data = data;
    }
}
