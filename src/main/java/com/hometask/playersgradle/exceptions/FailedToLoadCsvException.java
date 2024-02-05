package com.hometask.playersgradle.exceptions;

public class FailedToLoadCsvException extends RuntimeException {
    public FailedToLoadCsvException(String msg) {
        super(msg);
    }
}
