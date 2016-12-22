package com.gree.tcplinklib.handler.iml;

import android.util.Log;

import com.gree.tcplinklib.handler.iface.BaseReaderHandler;
import com.gree.tcplinklib.handler.iface.ExceptionHandler;

/**
 * Created by leesure on 16-12-21.
 */

public class TestReader extends BaseReaderHandler {
    public static final String TAG = "TestReader";
    private ExceptionHandler mExceptionHandler;

    @Override
    public void handleReadArray(char[] readArray) {
        String string = new String(readArray).trim();
        Log.e(TAG, Thread.currentThread() + "    " + string);
    }
}
