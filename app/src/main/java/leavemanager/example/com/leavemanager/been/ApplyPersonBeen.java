package leavemanager.example.com.leavemanager.been;

import java.util.ArrayList;

public class ApplyPersonBeen {
    private int code;
    private String msg;
    private ArrayList<person> data;
    public ArrayList<person> getData() {
        return data;
    }

    public void setData(ArrayList<person> data) {
        this.data = data;
    }


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


    public class person{

        //private String userid;
        //private String username;
        private String persionid;
        private String name;
//        private String gender;
//        private String birthday;
//        private String birthplace;
//        private String enthnic;
//        private String enlistdate;
//        private String firstdate;
//        private String lastdate;
//        private String status;
//        private String type;
//        private String rank;


        public String getPersionid() {
            return persionid;
        }

        public void setPersionid(String persionid) {
            this.persionid = persionid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
