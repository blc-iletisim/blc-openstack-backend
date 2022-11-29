package com.blc.customerInterface.configuration;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DefaultResponse<T>  implements Serializable {
    private boolean isSuccess;
    private String message;
    private T data;

    public DefaultResponse(boolean isSuccess, String message, T data) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.data = data;
    }

    public DefaultResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }


}
