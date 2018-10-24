package leavemanager.example.com.leavemanager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import leavemanager.example.com.leavemanager.BottomBar;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.fragment.LeaveFragment;
import leavemanager.example.com.leavemanager.fragment.MineFragment;
import leavemanager.example.com.leavemanager.fragment.PermitFragment;
import leavemanager.example.com.leavemanager.fragment.ReportbackFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar= (BottomBar) findViewById(R.id.bottom_bar);

        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#077ff7")
                .addItem(LeaveFragment.class,
                        "请假",
                        R.drawable.leave_before,
                        R.drawable.leave_after)
                .addItem(PermitFragment.class,
                        "审批",
                        R.drawable.permit_before,
                        R.drawable.permit_after)
                .addItem(ReportbackFragment.class,
                        "销假",
                        R.drawable.reportback_before,
                        R.drawable.reportback_after)
                .addItem(MineFragment.class,
                        "我的",
                        R.drawable.mine_before,
                        R.drawable.mine_after)

                .build();
    }
}
