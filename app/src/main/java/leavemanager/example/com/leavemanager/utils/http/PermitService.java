package leavemanager.example.com.leavemanager.utils.http;

import android.os.Message;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.UserInfo;

public class PermitService {
    private static String serverURL="http://118.190.83.62:8085";
    //private static Handler loginHandler = MyApplication.hanlder;
    /**
     * 用户注册
     */
    public static boolean userRegister(final UserInfo user)
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
                    String url ="http://192.168.1.102:8085/persion/login";
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
                        LoginFailed();
                        return;
                    }
                    if(loginBeen.getCode() == 0){
                        LoginSuccessed(loginBeen);
                        return;
                    }else{
                        LoginFailed();
                        return;
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    LoginFailed();
                }
            }
        }.start();
        return true;
    }

    private static LoginBeen formatData(String s) {
    }
    private static void LoginFailed() {
    }
    private static void LoginSuccessed(LoginBeen loginBeen) {
    }
}
