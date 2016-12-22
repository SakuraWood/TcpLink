package com.gree.tcplinklib.handler.iface;

import java.io.Writer;

/**
 * Created by leesure on 16-12-22.
 */

public class BaseWriterHandler implements WriterHandler {
    private Writer writer;
    private ExceptionHandler mExceptionHandler;

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        mExceptionHandler = exceptionHandler;
    }

    public void dealException() {
        mExceptionHandler.dealException();
    }

    public Writer getWriter() {
        return writer;
    }
}
