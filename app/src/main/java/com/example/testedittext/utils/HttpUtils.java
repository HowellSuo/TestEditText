package com.example.testedittext.utils;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * 网络工具类
 */
public class HttpUtils {
    private static String reponse;

    public static void sendHttpRequest(final String urlString, final HttpCallBackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(urlString);
                    conn = (HttpURLConnection) url.openConnection();
                    //请求时长
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");

                    reponse=null;
                    if ((conn.getResponseCode())==200) {
                        InputStream is = conn.getInputStream();
                        InputStreamReader reader = new InputStreamReader(is);
                        BufferedReader bufferedReader = new BufferedReader(reader);
                        String line = "";
                        StringBuilder builder = new StringBuilder();
                        while ((line = bufferedReader.readLine()) != null) {
                            builder.append(line);
                        }
                        reponse = builder.toString();

                        if(listener !=null){
                            //结束返回response
                            listener.onFinish(reponse);
                        }
                    }

                } catch (Exception e) {
                    if(listener !=null){
                        listener.onError(e);
                    }
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();

    }
}
