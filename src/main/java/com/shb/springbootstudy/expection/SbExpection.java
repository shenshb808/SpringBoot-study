package com.shb.springbootstudy.expection;

/**
 * 自定义异常
 */
public class SbExpection extends RuntimeException{

    public SbExpection(String message) {
        super(message);
    }
}
