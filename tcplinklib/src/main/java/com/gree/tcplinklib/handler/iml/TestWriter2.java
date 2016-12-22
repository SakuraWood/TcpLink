package com.gree.tcplinklib.handler.iml;

import android.util.Log;

import com.gree.tcplinklib.handler.iface.BaseWriterHandler;

import java.io.Writer;

/**
 * Created by leesure on 16-12-22.
 */

public class TestWriter2 extends BaseWriterHandler {
    private Writer writer;
    public static final String TAG = "TestWriter2";

    public void send() {
        writer = getWriter();
        String s = "hehe2";
        try {
            writer.write(s);
            writer.flush();
        } catch (Exception e) {
            Log.e(TAG, "输出流有异常");
            dealException();
        }
    }
}
