package org.exeplm.exception;

/**
 * 自定义异常类
 */
public class IllegalTokenException extends RuntimeException{

    public IllegalTokenException(String message) {
        super(message);
    }
}
