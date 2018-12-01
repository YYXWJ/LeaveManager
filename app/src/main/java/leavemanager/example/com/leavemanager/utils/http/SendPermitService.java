package leavemanager.example.com.leavemanager.utils.http;

import android.util.Log;


import com.google.gson.Gson;


import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.LeavePermitBeen;
import leavemanager.example.com.leavemanager.been.SendPermitBeen;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.utils.JsonUtil;

public class SendPermitService {
    private static String url = Constants.serverURL+"/m/leaveinfo/approve";
    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    public static boolean SendPermitLeaveInfo(final LeaveInfo leaveInfo,final CallBack callBack){
        new Thread() {
            @Override
            public void run() {
                try {
                    //JSONObject userJSON = JsonUtil.getJsonObject(new Gson().toJson(leaveInfo));
                    JSONObject userJSON = JsonUtil.getJsonObject(new Gson().toJson(leaveInfo));
                    //JSONObject userJSON = JSONObject.fromObject(leaveInfo);
                    //userJSON.put("id", MyApplication.getLoginBeen().getData().get(0).getId()+"");
                    //userJSON.put("id", "100000006");
                    //Log.e("123", String.valueOf(userJSON));
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
                    //String url = "http://10.103.241.30:8085/leaveinfo/creinfo";
                    url = url + "/"+MyApplication.getLoginBeen().getData().get(0).getPersionid();
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
                    //ApplyReceiveBeen applyReceiveBeen = null;
                    //permitFailed();
                    SendPermitBeen sendPermitBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        sendPermitBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if (sendPermitBeen.getCode() == 0) {
                        //LoginSuccessed(loginBeen);
                        if("success".equals(sendPermitBeen.getData())){
                            callBack.onSuccessed();
                        }
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
    private static SendPermitBeen formatData(String msg){
        Gson gson = new Gson();
        return gson.fromJson(msg, SendPermitBeen.class);
    }
}
