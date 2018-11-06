package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.adapter.LeavePermitAdapter;
import leavemanager.example.com.leavemanager.been.LeavePermitBeen;
import leavemanager.example.com.leavemanager.been.LeavePermitDescInfo;
import leavemanager.example.com.leavemanager.been.LeavePermitItem;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.utils.http.GetPermitService;
import leavemanager.example.com.leavemanager.utils.http.SendPermitService;

@SuppressLint("ValidFragment")
public class PermitFragment extends Fragment{
    private Context mContext;
    private RecyclerView mRv;
    private LeavePermitAdapter mAdapter;
    private static ProgressDialog progressDialog = null;
    public PermitFragment() {

    }

    public PermitFragment(Context context) {
        this.mContext = context;
    }

    public static PermitFragment newInstance(String s) {
        PermitFragment instence = new PermitFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        instence.setArguments(bundle);
        return instence;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_permit_new, container, false);
        mRv = view.findViewById(R.id.rv_list);
        mRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new LeavePermitAdapter(getActivity());
        mAdapter.setmOnViewClick(new LeavePermitAdapter.OnViewClick() {
            @Override
            public void onViewClick(final LeavePermitItem leavePermitItem) {
                LeaveInfo leaveInfo = (LeaveInfo)leavePermitItem.extra;
                leaveInfo.setApproveid(MyApplication.getLoginBeen().getData().get(0).getName());
                //SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日");
                Date date = new Date(System.currentTimeMillis());
                leaveInfo.setApprovedata(formatter.format(date));
                leaveInfo.setStatus("1");
                SendPermitService.SendPermitLeaveInfo(leaveInfo, new SendPermitService.CallBack() {
                    @Override
                    public void onSuccessed() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext,"批假成功",Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void onFailed() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mContext,"批假失败",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

            }
        });
        mRv.setAdapter(mAdapter);
        progressDialog = ProgressDialog.show(mContext, "请稍等...", "获取请假单中...", true);
        //加载数据
        GetPermitService.getPermitLeaveInfo(new GetPermitService.CallBack() {
            @Override
            public void onSuccessed(final LeavePermitBeen leavePermitBeen) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        loadData(leavePermitBeen);
                    }
                });
            }

            @Override
            public void onFailed() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        Toast.makeText(getContext(),"获取假条失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        return view;
    }

    private void loadData(final LeavePermitBeen leavePermitBeen) {
        List<Object> list = new ArrayList<>();
        ArrayList<LeaveInfo> leaveInfos = leavePermitBeen.getData();
        list.add("请假条");
        for (int i = 0; i < leaveInfos.size(); i++) {
            LeavePermitItem item = new LeavePermitItem();
            item.title = "申请人:"+leaveInfos.get(i).getApplicantid();
            LeavePermitDescInfo info = new LeavePermitDescInfo();
            info.applyPersons = leaveInfos.get(i).getApplicantid();
            info.startTime = leaveInfos.get(i).getFromdate();
            info.endTIme = leaveInfos.get(i).getTodate();
            info.place = leaveInfos.get(i).getLastdate();
            info.event = leaveInfos.get(i).getLeaveevent();
            info.permitPerson = leaveInfos.get(i).getSubmitid();
            info.permitTime = leaveInfos.get(i).getSubmitdate();
            Log.e("PermitFragment",info.toString());
            item.info = info;
            item.extra = leaveInfos.get(i);
            list.add(item);
        }

        mAdapter.addData(list);
    }
}
