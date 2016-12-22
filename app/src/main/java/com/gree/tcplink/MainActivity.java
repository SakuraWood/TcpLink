package com.gree.tcplink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gree.tcplinklib.TcpLink;
import com.gree.tcplinklib.handler.iml.TestMap;
import com.gree.tcplinklib.handler.iml.TestReader;
import com.gree.tcplinklib.handler.iml.TestWriter;
import com.gree.tcplinklib.handler.iml.TestWriter2;
import com.gree.tcplinklib.handler.iml.TestWriter3;

import java.util.Timer;
import java.util.TimerTask;

import static com.gree.tcplinklib.handler.iml.TestMap.hashMap;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<String> arrayList = CreatStr.creat(4);
////        TestThread thread1 = new TestThread("Thread  1", arrayList.get(0));
////        TestThread thread2 = new TestThread("Thread  2", arrayList.get(1));
////        TestThread thread3 = new TestThread("Thread  3", arrayList.get(2));
////        TestThread thread4 = new TestThread("Thread  4", arrayList.get(3));
////
////        thread1.start();
////        thread2.start();
////        thread3.start();
////        thread4.start();
//
//
//        TestRunable testRunable1 = new TestRunable("Thread  1", arrayList.get(0));
////        TestRunable testRunable2 = new TestRunable("Thread  2", arrayList.get(1));
////        TestRunable testRunable3 = new TestRunable("Thread  3", arrayList.get(2));
////        TestRunable testRunable4 = new TestRunable("Thread  4", arrayList.get(3));
//
//        new Thread(testRunable1).start();
//        new Thread(testRunable1).start();
//        new Thread(testRunable1).start();
//        new Thread(testRunable1).start();
        final TcpLink tcpLink = new TcpLink(this, "10.0.0.12", 8086);
        TestReader testReader = new TestReader();
        TestWriter testWriter = new TestWriter();
        tcpLink.setReaderHandler(testReader);
        tcpLink.setWriterHandler(testWriter);
        new Thread(tcpLink).start();


        final TcpLink tcpLink2 = new TcpLink(this, "10.0.0.12", 8087);
        TestReader testReader2 = new TestReader();
        TestWriter2 testWriter2 = new TestWriter2();
        tcpLink2.setReaderHandler(testReader2);
        tcpLink2.setWriterHandler(testWriter2);
        new Thread(tcpLink2).start();


        final TcpLink tcpLink3 = new TcpLink(this, "10.0.0.12", 8088);
        TestReader testReader3 = new TestReader();
        TestWriter3 testWriter3 = new TestWriter3();
        tcpLink3.setReaderHandler(testReader3);
        tcpLink3.setWriterHandler(testWriter3);
        new Thread(tcpLink3).start();


        TestMap.init();
        hashMap.put(8086, tcpLink);
        hashMap.put(8087, tcpLink2);
        hashMap.put(8088, tcpLink3);


        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                ((TestWriter) hashMap.get(8086).getWriterHandler()).send();
            }
        };
        timer.schedule(timerTask, 3000, 3000);

        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                ((TestWriter2) hashMap.get(8087).getWriterHandler()).send();
            }
        };
        timer2.schedule(timerTask2, 3000, 3000);


        Timer timer3 = new Timer();
        TimerTask timerTask3 = new TimerTask() {
            @Override
            public void run() {
                ((TestWriter3) hashMap.get(8088).getWriterHandler()).send();
            }
        };
        timer3.schedule(timerTask3, 3000, 3000);
    }
}
