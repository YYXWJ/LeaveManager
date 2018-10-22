package leavemanager.example.com.leavemanager.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
    public static Dialog userNameOrUserPasswdIsNoneDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //    设置Title的内容
        builder.setTitle("警告");
        //    设置Content来显示一个信息
        builder.setMessage("电话号码或密码为空");
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        return builder.create();
    }
    public static Dialog loginFail(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //    设置Title的内容
        builder.setTitle("警告");
        //    设置Content来显示一个信息
        builder.setMessage("登录失败");
        //    设置一个PositiveButton
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        return builder.create();
    }
}
