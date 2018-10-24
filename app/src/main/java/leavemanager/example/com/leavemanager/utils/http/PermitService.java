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
import java.util.ArrayList;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.ApplyReceiveBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.ReceiveLeaveInfo;

public class PermitService {
    private static String serverURL="http://118.190.83.62:8085";
    //private static Handler loginHandler = MyApplication.hanlder;
    /**
     * 用户注册
     */
    public static boolean permitLeaveInfo(final ReceiveLeaveInfo receiveLeaveInfo)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("applyPersons",receiveLeaveInfo.getApplyPersons());
                    userJSON.put("startTime",receiveLeaveInfo.getStartTime());
                    userJSON.put("endTime",receiveLeaveInfo.getEndTIme());
                    userJSON.put("place",receiveLeaveInfo.getPlace());
                    userJSON.put("event",receiveLeaveInfo.getEvent());
                    userJSON.put("permitPerson",receiveLeaveInfo.getPermitPerson());
                    userJSON.put("permitTime",receiveLeaveInfo.getPermitTime());
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
                    String url ="http://10.103.241.30:8085/leaveinfo/creinfo";
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
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        applyReceiveBeen = formatData(br.readLine());
                    } else {
                        permitFailed();
                        return;
                    }
                    if(applyReceiveBeen.getCode() == 0){
                        permitSuccessed(applyReceiveBeen);
                        return;
                    }else{
                        permitFailed();
                        return;
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    permitFailed();
                }
            }
        }.start();
        return true;
    }

    private static ApplyReceiveBeen formatData(String msg) {
        Gson gson = new Gson();
//        ApplyReceiveBeen applyReceiveBeen = gson.fromJson(msg, ApplyReceiveBeen.class);
//        Log.e("123","loginBeen--"+applyReceiveBeen.getMsg());
//        Log.e("123","loginBeen--"+applyReceiveBeen.getCode());
//        Object data = applyReceiveBeen.getData();
        return gson.fromJson(msg, ApplyReceiveBeen.class);
    }
    private static void permitFailed() {
        Message msg = Message.obtain();
        msg.what = MyApplication.PERMIT_FAIL;
        MyApplication.hanlder.handleMessage(msg);
    }
    private static void permitSuccessed(ApplyReceiveBeen applyReceiveBeen) {
        Message msg = Message.obtain();
        msg.what = MyApplication.PERMIT_SUCCESS;
        MyApplication.hanlder.handleMessage(msg);

    }
}
