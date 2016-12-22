package com.gree.tcplink;

import android.util.Log;

import static java.lang.Thread.currentThread;

/**
 * Created by leesure on 16-12-21.
 */

public class TestRunable implements Runnable {
    private String name;
    private String temp;
    private int i = 500;


    public TestRunable(String name, String temp) {
        this.name = name;
        this.temp = temp;
    }

    @Override
    public void run() {
        handStr(temp);
    }

    private void handStr(String str) {
        i--;
//        for (int i = 0; i < this.i; i++) {
//            Log.e("thread", currentThread() + temp.substring(i, i + 1));
            Log.e("thread", currentThread() + "" + this.i);
//        }
    }
}
