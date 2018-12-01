package leavemanager.example.com.leavemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import leavemanager.example.com.leavemanager.MyApplication;
import leavemanager.example.com.leavemanager.R;
import leavemanager.example.com.leavemanager.been.LeavePermitItem;
import leavemanager.example.com.leavemanager.node.LeaveInfo;
import leavemanager.example.com.leavemanager.utils.http.SendPermitService;
import leavemanager.example.com.leavemanager.utils.http.SendReportbackService;

public class ReportbackListviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public interface OnViewClick{
        void onViewClick(LeavePermitItem leavePermitItem);
    }
    private LeavePermitAdapter.OnViewClick mOnViewClick;
    public void setmOnViewClick(LeavePermitAdapter.OnViewClick onViewClick){
        mOnViewClick = onViewClick;
    }

    public ReportbackListviewAdapter(Activity context) {
        mContext = context;
    }

    private static final int TYPE_HEADER = 1;
    private static final int TYPE_VIEW = 2;

    private List<Object> mList = new ArrayList<>();
    private Activity mContext;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new LeavePermitTitleVH(LayoutInflater.from(mContext).inflate(R.layout.item_leave_permit_header
                    , parent,false));
        } else {
            return new LeavePermitVH(LayoutInflater.from(mContext).inflate(R.layout.item_leave_reportback
                    , parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof LeavePermitTitleVH) {
            LeavePermitTitleVH vh = (LeavePermitTitleVH) holder;
            vh.mTitleTv.setText((String) mList.get(position));
        } else {
            final LeavePermitVH vh = (LeavePermitVH) holder;
            final LeavePermitItem data = (LeavePermitItem) mList.get(position);
            vh.mTitleTv.setText(data.title);
//            vh.mNameTv.setText(data.info.getApplicantName());
//            vh.mTimeTv.setText(data.info.getFromdate()+"---"+data.info.getTodate());
//            vh.mEventTv.setText(data.info.getLeaveevent());
//            vh.mPlaceTv.setText(data.info.getLeavesite());
//            vh.mPermitNameTv.setText(data.info.getSubmitName());
           // vh.mPermitTimeTv.setText(data.info.getSubmitdate());
            //final LeaveInfo leaveInfo = (LeaveInfo)data.extra;
            vh.mReportbackBn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(mOnViewClick!=null){
//                        mOnViewClick.onViewClick(data);
//                    }
                    LeaveInfo leaveInfo = (LeaveInfo)data.extra;
                    leaveInfo.setApproveid(MyApplication.getLoginBeen().getData().get(0).getName());
                    //SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                    SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日");
                    Date date = new Date(System.currentTimeMillis());
                    leaveInfo.setApprovedata(formatter.format(date));
                    leaveInfo.setStatus("4");
                    SendReportbackService.SendReportbackLeaveInfo(leaveInfo, new SendReportbackService.CallBack() {
                        @Override
                        public void onSuccessed() {
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //vh.mLinearLayout.setVisibility(View.GONE);
                                    //vh.mDescContainer.setVisibility(View.GONE);
                                    mList.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText(mContext,"销假成功",Toast.LENGTH_LONG).show();

                                }
                            });
                        }

                        @Override
                        public void onFailed() {
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,"销假失败",Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    });
                }
            });

            vh.mDescContainer.setVisibility(vh.itemView.isSelected() ? View.VISIBLE : View.GONE);
//            vh.mNameTv.setText(data.info.name);
//            vh.mTimeTv.setText(data.info.time);
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setSelected(!view.isSelected());
                    vh.mDescContainer.setVisibility(vh.itemView.isSelected() ? View.VISIBLE : View.GONE);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position) instanceof String) {
            return TYPE_HEADER;
        } else {
            return TYPE_VIEW;
        }
    }


    public void addData(List<Object> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    class LeavePermitTitleVH extends RecyclerView.ViewHolder {

        private TextView mTitleTv;

        public LeavePermitTitleVH(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_title);
        }
    }

    class LeavePermitVH extends RecyclerView.ViewHolder {

        private TextView mTitleTv;
        private View mDescContainer;
        private TextView mNameTv;
        private TextView mTimeTv;
        private TextView mPlaceTv;
        private TextView mEventTv;
        private TextView mPermitNameTv;
        private TextView mPermitTimeTv;
        private LinearLayout mLinearLayout;
        private Button mReportbackBn;
        //private
        public LeavePermitVH(View itemView) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mDescContainer = itemView.findViewById(R.id.ll_desc);
            mNameTv = itemView.findViewById(R.id.tv_name);
            mTimeTv = itemView.findViewById(R.id.tv_time);
            mEventTv = itemView.findViewById(R.id.tv_event);
            mPlaceTv = itemView.findViewById(R.id.tv_place);
            mPermitNameTv = itemView.findViewById(R.id.tv_permitname);
            mPermitTimeTv = itemView.findViewById(R.id.tv_permittime);
            mLinearLayout = itemView.findViewById(R.id.ll_rb_item);
            mReportbackBn = itemView.findViewById(R.id.bn_reportback);
        }
    }
}