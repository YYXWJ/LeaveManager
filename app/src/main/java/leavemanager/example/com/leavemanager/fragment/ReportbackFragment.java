package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.R;

public class ReportbackFragment extends Fragment {
    private Context mContext;
    public ReportbackFragment(){

    }
    @SuppressLint("ValidFragment")
    public ReportbackFragment(Context context){
        this.mContext = context;
    }
    public static ReportbackFragment newInstance(String s){
        ReportbackFragment instence = new ReportbackFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        instence.setArguments(bundle);
        return instence;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportback, container, false);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
