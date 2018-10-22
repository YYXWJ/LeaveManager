package leavemanager.example.com.leavemanager.utils.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.activity.LoginActivity;
import leavemanager.example.com.leavemanager.node.UserInfo;

public class ConnectService {
    private static String serverURL="";
    private static Handler loginHandler = MyApplication.hanlder;
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

                    userJSON.put("userPhone",user.getPhoneNumber());
                    userJSON.put("userPassword",user.getPasswd());
                    //JSONObject object = new JSONObject();
                    //object.put("user",userJSON);

                    String content = String.valueOf(userJSON);

                    //HttpURLConnection connection  =
                    /**
                     * 请求地址
                     */
//                    String url = serverURL+"user/save";
//
//                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//                    //HttpURLConnection connection = (HttpURLConnection) nURL.openConnection();
//                    connection.setConnectTimeout(5000);
//                    connection.setRequestMethod("POST");
//                    connection.setDoOutput(true);
//                    connection.setRequestProperty("User-Agent", "Fiddler");
//                    connection.setRequestProperty("Content-Type", "application/json");
//                    connection.setRequestProperty("Charset", "UTF-8");
//                    OutputStream os = connection.getOutputStream();
//                    os.write(content.getBytes());
//                    os.close();
                    /**
                     * 服务器返回结果
                     * 继续干什么事情....待续
                     */
                    //String result = read(connection.getInputStream());

                    Log.i("success","成功注册");
                    sleep(2000);
                    Message msg = Message.obtain();
                    msg.what = 1;
                    loginHandler.handleMessage(msg);
                }catch (Exception e)
                {

                }
            }
        }.start();
        return true;
    }

}
