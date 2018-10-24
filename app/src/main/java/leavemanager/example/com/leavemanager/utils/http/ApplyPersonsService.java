package leavemanager.example.com.leavemanager.utils.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;

public class ApplyPersonsService {
    private static final String url = "http://10.103.241.30:8085/leaveinfo/validpersion";
    //private static Handler loginHandler = MyApplication.hanlder;
    public interface CallBack{
        void onSuccessed(ApplyPersonBeen applyPersonBeen);
        void onFailed();
    }
    /***
     * 获取申请人名单
     */

    public static boolean getApplyPersions(final String Groups,final CallBack callBack){
        new Thread()
        {
            @Override
            public void run()
            {
                try {
                    JSONObject userJSON = new JSONObject();
                    userJSON.put("groupid",Groups);
                    String content = String.valueOf(userJSON);
                    /**
                     * 请求地址
                     */
                    //String url = serverURL+"/persion/login";
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
                    ApplyPersonBeen applyPersonBeen = null;
                    if (stat == 200) {
                        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        applyPersonBeen = formatData(br.readLine());
                    } else {
                        //applyPersonFailed();
                        callBack.onFailed();
                        return;
                    }
                    if(applyPersonBeen.getCode() == 0){
                        //applyPersonSuccessed(applyPersonBeen);
                        callBack.onSuccessed(applyPersonBeen);
                        return;
                    }else{
                        //applyPersonFailed();
                        callBack.onFailed();
                        return;
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                    //applyPersonFailed();
                    callBack.onFailed();
                }
            }
        }.start();
        return true;
    }
    private static void applyPersonFailed() {
        Message msg = Message.obtain();
        msg.what = MyApplication.APPLYPERSON_FAIL;
        MyApplication.hanlder.handleMessage(msg);
    }
    private static void applyPersonSuccessed(ApplyPersonBeen applyPersonBeen) {
        Message msg = Message.obtain();
        msg.what = MyApplication.APPLYPERSON_SUCCESS;
        msg.obj = applyPersonBeen;
        MyApplication.hanlder.handleMessage(msg);
    }
    public static ApplyPersonBeen formatData(String msg) throws JSONException {
        Gson gson = new Gson();
        ApplyPersonBeen applyPersionBeen = gson.fromJson(msg, ApplyPersonBeen.class);
//        Log.e("123","loginBeen--"+applyPersionBeen.getMsg());
//        Log.e("123","loginBeen--"+applyPersionBeen.getCode());
//        ArrayList<ApplyPersionBeen.persion> data = applyPersionBeen.getData();
//        for(ApplyPersionBeen.persion p:data){
//            Log.e("123","data--"+p.toString());
//        }
        return gson.fromJson(msg, ApplyPersonBeen.class);

    }

}
