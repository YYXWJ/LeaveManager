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

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.ApplyReceiveBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.ReceiveLeaveInfo;

public class PermitService {
    private static String serverURL = "http://118.190.83.62:8085";
    //private static Handler loginHandler = MyApplication.hanlder;
    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    /**
     * 用户注册
     */
    public static boolean permitLeaveInfo(final ReceiveLeaveInfo receiveLeaveInfo,final CallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("applyPersons", receiveLeaveInfo.getApplyPersons());
                    userJSON.put("startTime", receiveLeaveInfo.getStartTime());
                    userJSON.put("endTime", receiveLeaveInfo.getEndTIme());
                    userJSON.put("place", receiveLeaveInfo.getPlace());
                    userJSON.put("event", receiveLeaveInfo.getEvent());
                    userJSON.put("permitPerson", receiveLeaveInfo.getPermitPerson());
                    userJSON.put("permitTime", receiveLeaveInfo.getPermitTime());
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
                    String url = "http://10.103.241.30:8085/leaveinfo/creinfo";
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
                    LoginBeen loginBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        loginBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if (loginBeen.getCode() == 0) {
                        //LoginSuccessed(loginBeen);
                        callBack.onSuccessed();
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

    private static LoginBeen formatData(String s) {
        return null;
    }

    private static void LoginFailed() {
    }

    private static void LoginSuccessed(LoginBeen loginBeen) {
    }
}
