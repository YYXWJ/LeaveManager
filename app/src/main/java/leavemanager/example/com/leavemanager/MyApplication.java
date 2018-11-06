package leavemanager.example.com.leavemanager;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import leavemanager.example.com.leavemanager.activity.LoginActivity;
import leavemanager.example.com.leavemanager.activity.MainActivity;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.fragment.LeaveFragment;
import leavemanager.example.com.leavemanager.utils.DialogUtil;

public class MyApplication extends Application {
    private static MyApplication myApplication = null;
    public static Handler hanlder = new MyHandler();
    public final static int LOGIN_FAIL=0;
    public final static int LOGIN_SUCCESS=1;
    public final static int APPLYPERSON_FAIL=3;
    public final static int APPLYPERSON_SUCCESS=4;
    public final static int PERMIT_FAIL=5;
    public final static int PERMIT_SUCCESS= 6;

    private static LoginBeen loginBeen = null;
    public static LoginBeen getLoginBeen(){
        if(loginBeen != null){
            return loginBeen;
        }
        return null;
    }
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
                    //test
                    //LoginActivity.loginSuccess();
                    break;
                case LOGIN_SUCCESS://登录成功
                    loginBeen = (LoginBeen)msg.obj;
                    LoginActivity.loginSuccess();
                    break;
                case APPLYPERSON_FAIL:
                    //LeaveFragment.getApplyPersonsFail();
                    break;
                case APPLYPERSON_SUCCESS:
                    //LeaveFragment.getApplyPersonsSuccess((ApplyPersonBeen)msg.obj);
                    break;
                case PERMIT_FAIL:
                    break;
                case PERMIT_SUCCESS:
                    break;
            }
            Looper.loop();
        }
    }
}
