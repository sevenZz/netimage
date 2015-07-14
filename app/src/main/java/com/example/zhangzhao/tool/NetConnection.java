package com.example.zhangzhao.tool;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by zhangzhao on 2015/1/7.
 */
public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback, final FailCallback failCallback, final String... kvs){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                StringBuffer stringBuffer = new StringBuffer();
                for(int i = 0; i < kvs.length; i+=2){
                    stringBuffer.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }
                try{
                    URLConnection uc;
                    switch (method){
                    case POST:
                        uc = new URL(url).openConnection();
                        uc.setDoOutput(true);
                        uc.setReadTimeout(5000);
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                        bw.write(stringBuffer.toString());
                        bw.flush();

                        Log.d("url???POST", uc.toString());
                        break;
                    default:
                        uc = new URL(url + "?" + stringBuffer.toString()).openConnection();

                        Log.d("url???GET", uc.toString());
                        break;
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while((line = br.readLine()) != null){
                        result.append(line);
                    }
                    System.out.println(result);

                    return result.toString();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null){
                    if (successCallback != null){
                        successCallback.onSuccess(result);
                    }
                }else{
                    if (failCallback != null){
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        }.execute();
    }






    public static interface SuccessCallback{
        void onSuccess(String result);
    }
    public static interface FailCallback{
        void onFail(int errorCode);
    }

//    public String getText() throws Exception {
//        String path = "http://10.202.101.231:81/Output.ashx?id=6";
//        URL url = new URL(path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//       conn.setConnectTimeout(5000);
//        conn.setRequestMethod("GET");
//
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//
//        if (conn.getResponseCode() == 200){
//            InputStream inStream = conn.getInputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while((len = inStream.read(buffer)) != -1){
//                outStream.write(buffer, 0, len);
//            }
//
//            byte[] data = outStream.toByteArray();
//
//
//            return new String(data);
//        }
//        return null;
//
//    }
}
