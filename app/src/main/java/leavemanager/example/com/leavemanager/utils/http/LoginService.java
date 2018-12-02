package leavemanager.example.com.leavemanager.utils.http;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.activity.LoginActivity;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.UserInfo;

public class LoginService {
    public interface CallBack{
        void onSuccess();
        void onFailed();
    }

    private static String url= Constants.serverURL+"/m/persion/login";
    //private static Handler loginHandler = MyApplication.hanlder;
    /**
     * 用户注册
     */
    public static boolean userRegister(final UserInfo user, final LoginService.CallBack callBack)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("username",user.getUsername());
                    userJSON.put("password",user.getPasswd());
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
                    //String url ="http://10.103.241.30:8085/persion/login";
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("User-Agent", "Fiddler");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Charset", "UTF-8");
                    OutputStream os = connection.getOutputStream();
                    os.write(content.getBytes());
                    os.close();
                    connection.connect();
                    int stat = connection.getResponseCode();
                    BufferedReader br = null;
                    LoginBeen loginBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        loginBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if(loginBeen.getCode() == 0){
                        //LoginSuccessed(loginBeen);
                        MyApplication.setLoginBeen(loginBeen);
                        callBack.onSuccess();
                        return;
                    }else{
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    //LoginFailed();
                    callBack.onFailed();
                }
            }
        }.start();
        return true;
    }

//    private static void LoginFailed() {
//        Message msg = Message.obtain();
//        msg.what = MyApplication.LOGIN_FAIL;
//        MyApplication.hanlder.handleMessage(msg);
//    }
//    private static void LoginSuccessed(LoginBeen loginBeen) {
//        Message msg = Message.obtain();
//        msg.what = MyApplication.LOGIN_SUCCESS;
//        msg.obj = loginBeen;
//        MyApplication.hanlder.handleMessage(msg);
//    }


    public static LoginBeen formatData(String msg) throws JSONException {
        Gson gson = new Gson();
       // LoginBeen loginBeen = gson.fromJson(msg, LoginBeen.class);
        return gson.fromJson(msg, LoginBeen.class);

    }

}
