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

@SuppressLint("ValidFragment")
public class MineFragment extends Fragment {
    @SuppressLint("ValidFragment")
    private Context mContext;
    public MineFragment(){

    }
    public MineFragment(Context context){
        this.mContext = context;
    }
    public static MineFragment newInstance(String s){
        MineFragment instence = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        instence.setArguments(bundle);
        return instence;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
