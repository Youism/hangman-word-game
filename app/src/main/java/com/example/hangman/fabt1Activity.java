package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

public class fabt1Activity extends AppCompatActivity {

    ArrayAdapter<String> adapter1 = null;
    private String result = "";
    EditText username;
    EditText password;
    EditText repassword;
    EditText phone;
    Button reg;
    ImageButton bck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabt1);

        username = findViewById(R.id.et1);
        password = findViewById(R.id.et2);
        repassword = findViewById(R.id.et3);
        phone = findViewById(R.id.et4);
        reg = findViewById(R.id.fabt1);
        bck = findViewById(R.id.back);

//        final TextView t = (TextView) findViewById(R.id.notice);
//        t.setText("Please input your info！");

        //申明并找到空间
        Spinner sp1 = findViewById(R.id.sp_1);
        String[] wordsbook = new String[]{"大学英语四级", "大学英语六级", "考研", "雅思", "托福", "GRE", "英专", ""};
        //        数据来源数组
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, wordsbook);
        //        adapter设置属性、下拉框长什么样
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //        输出下拉框内容，将适配器与列表关联
        sp1.setAdapter(adapter1);
        //        默认选择
        sp1.setSelection(0, true);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(fabt1Activity.this, MainActivity.class);
                startActivity(intent2);
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Button输入检测
                if (!password.getText().toString().equalsIgnoreCase(repassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        register();
                    }
                }).start();
            }
        });
    }

    private int register() {
        String target = "http://10.101.149.53:8080/athenas_word_userinfo/RegisterServlet";    //要提交的服务器地址`
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
            //Log.i("username", edit_Username.getText().toString());
            DataOutputStream out = new DataOutputStream(
                    urlConn.getOutputStream()); // 获取输出流
            String param = "username="
                    + URLEncoder.encode(username.getText().toString(), "utf-8")
                    + "&userpwd="
                    + URLEncoder.encode(password.getText().toString(), "utf-8")
                    + "&phone="
                    + URLEncoder.encode(phone.getText().toString(), "utf-8");    //连接要提交的数据
            out.writeBytes(param);//将要传递的数据写入数据输出流
            out.flush();    //输出缓存
            out.close();    //关闭数据输出
            Log.i("HttpURLConnection=", "sdf");
            Log.i("urlConn.getResponseCode", String.valueOf(urlConn.getResponseCode()));
            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {  //判断是否响应成功
                Log.i("urlConngetResponseCode=", String.valueOf(urlConn.getResponseCode()));
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
            if ("ok".equals(result)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "注册失败！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
