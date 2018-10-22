package leavemanager.example.com.leavemanager;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import leavemanager.example.com.leavemanager.activity.LoginActivity;
import leavemanager.example.com.leavemanager.activity.MainActivity;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;
    public static Handler hanlder = new MyHandler();
    public final static int LOGIN_FAIL=0;
    public final static int LOGIN_SUCCESS=1;

    public static MyApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;
    }
    private static class MyHandler extends Handler {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Looper.prepare();

            switch (msg.what) {
                case LOGIN_FAIL://登录失败
                    LoginActivity.loginFail();
                    break;
                case LOGIN_SUCCESS://登录成功
                    LoginActivity.loginSuccess();
                    break;
            }
            Looper.loop();
        }
    }
}
