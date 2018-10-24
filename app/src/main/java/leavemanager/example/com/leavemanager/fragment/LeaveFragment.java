package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.activity.MainActivity;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.node.ReceiveLeaveInfo;
import leavemanager.example.com.leavemanager.utils.DateUtil;
import leavemanager.example.com.leavemanager.utils.DialogUtil;
import leavemanager.example.com.leavemanager.utils.http.ApplyPersonsService;
import leavemanager.example.com.leavemanager.utils.http.PermitService;


public class LeaveFragment extends Fragment {
    private final static String TAG = "LeaveFragment";
    private EditText selectStartTimeButton;
    private EditText selectEndTimeButton;
    private EditText selectApplyPerson;
    private EditText et_place;
    private EditText et_event;

    private Button sendButton;

    private static Context mContext;
    private static ProgressDialog progressDialog = null;
    public LeaveFragment(){

    }
    @SuppressLint("ValidFragment")
    public LeaveFragment(Context context){
        mContext = context;
    }
    public static LeaveFragment newInstance(String s){
        LeaveFragment instence = new LeaveFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        instence.setArguments(bundle);
        return instence;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        selectStartTimeButton = view.findViewById(R.id.start_time);
        selectEndTimeButton = view.findViewById(R.id.end_time);
        selectApplyPerson = view.findViewById(R.id.apply_person);
        et_place = view.findViewById(R.id.place);
        et_event = view.findViewById(R.id.event);
        sendButton = view.findViewById(R.id.leave_submit);
        selectApplyPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyApplication.getLoginBeen() == null){
                    return;
                }
                StringBuffer sb = new StringBuffer();

                for(LoginBeen.person p: MyApplication.getLoginBeen().getData()){
                    if(p.getTyping().equals("A")){
                        sb.append(p.getGroupid());
                        sb.append(";");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                progressDialog = ProgressDialog.show(mContext, "请稍等...", "获取名单中...", true);
                ApplyPersonsService.getApplyPersions(sb.toString(), new ApplyPersonsService.CallBack() {
                    @Override
                    public void onSuccessed(final ApplyPersonBeen applyPersonBeen) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getApplyPersonsSuccess(applyPersonBeen);
                            }
                        });
                    }

                    @Override
                    public void onFailed() {
                        getApplyPersonsFail();
                    }
                });
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
               // DialogUtil.selectApplyPersions(mContext).show();
            }
        });
        selectStartTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSelectTimeDialog(v);
            }

        });
        selectEndTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSelectTimeDialog(v);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReceiveLeaveInfo receiveLeaveInfo = new ReceiveLeaveInfo();
                receiveLeaveInfo.setApplyPersons("123123;123123");
                receiveLeaveInfo.setStartTime("20181818");
                receiveLeaveInfo.setEndTIme("123123123");
                receiveLeaveInfo.setPlace("123123");
                receiveLeaveInfo.setPermitPerson("name");
                receiveLeaveInfo.setPermitTime("2002020220");
                PermitService.permitLeaveInfo(receiveLeaveInfo);
            }
        });
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void buildSelectTimeDialog(final View v) {
        Log.e(TAG,"view----"+v);
        View view = View.inflate(MyApplication.getApplication(), R.layout.date_time_picker, null);
        final DatePicker datePicker = (DatePicker)view.findViewById(R.id.new_act_date_picker);
        final TimePicker timePicker = (TimePicker)view.findViewById(R.id.new_act_time_picker);

        // Init DatePicker
        int year;
        int month;
        int day;
        //if (StringUtils.isEmpty(arriveDateBtn.getText().toString())) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
//                } else {
//                    year = NewActActivity.arrive_year;
//                    month = NewActActivity.arrive_month;
//                    day = NewActActivity.arrive_day;
//                }
        datePicker.init(year, month, day, null);

        // Init TimePicker
        int hour;
        int minute;
        //if (StringUtils.isEmpty(arriveTimeBtn.getText().toString())) {
        // Use the current time as the default values for the picker
        //  final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
//                } else {
//                    hour = NewActActivity.arrive_hour;
//                    minute = NewActActivity.arrive_min;
//                }
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        // Build DateTimeDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setView(view);
        //builder.setTitle(R.string.new_act_date_time_picker_title);
        builder.setTitle("日期");
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //格式化日期并写入控件
                        int arrive_year = datePicker.getYear();
                        int arrive_month = datePicker.getMonth();
                        int arrive_day = datePicker.getDayOfMonth();
                        String dateStr = DateUtil.formatDate(arrive_year, arrive_month, arrive_day);

                        int arrive_hour = timePicker.getCurrentHour();
                        int arrive_min = timePicker.getCurrentMinute();
                        String timeStr = DateUtil.formatTime(arrive_hour, arrive_min);
                ((EditText)v).setText(dateStr+timeStr);

            }
        });
        builder.show();
    }
    public void getApplyPersonsFail(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        Toast.makeText(MyApplication.getApplication(),"获取名单失败，请重试",Toast.LENGTH_LONG).show();
    }
    public void getApplyPersonsSuccess(ApplyPersonBeen obj){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        selectApplyPersions(mContext,obj).show();
    }
    public Dialog selectApplyPersions(Context context, ApplyPersonBeen obj){

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
                //这里需要加一个刷新控件的逻辑
                selectApplyPerson.setText(sb.toString());
                dialog.dismiss();

            }
        });
        return builder.create();
    }
}
