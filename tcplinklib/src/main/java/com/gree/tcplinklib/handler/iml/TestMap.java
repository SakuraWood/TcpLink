package com.gree.tcplinklib.handler.iml;

import com.gree.tcplinklib.TcpLink;

import java.util.HashMap;

/**
 * Created by leesure on 16-12-22.
 */

public class TestMap {
    public static HashMap<Integer, TcpLink> hashMap;

    public static void init() {
        hashMap = new HashMap<>();
    }
}
