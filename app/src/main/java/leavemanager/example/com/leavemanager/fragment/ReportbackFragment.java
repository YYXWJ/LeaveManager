package leavemanager.example.com.leavemanager.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.GetChars;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import leavemanager.example.com.leavemanager.Constants;
import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.adapter.ReportbackListviewAdapter;
import leavemanager.example.com.leavemanager.been.LeavePermitBeen;
import leavemanager.example.com.leavemanager.been.LeavePermitDescInfo;
import leavemanager.example.com.leavemanager.been.LeavePermitItem;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.utils.http.GetPermitService;
import leavemanager.example.com.leavemanager.utils.http.GetReportbackService;

public class ReportbackFragment extends Fragment {
    private Context mContext;
    private RecyclerView mRv;
    private ReportbackListviewAdapter mAdapter;
    private static ProgressDialog progressDialog = null;
    public ReportbackFragment() {

    }

    @SuppressLint("ValidFragment")
    public ReportbackFragment(Context context) {
        this.mContext = context;
    }

    public static ReportbackFragment newInstance(String s) {
        ReportbackFragment instence = new ReportbackFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        instence.setArguments(bundle);
        return instence;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            progressDialog = ProgressDialog.show(mContext, "请稍等...", "获取请假单中...", true);
            GetReportbackService.getReportbackLeaveInfo(new GetPermitService.CallBack() {
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
        }
        super.onHiddenChanged(hidden);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reportback_new, container, false);
        View footview = inflater.inflate(R.layout.reportback_listview_footview, null);
        TextView tv = footview.findViewById(R.id.reportbacklistviewfootview);
        progressDialog = ProgressDialog.show(mContext, "请稍等...", "获取请假单中...", true);
        mRv = view.findViewById(R.id.reportback_list);
        mRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mAdapter = new ReportbackListviewAdapter(getActivity());
        mRv.setAdapter(mAdapter);
        GetReportbackService.getReportbackLeaveInfo(new GetPermitService.CallBack() {
            @Override
            public void onSuccessed(final LeavePermitBeen leavePermitBeen) {
                progressDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadData(leavePermitBeen);
                    }
                });

            }

            @Override
            public void onFailed() {
                progressDialog.dismiss();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"获取假条失败",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
        //加载数据
        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadData(LeavePermitBeen leavePermitBeen) {
        List<Object> list = new ArrayList<>();
        ArrayList<LeaveInfo> leaveInfos = leavePermitBeen.getData();
        list.add("我的请假条");
        int i =0;
        for (; i < leaveInfos.size(); i++) {
            if(leaveInfos.get(i).getApplicantid().contains(MyApplication.getLoginBeen().getData().get(0).getPersionid())) {
                LeavePermitItem item = new LeavePermitItem();
                item.title = "申请人:" + leaveInfos.get(i).getName().replace(";", " ");
                LeaveInfo info = leaveInfos.get(i);
                item.info = info;
                item.extra = leaveInfos.get(i);
                list.add(item);
            }else{
                break;
            }
        }
        list.add("他人的请假条");
        for(;i<leaveInfos.size();i++){
            LeavePermitItem item = new LeavePermitItem();
            item.title = "申请人:" + leaveInfos.get(i).getName().replace(";", " ");
            LeaveInfo info = leaveInfos.get(i);
            item.info = info;
            item.extra = leaveInfos.get(i);
            list.add(item);
        }

//        mAdapter.addData(list);
//        List<Object> list = new ArrayList<>();
//
//        list.add("我的请假条");
//        for (int i = 0; i < 5; i++) {
//            LeavePermitItem item = new LeavePermitItem();
//            item.title = i + "号的请假条";
//            LeavePermitDescInfo info = new LeavePermitDescInfo();
////            info.name = i + "号同学";
////            info.time = "第" + i + "个星期一";
//            item.info = info;
//            list.add(item);
//        }
//
//        list.add("他人的销假条");
//        for (int i = 0; i < 5; i++) {
//            LeavePermitItem item = new LeavePermitItem();
//            item.title = i + "号的销价条";
//            LeavePermitDescInfo info = new LeavePermitDescInfo();
////            info.name = i + "号同学";
////            info.time = "第" + i + "个星期日";
//            item.info = info;
//            list.add(item);
//        }
//
        mAdapter.addData(list);
    }
}
