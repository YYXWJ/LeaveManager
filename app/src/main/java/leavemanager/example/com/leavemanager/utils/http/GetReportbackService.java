package leavemanager.example.com.leavemanager.utils.http;

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

public class GetReportbackService {
    private static String url = Constants.serverURL+"/m/leaveinfo/runningleave";//这里需要改
    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    public static boolean getReportbackLeaveInfo(final GetPermitService.CallBack callBack){
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("id", MyApplication.getLoginBeen().getData().get(0).getPersionid()+"");
                    //userJSON.put("id", "100000006");
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
                    //ApplyReceiveBeen applyReceiveBeen = null;
                    //permitFailed();
                    LeavePermitBeen leavePermitBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        leavePermitBeen = formatData(br.readLine());
                    } else {
                        //LoginFailed();
                        callBack.onFailed();
                        return;
                    }
                    if (leavePermitBeen.getCode() == 0) {
                        //LoginSuccessed(loginBeen);
                        callBack.onSuccessed(leavePermitBeen);
                        return;
                    } else {
                        //LoginFailed();
                        if(leavePermitBeen.getMsg().equals("数据为空！")){
                            callBack.onSuccessed(leavePermitBeen);
                        }else{
                            callBack.onFailed();
                        }
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
    public static LeavePermitBeen formatData(String msg) {
        Gson gson = new Gson();
//        LeavePermitBeen leavePermitBeen = gson.fromJson(msg, LeavePermitBeen.class);
//        ArrayList<LeaveInfo> leaveInfos = leavePermitBeen.getData();
//        for(int i = 0;i < le)
        return gson.fromJson(msg, LeavePermitBeen.class);

    }
}
