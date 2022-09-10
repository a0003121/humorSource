package com.demo.humorsource.enums;

public enum StatusCode {
    SUCCESS(200, "success"), Error(500, "server busy");

    private int code;
    private String message;
    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
