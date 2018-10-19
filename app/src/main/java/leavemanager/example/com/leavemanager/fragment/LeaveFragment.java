package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import org.jetbrains.annotations.Nullable;

import java.util.Calendar;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.activity.MainActivity;

public class LeaveFragment extends Fragment {
    private final static String TAG = "LeaveFragment";
    private Button selectStartTimeButton;
    private Button selectEndTimeButton;
    private Button sendButton;
    private Context mContext;
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
        selectStartTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildSelectTimeDialog(v);
            }

        });
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void buildSelectTimeDialog(View v) {
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
//                        int arrive_year = datePicker.getYear();
//                        int arrive_month = datePicker.getMonth();
//                        int arrive_day = datePicker.getDayOfMonth();
//                        String dateStr = DateUtil.formatDate(arrive_year, arrive_month, arrive_day);
//
//                        int arrive_hour = timePicker.getCurrentHour();
//                        int arrive_min = timePicker.getCurrentMinute();
//                        String timeStr = DateUtil.formatTime(arrive_hour, arrive_min);
//                        arriveTimeBtn.setText(timeStr);

            }
        });
        builder.show();
    }
}
