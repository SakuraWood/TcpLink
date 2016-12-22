package com.gree.tcplinklib;

import android.content.Context;
import android.util.Log;

import com.gree.tcplinklib.handler.iface.ExceptionHandler;
import com.gree.tcplinklib.handler.iface.ReaderHandler;
import com.gree.tcplinklib.handler.iface.WriterHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by leesure on 16-12-21.
 */
public class TcpLink implements Runnable, ExceptionHandler {
    /**
     * socket及IO相关
     */
    private Socket socket;
    private int port;
    private String ip;
    private BufferedReader br;
    private BufferedWriter bw;

    /**
     * 是否连接，是否屏蔽接收
     */
    private boolean isConnected;
    private boolean recievable;

    /**
     * android相关
     */
    private Context context;

    /**
     * I/O处理相关
     */
    private ReaderHandler mReaderHandler;
    private WriterHandler mWriterHandler;

    private final static String TAG = "TcpLink";

    /**
     * @param context
     * @param ip
     * @param port
     */
    public TcpLink(Context context, String ip, int port) {
        this.context = context;
        this.ip = ip;
        this.port = port;
    }

    public void setReaderHandler(ReaderHandler readerHandler) {
        mReaderHandler = readerHandler;
    }

    public void setWriterHandler(WriterHandler writerHandler) {
        mWriterHandler = writerHandler;
        mWriterHandler.setExceptionHandler(this);
    }

    public ReaderHandler getReaderHandler() {
        return mReaderHandler;
    }

    public WriterHandler getWriterHandler() {
        return mWriterHandler;
    }

    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                isConnected = creatTcpLink();
                recievable = creatIO();
                if (!isConnected) {
                    Log.e(TAG, "tcp未连接");
                    return;
                } else {
                    Log.e(TAG, "建立TCP成功");
                }
                if (!recievable) {
                    Log.e(TAG, "读写流未初始化");
                    return;
                } else {
                    Log.e(TAG, "建立读写流成功");
                }
                //给写入工具类初始化好输入流
                mWriterHandler.setWriter(bw);
                //开始读取
                readStream();
                Log.e(TAG, "初始化writer成功");
            }
        }).start();

    }

    /**
     * 创建tcp连接，若成功则返回true
     *
     * @return
     */
    private boolean creatTcpLink() {
        try {
            socket = new Socket(ip, port);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "tcp连接异常，请检查端口和ip是否正确");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 初始化IO，若有异常则返回false
     *
     * @return
     */
    private boolean creatIO() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            return true;
        } catch (Exception e) {
            Log.e(TAG, "读写流建立异常，请检查网络连接");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 读取IO流
     */
    private void readStream() {
        while (isConnected && recievable) {
            char[] readArray = new char[4096];
            try {
                int readlength = br.read(readArray);
                if (readlength > 0) {
                    recieve(readArray);
                }
            } catch (Exception e) {
                Log.e(TAG, "读取异常");
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理读取到的数据
     *
     * @param readArray
     */
    private void recieve(char[] readArray) {
        /**
         * 数据操作，默认采取字符串处理方式
         */
        if (mReaderHandler == null) {
            Log.e(TAG, "接收的处理类为空");
        } else {
            mReaderHandler.handleReadArray(readArray);
        }
    }

    @Override
    public void dealException() {
        this.isConnected = false;
        this.recievable = false;
        try {
            this.bw.flush();
            this.br.close();
            this.bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (bw != null) {
                bw = null;
            }
            if (br != null) {
                br = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (socket.isConnected() || !socket.isClosed()
                    || !socket.isInputShutdown() || !socket.isOutputShutdown()) {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        run();
    }
}