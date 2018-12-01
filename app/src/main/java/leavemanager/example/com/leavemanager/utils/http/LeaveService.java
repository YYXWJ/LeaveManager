package leavemanager.example.com.leavemanager.utils.http;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;
import leavemanager.example.com.leavemanager.been.ApplyReceiveBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.been.SendLeaveInfoBeen;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.node.ReceiveLeaveInfo;
import leavemanager.example.com.leavemanager.utils.JsonUtil;

public class LeaveService {
    private static String url = Constants.serverURL+"/m/leaveinfo/creinfo";
    //private static Handler loginHandler = MyApplication.hanlder;
    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    /**
     * 用户注册
     */
    public static boolean permitLeaveInfo(final LeaveInfo leaveInfo, final CallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    //JSONObject userJSON = new JSONObject();
                    JSONObject userJSON = JsonUtil.getJsonObject(new Gson().toJson(leaveInfo));
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
                    //String url = "http://10.103.241.30:8085/leaveinfo/creinfo";
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
                    ApplyReceiveBeen applyReceiveBeen = null;
                    //permitFailed();
                    SendLeaveInfoBeen sendLeaveInfoBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        sendLeaveInfoBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if (sendLeaveInfoBeen.getCode() == 0) {
                        //LoginSuccessed(loginBeen);
                        if("success".equals(sendLeaveInfoBeen.getData())){
                            callBack.onSuccessed();
                        }
                        callBack.onFailed();
                        return;
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    }catch(Exception e)
                    {
                        e.printStackTrace();
                        //permitFailed();
                        callBack.onFailed();
                    }
                }
            }.start();
        return true;
        }

    private static SendLeaveInfoBeen formatData(String msg) {
        Gson gson = new Gson();
        //SendLeaveInfoBeen sendLeaveInfoBeen = gson.fromJson(msg, SendLeaveInfoBeen.class);
        return gson.fromJson(msg, SendLeaveInfoBeen.class);
    }

    private static void LoginFailed() {
    }

    private static void LoginSuccessed(LoginBeen loginBeen) {
    }
}
