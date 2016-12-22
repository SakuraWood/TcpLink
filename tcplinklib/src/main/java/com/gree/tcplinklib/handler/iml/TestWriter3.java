package com.gree.tcplinklib.handler.iml;

import android.util.Log;

import com.gree.tcplinklib.handler.iface.ExceptionHandler;
import com.gree.tcplinklib.handler.iface.WriterHandler;

import java.io.Writer;

/**
 * Created by leesure on 16-12-22.
 */

public class TestWriter3 implements WriterHandler {
    private Writer writer;
    private ExceptionHandler mExceptionHandler;
    public static final String TAG = "TestWriter3";

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        this.mExceptionHandler = exceptionHandler;
    }

    public void send() {
        String s = "hehe3";
        try {
            writer.write(s);
            writer.flush();
        } catch (Exception e) {
            Log.e(TAG, "输出流有异常");
            mExceptionHandler.dealException();
        }
    }
}
