package leavemanager.example.com.leavemanager.been;

import java.util.ArrayList;

import leavemanager.example.com.leavemanager.node.LeaveInfo;

public class LeavePermitBeen {
    private int code;
    private String msg;
    private ArrayList<LeaveInfo> data;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<LeaveInfo> getData() {
        return data;
    }

    public void setData(ArrayList<LeaveInfo> data) {
        this.data = data;
    }


}
