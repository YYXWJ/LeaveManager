package leavemanager.example.com.leavemanager.been;

import java.io.Serializable;

import leavemanager.example.com.leavemanager.node.LeaveInfo;

/**
 * Created with Android Studio.
 * User: zhaokai
 * Date: 2018/10/31
 * Time: 10:28 PM
 * Desc: 假条列表 item
 */
public class LeavePermitItem implements Serializable {

    public String title;

    public LeaveInfo info;

    public Object extra;
}
