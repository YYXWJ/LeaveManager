package leavemanager.example.com.leavemanager.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;

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
    public static Dialog selectApplyPersions(Context context, ApplyPersonBeen obj){

        final String items[] = new String[obj.getData().size()];
        final boolean selected[] = new boolean[obj.getData().size()];
        int i = 0;
        for(ApplyPersonBeen.person p : obj.getData()){
            items[i] = p.getName();
            selected[i++] = false;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,3);
        builder.setTitle("请选择请假人");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, selected,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {

                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < selected.length; i++) {
                    sb.append(items[i]);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                dialog.dismiss();

            }
        });
        return builder.create();
    }
}
