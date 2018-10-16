package leavemanager.example.com.leavemanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.Nullable;

import leavemanager.example.com.leavemanager.Constants;

public class PermitFragment extends Fragment {
    public static PermitFragment newInstance(String s){
        PermitFragment instence = new PermitFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS,s);
        instence.setArguments(bundle);
        return instence;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
