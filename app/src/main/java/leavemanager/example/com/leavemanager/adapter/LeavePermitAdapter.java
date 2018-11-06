package leavemanager.example.com.leavemanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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


public class LeavePermitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public interface OnViewClick{
        void onViewClick(LeavePermitItem leavePermitItem);
    }
    private OnViewClick mOnViewClick;
    public void setmOnViewClick(OnViewClick onViewClick){
        mOnViewClick = onViewClick;
    }
    public LeavePermitAdapter(Activity context) {
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
            return new LeavePermitVH(LayoutInflater.from(mContext).inflate(R.layout.item_leave_permit
                    , parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LeavePermitTitleVH) {
            LeavePermitTitleVH vh = (LeavePermitTitleVH) holder;
            vh.mTitleTv.setText((String) mList.get(position));
        } else {
            final LeavePermitVH vh = (LeavePermitVH) holder;
            final LeavePermitItem data = (LeavePermitItem) mList.get(position);
            vh.mTitleTv.setText(data.title);
            vh.mNameTv.setText(data.info.applicantId);
            vh.mTimeTv.setText(data.info.startTime+"-"+data.info.endTIme);
            vh.mEventTv.setText(data.info.event);
            vh.mPlaceTv.setText(data.info.place);
            vh.mPermitNameTv.setText(data.info.permitPerson);
            vh.mPermitTimeTv.setText(data.info.permitTime);
            //final LeaveInfo leaveInfo = (LeaveInfo)data.extra;
            vh.mAgreeBn.setOnClickListener(new View.OnClickListener() {
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
                    leaveInfo.setStatus("1");
                    SendPermitService.SendPermitLeaveInfo(leaveInfo, new SendPermitService.CallBack() {
                        @Override
                        public void onSuccessed() {
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,"批假成功",Toast.LENGTH_LONG).show();

                                }
                            });
                        }

                        @Override
                        public void onFailed() {
                            mContext.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext,"批假失败",Toast.LENGTH_LONG).show();

                                }
                            });
                        }
                    });
                }
            });
            vh.mDisagreeBn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LeaveInfo leaveInfo = (LeaveInfo)data.extra;
                    leaveInfo.setApproveid(MyApplication.getLoginBeen().getData().get(0).getName());
                    SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日   HH:mm:ss");
                    Date date = new Date(System.currentTimeMillis());
                    leaveInfo.setApprovedata(formatter.format(date));
                    leaveInfo.setStatus("-1");
                    SendPermitService.SendPermitLeaveInfo(leaveInfo, new SendPermitService.CallBack() {
                        @Override
                        public void onSuccessed() {
                            //Toast.makeText(mContext,"批假成功",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailed() {
                            //Toast.makeText(mContext,"批假失败",Toast.LENGTH_LONG).show();
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
        private Button mAgreeBn;
        private Button mDisagreeBn;
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
            mAgreeBn = itemView.findViewById(R.id.bn_agree);
            mDisagreeBn = itemView.findViewById(R.id.bn_disagree);
        }
    }
}
