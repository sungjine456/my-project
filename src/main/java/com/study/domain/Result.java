package com.study.domain;

public class Result {
	private boolean valid;
    private String message;

    private Result(boolean valid) {
        this(valid, "");
    }

    private Result(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public static Result ok() {
        return new Result(true);
    }

    public static Result fail(String message) {
        return new Result(false, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Result [valid=" + valid + ", message=" + message + "]";
    }
}
