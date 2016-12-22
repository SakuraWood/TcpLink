package com.gree.tcplinklib.handler.iface;

import java.io.Writer;

/**
 * Created by leesure on 16-12-21.
 */

public interface WriterHandler {
    void setWriter(Writer writer);

    void setExceptionHandler(ExceptionHandler exceptionHandler);
}
