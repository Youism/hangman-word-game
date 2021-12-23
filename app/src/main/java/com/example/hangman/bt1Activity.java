package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class bt1Activity extends AppCompatActivity {
    public static String id="";
    private String result = ""; // 定义一个代表显示内容的字符串
    //EditText
    EditText username;
    EditText pwd;

    //Button
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt1);

//        final TextView t = (TextView) findViewById(R.id.notice);
        username=(EditText) findViewById(R.id.et1);
        pwd=(EditText) findViewById(R.id.et2);
        bt1 = findViewById(R.id.go);
//        t.setText("请输入用户名与密码！");

        //实现单击查询按钮，发送信息与服务器交互
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当为空时给出相应提示
                if ("".equals(username.getText().toString())
                        || "".equals(pwd.getText().toString())) {
                    Toast.makeText(bt1Activity.this, "请填写用户名和密码！",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                id=username.getText().toString();

                new Thread(new Runnable() {  // 创建一个新线程
                    public void run() {
                        send();     //调用send()方法，
                    }
                }).start();     // 开启线程
            }
        });
    }
    public void send() {

        String target = "http://10.101.149.53:8080/athenas_word_userinfo/LoginServlet";    //要提交的服务器地址`
        URL url;

        try {
            url = new URL(target);  //创建URL对象
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();  // 创建一个HTTP连接
            urlConn.setRequestMethod("POST"); // 指定使用POST请求方式
            urlConn.setDoInput(true); // 向连接中写入数据
            urlConn.setDoOutput(true); // 从连接中读取数据
            urlConn.setUseCaches(false); // 禁止缓存
            urlConn.setInstanceFollowRedirects(true);    //自动执行HTTP重定向
            urlConn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded"); // 设置内容类型
//            Log.i("username", edit_Username.getText().toString());
            DataOutputStream out = new DataOutputStream(
                    urlConn.getOutputStream()); // 获取输出流
            String param = "username="
                    + URLEncoder.encode(username.getText().toString(), "utf-8")
                    + "&userpwd="
                    + URLEncoder.encode(pwd.getText().toString(), "utf-8");    //连接要提交的数据
            out.writeBytes(param);//将要传递的数据写入数据输出流
            out.flush();    //输出缓存
            out.close();    //关闭数据输出流
            Log.i("HttpURLConnection=", "sdf");
            Log.i("urlConn.getResponseCode",String.valueOf(urlConn.getResponseCode()) );
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {  //判断是否响应成功
                Log.i("urlConngetResponseCode=",String.valueOf(urlConn.getResponseCode()) );
                InputStreamReader in = new InputStreamReader(
                        urlConn.getInputStream()); // 获得读取的内容
                BufferedReader buffer = new BufferedReader(in); // 获取输入流对象
                String inputLine = null;
                while ((inputLine = buffer.readLine()) != null) {  //通过循环逐行读取输入流中的内容
                    result = inputLine;
                }
                in.close();    //关闭字符输入流
            }
            Log.i("result=", result);
            if("ok".equalsIgnoreCase(result)){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    }
                });
                Intent  intent = new Intent(bt1Activity.this,GotoActivity.class);
                startActivity(intent);
            }else{
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"登录失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            urlConn.disconnect();    //断开连接
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
