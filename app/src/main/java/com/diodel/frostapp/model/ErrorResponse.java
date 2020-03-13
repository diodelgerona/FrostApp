package com.diodel.frostapp.model;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("error")
    public ErrorResponse.Error errors = null;

    public class Error {

        private int code;
        private  String message;
        private String reason;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }

}
