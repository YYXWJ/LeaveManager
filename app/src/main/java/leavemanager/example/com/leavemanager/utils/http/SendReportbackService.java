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
import leavemanager.example.com.leavemanager.been.LeavePermitBeen;
import leavemanager.example.com.leavemanager.been.SendReportbackBeen;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.utils.JsonUtil;

public class SendReportbackService {
    private static String url = Constants.serverURL+"/m/leaveinfo/updaterunningleave";

    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    public static boolean SendReportbackLeaveInfo(final LeaveInfo leaveInfo, final CallBack callBack){
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONObject userJSON = JsonUtil.getJsonObject(new Gson().toJson(leaveInfo));
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    Log.e("Ser","content----"+content);
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
                    SendReportbackBeen sendReportbackBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        sendReportbackBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if (sendReportbackBeen.getCode() == 0) {
                        //LoginSuccessed(loginBeen);
                        if("success".equals(sendReportbackBeen.getData())){
                            callBack.onSuccessed();
                        }
                        //callBack.onSuccessed(leavePermitBeen);
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
    private static SendReportbackBeen formatData(String msg){
        Gson gson = new Gson();
        return gson.fromJson(msg, SendReportbackBeen.class);
    }
}
