package com.wp.TmallMarket.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class UserResponse<T> {
    private List<T> data;
    private boolean isSuccess;
    private String errMsg;

    public static <K> UserResponse<K> newSuccessResponse(List<K> data) {
        UserResponse<K> response = new UserResponse<>();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public static UserResponse<Void> newFailResponse(String errMsg) {
        UserResponse<Void> failUserResponse = new UserResponse<>();
        failUserResponse.setSuccess(false);
        failUserResponse.setErrMsg(errMsg);
        return failUserResponse;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
