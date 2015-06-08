package com.example.zhangzhao.tool;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangzhao on 2015/1/19.
 */
public class ToolAgain {
    public OutputStream getText() throws Exception {
        String path = "http://10.202.101.231:81/Output.ashx?id=6";
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");

        OutputStream outStream = new ByteArrayOutputStream();

        if (conn.getResponseCode() == 200){
            return outStream;
        }
        return null;

    }
}
