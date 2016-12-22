package com.gree.tcplink;

import android.util.Log;

/**
 * Created by leesure on 16-12-21.
 */

public class TestThread extends Thread {
    private String name;
    private String temp;


    public TestThread(String name, String temp) {
        this.name = name;
        this.temp = temp;
    }

    @Override
    public void run() {
        super.run();
        handStr(temp);
    }

    private void handStr(String str) {
        for (int i = 0; i < temp.length(); i++) {
            Log.e("thread", this.currentThread() + temp.substring(i, i + 1));
        }
    }
}
