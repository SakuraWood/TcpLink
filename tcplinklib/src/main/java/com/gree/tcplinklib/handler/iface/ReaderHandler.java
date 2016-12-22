package com.gree.tcplinklib.handler.iface;

/**
 * Created by leesure on 16-12-21.
 */

public interface ReaderHandler {
    void handleReadArray(char[] readArray);

    void setExceptionHandler(ExceptionHandler exceptionHandler);

}
