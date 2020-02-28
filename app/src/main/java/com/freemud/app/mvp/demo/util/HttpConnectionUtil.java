package com.freemud.app.mvp.demo.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by li.liu on 2018/5/31.
 * 原生请求网络
 */

public class HttpConnectionUtil {
    public static HttpConnectionUtil http = new HttpConnectionUtil();

    public static HttpConnectionUtil getHttp() {
        return http;
    }

    public static String getRequset(final String url) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL requestUrl = new URL(url);
                    connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    if (connection.getResponseCode() == 200) {
                        InputStream in = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(in));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } else {
                        return "请求失败";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                    if (connection != null) {
                        connection.disconnect();//断开连接，释放资源
                    }
                }
                return sb.toString();
            }
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String postRequset( String url,  Map<String, Object> map) {
        final StringBuilder sb = new StringBuilder();
        FutureTask<String> task = new FutureTask<>(() -> {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL requestUrl = new URL(url);
                connection = (HttpURLConnection) requestUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setConnectTimeout(8000);//链接超时
                connection.setReadTimeout(8000);//读取超时
                //发送post请求必须设置
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setUseCaches(false);
                connection.setInstanceFollowRedirects(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("X-Customer-Id", "123");
                OutputStream out = connection.getOutputStream();
                StringBuilder request = new StringBuilder();
                for (String key : map.keySet()) {
                    request.append(key + "=" + URLEncoder.encode(String.valueOf(map.get(key)), "UTF-8") + "&");
                }
                String req = request.substring(0, request.length() - 1);
                out.write(req.getBytes());//写入请求参数
                out.flush();
             //   out.close();
                LogUtils.i(req);
                LogUtils.i("connection=" + connection);
                LogUtils.i("code=" + connection.getResponseCode());
                if (connection.getResponseCode() == 200) {
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                } else {
                    return "请求失败";
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader.close();//关闭流
                }
                if (connection != null) {
                    connection.disconnect();//断开连接，释放资源
                }
            }
            return sb.toString();
        });
        new Thread(task).start();
        String s = null;
        try {
            s = task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public static String doPost(String urlPath, String json) {
        // HttpClient 6.0被抛弃了
        String result = "";
        BufferedReader reader = null;
        OutputStream outputStream = null;
        InputStreamReader inputStreamReader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //conn.setUseCaches(false);
            conn.setRequestProperty("Charset", "UTF-8");
            // 设置文件类型:
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=UTF-8");
            // 设置接收类型否则返回415错误
            // conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
            conn.setRequestProperty("accept", "application/json");
            // 往服务器里面发送数据

            if (json != null && json.length() > 0) {
                byte[] writebytes = json.getBytes();
                // 设置文件长度
                conn.setRequestProperty("Content-Length",
                        String.valueOf(writebytes.length));
                outputStream = conn.getOutputStream();
                outputStream.write(writebytes);
                outputStream.flush();
                outputStream.close();
            }
            if (conn.getResponseCode() == 200) {
                inputStreamReader = new InputStreamReader(conn.getInputStream());
                reader = new BufferedReader(inputStreamReader);
                result = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
