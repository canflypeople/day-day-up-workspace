package com.zmji.util.thread;

/**
 * @author : zhongmou.ji
 * @date : 2022/1/28 1:44 下午
 **/
public class StopPooledThreadException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StopPooledThreadException(String msg) {
        super(msg, null, false, false);
    }
}
