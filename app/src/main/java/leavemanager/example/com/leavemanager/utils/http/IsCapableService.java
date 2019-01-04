package leavemanager.example.com.leavemanager.utils.http;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.been.IsCapableBeen;

public class IsCapableService {
    private static String url = Constants.serverURL+"/leaveinfo/getpersionleave";
    //private static Handler loginHandler = MyApplication.hanlder;
    public interface CallBack{
        void onSuccessed();
        void onFailed();
    }
    public static boolean isCapable(final String applications) {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        String tmp = applications.replaceAll(";",",");
        Callable task = new TaskCallable(tmp);
        Future f1 = pool.submit(task);
        try {
            ArrayList<String> list = (ArrayList<String>) f1.get();
            if(list == null){
                return false;
            }else if(list.size() == 0){
                return false;
            }else{
                if(list.size() == tmp.split(",").length){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        return true;
    }
    public static IsCapableBeen formatData(String msg) {
        Gson gson = new Gson();
//        LeavePermitBeen leavePermitBeen = gson.fromJson(msg, LeavePermitBeen.class);
//        ArrayList<LeaveInfo> leaveInfos = leavePermitBeen.getData();
//        for(int i = 0;i < le)
        return gson.fromJson(msg, IsCapableBeen.class);

    }
    public static class TaskCallable implements Callable<ArrayList<String>> {
        private String mApplications;
        public TaskCallable(String applications){
            mApplications = applications;
        }
        @Override
        public ArrayList<String> call() throws Exception {

            try {
                JSONObject userJSON = new JSONObject();
                userJSON.put("qjrs", mApplications);
                String content = String.valueOf(userJSON);
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
                IsCapableBeen isCapableBeen = null;
                if (stat == 200) {
                    br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    isCapableBeen = formatData(br.readLine());
                } else {
                    //LoginFailed();
                    return null;
                }
                if (isCapableBeen.getCode() == 0) {
                    //LoginSuccessed(loginBeen);

                    return isCapableBeen.getData();
                } else if(isCapableBeen.getCode() == -1){
                    //LoginFailed();
                    return null;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
