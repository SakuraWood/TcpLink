package com.gree.tcplink;

import java.util.ArrayList;

/**
 * Created by leesure on 16-12-21.
 */

public class CreatStr {
    private static ArrayList<String> mArrayList;

    public static ArrayList<String> creat(int i) {
        mArrayList = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            mArrayList.add(appendStr(j));
        }
        return mArrayList;
    }

    private static String appendStr(int i) {
        String string = new String();
        for (int j = 0; j < 500; j++) {
            String str = i + "";
            string = string + str;
        }
        return string;
    }
}
