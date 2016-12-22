package com.gree.tcplinklib.handler.iface;

/**
 * Created by leesure on 16-12-22.
 */

public abstract class BaseReaderHandler implements ReaderHandler {
    private ExceptionHandler mExceptionHandler;

    @Override
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        mExceptionHandler = exceptionHandler;
    }
}
