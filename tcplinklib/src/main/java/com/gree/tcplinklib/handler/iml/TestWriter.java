package com.gree.tcplinklib.handler.iml;

import android.util.Log;

import com.gree.tcplinklib.handler.iface.ExceptionHandler;
import com.gree.tcplinklib.handler.iface.WriterHandler;

import java.io.Writer;

/**
 * Created by leesure on 16-12-21.
 */

public class TestWriter implements WriterHandler {
    private Writer writer;
    private ExceptionHandler mExceptionHandler;
    private boolean sendable = true;

    public static final String TAG = "TestWriter";

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void send() {
        String s = "hehe";
        try {
            writer.write(s);
            writer.flush();
        } catch (Exception e) {
            Log.e(TAG, Thread.currentThread() + "    " + "输出流有异常");
            sendable = false;
            mExceptionHandler.dealException();
        }
    }

    @Override
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        mExceptionHandler = exceptionHandler;
    }
}
