package leavemanager.example.com.leavemanager.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.node.UserInfo;
import leavemanager.example.com.leavemanager.utils.DialogUtil;
import leavemanager.example.com.leavemanager.utils.http.LoginService;

public class LoginActivity extends Activity {
    private Button loginButton;
    private Button forgetpasswdButton;
    private EditText et_phone;
    private EditText et_passwd;
    private static ProgressDialog progressDialog = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);
        forgetpasswdButton = findViewById(R.id.forgetPassword);
        et_phone = findViewById(R.id.accountEdittext);
        et_passwd = findViewById(R.id.pwdEdittext);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //oginSuccess();
                if (et_phone.getText().toString().equals("") || et_passwd.getText().toString().equals("")) {
                    DialogUtil.userNameOrUserPasswdIsNoneDialog(LoginActivity.this).show();

                    return;
                }
                progressDialog = ProgressDialog.show(LoginActivity.this, "请稍等...", "登录中...", true);
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername(et_phone.getText().toString());
                userInfo.setPasswd(et_passwd.getText().toString());
                LoginService.userRegister(userInfo, new LoginService.CallBack() {
                    @Override
                    public void onSuccess() {
                        loginFail();
                    }

                    @Override
                    public void onFailed() {
                        loginSuccess();
                    }
                });
            }
        });
    }
    public void loginFail(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        Toast.makeText(this,"登录失败，请重试",Toast.LENGTH_LONG).show();
    }
    public void loginSuccess(){
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
}
