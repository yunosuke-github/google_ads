package com.example.google_ads_api.consts;

public enum ErrorCode {
    E001("E-001", "No login session information."),
    E002("E-002", "No token is set in the request header.");

    private String code;
    private String message;

    private ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}
