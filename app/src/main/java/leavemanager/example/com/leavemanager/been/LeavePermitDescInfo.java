package leavemanager.example.com.leavemanager.been;

/**
 * Created with Android Studio.
 * User: zhaokai
 * Date: 2018/10/31
 * Time: 10:30 PM
 * Desc: 假条概述信息
 */
public class LeavePermitDescInfo {
    public String applyPersons;
    public String startTime;
    public String endTIme;
    public String place;
    public String event;
    public String permitPerson;
    public String permitTime;
    public String applicantId;

    @Override
    public String toString() {
        return "applyPersons--"+applyPersons+
                "startTime--"+startTime+
                "endTime--"+endTIme+
                "place--"+place+
                "event--"+event+
                "permitPerson--"+permitPerson+
                "permitTime--"+permitTime;

    }
}
