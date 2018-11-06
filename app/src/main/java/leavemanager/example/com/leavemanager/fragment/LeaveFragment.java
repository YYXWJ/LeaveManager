package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.been.ApplyPersonBeen;
import leavemanager.example.com.leavemanager.been.LoginBeen;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.node.ReceiveLeaveInfo;
import leavemanager.example.com.leavemanager.utils.DateUtil;
import leavemanager.example.com.leavemanager.utils.http.ApplyPersonsService;
import leavemanager.example.com.leavemanager.utils.http.LeaveService;


public class LeaveFragment extends Fragment {
    private final static String TAG = "LeaveFragment";
    private EditText et_selectStartTime;
    private EditText et_selectEndTime;
    private EditText et_selectApplyPerson;
    private EditText et_place;
    private EditText et_event;
    private EditText et_real_apply_person;
    private EditText et_now_time;
    private Button sendButton;
    private String persionid;
    private String applicantid;

    public String getApplicantid() {
        return applicantid;
    }

    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }

    public String getPersionid() {
        return persionid;
    }

    public void setPersionid(String persionid) {
        this.persionid = persionid;
    }
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
        //带人请假这个还没加
        View view = inflater.inflate(R.layout.fragment_leave, container, false);
        et_selectStartTime = view.findViewById(R.id.start_time);
        et_selectEndTime = view.findViewById(R.id.end_time);
        et_selectApplyPerson = view.findViewById(R.id.apply_person);
        et_place = view.findViewById(R.id.place);
        et_event = view.findViewById(R.id.event);
        sendButton = view.findViewById(R.id.leave_submit);
        et_real_apply_person = view.findViewById(R.id.real_apply_person);
        et_now_time = view.findViewById(R.id.apply_time);
        initView();
//        et_real_apply_person.setEnabled(false);
//        et_real_apply_person.setFocusable(false);
        et_real_apply_person.setKeyListener(null);
        et_real_apply_person.setOnClickListener(new View.OnClickListener() {
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
                                getRealApplyPersonSuccess(applyPersonBeen);
                            }
                        });
                    }

                    @Override
                    public void onFailed() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getApplyPersonsFail();
                            }
                        });
                    }
                });
            }
        });


        et_now_time.setEnabled(false);
        et_now_time.setFocusable(false);
        et_now_time.setKeyListener(null);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        Date date = new Date(System.currentTimeMillis());
        et_now_time.setText(simpleDateFormat.format(date));
        et_selectApplyPerson.setOnClickListener(new View.OnClickListener() {
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
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                getApplyPersonsFail();
                            }
                        });
                    }
                });
            }
        });
        et_selectStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSelectTimeDialog(v);
            }

        });
        et_selectEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSelectTimeDialog(v);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_selectApplyPerson.getText().equals("")||
                        et_selectStartTime.getText().equals("")||
                        et_selectEndTime.getText().equals("")||
                        et_place.getText().equals("")||
                        et_event.getText().equals("")){
                    Toast.makeText(getContext(),"假条信息不全,请补充请假信息",Toast.LENGTH_LONG).show();
                    return;
                }
                LeaveInfo leaveInfo = new LeaveInfo();
                leaveInfo.setApplicantName(et_selectApplyPerson.getText().toString());
                leaveInfo.setApplicantid(getPersionid());
                leaveInfo.setFromdate(et_selectStartTime.getText().toString());
                leaveInfo.setTodate(et_selectEndTime.getText().toString());
                leaveInfo.setLeavesite(et_place.getText().toString());
                leaveInfo.setLeaveevent(et_event.getText().toString());
                leaveInfo.setSubmitdate(et_now_time.getText().toString());
                leaveInfo.setPersionid(getPersionid());
                leaveInfo.setPersionName(et_real_apply_person.getText().toString());
                leaveInfo.setSubmitid(MyApplication.getLoginBeen().getData().get(0).getId()+"");
                LeaveService.permitLeaveInfo(leaveInfo, new LeaveService.CallBack() {
                    @Override
                    public void onSuccessed() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"提交成功",Toast.LENGTH_LONG).show();
                                initView();
                            }
                        });

                    }

                    @Override
                    public void onFailed() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(),"提交失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
    private void initView(){
        et_selectStartTime.setText("");
        et_selectEndTime.setText("");
        et_selectApplyPerson.setText("");
        et_place.setText("");
        et_event.setText("");
        et_real_apply_person.setText("");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        Date date = new Date(System.currentTimeMillis());
        et_now_time.setText(simpleDateFormat.format(date));
    }
    private void getRealApplyPersonSuccess(ApplyPersonBeen applyPersonBeen) {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
        selectRealApplyPersions(mContext,applyPersonBeen).show();
    }

    private Dialog selectRealApplyPersions(Context mContext, ApplyPersonBeen obj) {
        final String items[] = new String[obj.getData().size()];
        final int ids[] = new int[obj.getData().size()];
        final boolean selected[] = new boolean[obj.getData().size()];
        int i = 0;
        for(ApplyPersonBeen.person p : obj.getData()){
            items[i] = p.getName();
            ids[i] = p.getId();
            selected[i++] = false;
        }
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext,3);
        builder.setTitle("请选择申请人");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(ids[which] == MyApplication.getLoginBeen().getData().get(0).getId()){
                            et_real_apply_person.setText(items[which]);
                            setPersionid(ids[which]+"");
                        }else{
                            et_real_apply_person.setText(items[which]+"(代"+MyApplication.getLoginBeen().getData().get(0).getName()+")");
                            setPersionid(ids[which]+";"+MyApplication.getLoginBeen().getData().get(0).getId());
                        }
                        dialog.dismiss();
                    }
                });
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                StringBuffer sb = new StringBuffer();
//                StringBuffer id = new StringBuffer();
//                for (int i = 0; i < selected.length; i++) {
//                    if(selected[i]==true){
//                        sb.append(items[i]);
//                        sb.append(";");
//                        id.append(ids[i]+"");
//                        id.append(";");
//                    }
//                }
//                sb.deleteCharAt(sb.length() - 1);
//                setPersionid(id.toString());
//                et_selectApplyPerson.setText(sb.toString());
//                dialog.dismiss();
//
//            }
//        });
        return builder.create();
    }

    private void buildSelectTimeDialog(final View v) {
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
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                Toast.makeText(getContext(),
//                                                        hourOfDay + "小时" + minute + "分钟",
//                                                        Toast.LENGTH_SHORT).show();
            }
        });
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
        final int ids[] = new int[obj.getData().size()];
        final boolean selected[] = new boolean[obj.getData().size()];
        int i = 0;
        for(ApplyPersonBeen.person p : obj.getData()){
            items[i] = p.getName();
            ids[i] = p.getId();
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
                StringBuffer id = new StringBuffer();
                for (int i = 0; i < selected.length; i++) {
                    if(selected[i]==true){
                        sb.append(items[i]);
                        sb.append(";");
                        id.append(ids[i]+"");
                        id.append(";");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
                setPersionid(id.toString());
                et_selectApplyPerson.setText(sb.toString());
                dialog.dismiss();

            }
        });
        return builder.create();
    }
}
