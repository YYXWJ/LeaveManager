package leavemanager.example.com.leavemanager.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.fragment.LeaveFragment;
import leavemanager.example.com.leavemanager.fragment.MineFragment;
import leavemanager.example.com.leavemanager.fragment.PermitFragment;
import leavemanager.example.com.leavemanager.fragment.ReportbackFragment;

public class MainActivity extends Activity {
    private RadioGroup mRadioGroup = null;
    private RadioButton mRadioLeave = null;
    private RadioButton mRadioPermit = null;
    private RadioButton mRadioReportback = null;
    private RadioButton mRadioMine = null;
    private LeaveFragment mLeaveFragment;
    private PermitFragment mPermitFragment;
    private ReportbackFragment mReportbackFragment;
    private MineFragment mMineFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup = findViewById(R.id.radio_group);
        mRadioLeave = findViewById(R.id.leave_rb);
        mRadioPermit = findViewById(R.id.permit_rb);
        mRadioReportback = findViewById(R.id.reportback_rb);
        mRadioMine = findViewById(R.id.mine_rb);
        mRadioGroup.setOnCheckedChangeListener(new MyRadioGroupListener());
        fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mLeaveFragment = new LeaveFragment();
        transaction.add(R.id.main_fragment_container,mLeaveFragment);
        transaction.commit();

        mRadioLeave.setSelected(true);
        mRadioLeave.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));

    }
    class MyRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            switch (checkedId) {
                case R.id.leave_rb:
                    if (mLeaveFragment == null) {
                        mLeaveFragment = LeaveFragment.newInstance(getString(R.string.leave));
                    }
                    transaction.replace(R.id.main_fragment_container, mLeaveFragment);
                    break;
                case R.id.permit_rb:
                    if (mPermitFragment == null) {
                        mPermitFragment = PermitFragment.newInstance(getString(R.string.permit));
                    }
                    transaction.replace(R.id.main_fragment_container, mPermitFragment);
                    break;
                case R.id.reportback_rb:
                    if (mReportbackFragment == null) {
                        mReportbackFragment = ReportbackFragment.newInstance(getString(R.string.reportback));
                    }
                    transaction.replace(R.id.main_fragment_container, mReportbackFragment);
                    break;
                case R.id.mine_rb:
                    if (mMineFragment == null) {
                        mMineFragment = MineFragment.newInstance(getString(R.string.mine));
                    }
                    transaction.replace(R.id.main_fragment_container, mMineFragment);
                    break;
            }
            setTabState();//设置状态
            transaction.commit();
        }


    }
    private void setTabState() {
        setLeaveState();
        setPermitState();
        setReportbackState();
        setMineState();
    }

    /**
     * set tab home state
     */
    private void setLeaveState() {
        if (mRadioLeave.isChecked()) {
            //mRadioLeave.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
            mRadioLeave.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioLeave.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private void setPermitState() {
        if (mRadioPermit.isChecked()) {
            mRadioPermit.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioPermit.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private void setReportbackState() {
        if (mRadioReportback.isChecked()) {
            mRadioReportback.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioReportback.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    private void setMineState() {
        if (mRadioMine.isChecked()) {
            mRadioMine.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        } else {
            mRadioMine.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        }
    }
}
