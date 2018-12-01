package leavemanager.example.com.leavemanager.been;

import java.util.ArrayList;

public class LoginBeen {
    private int code;
    private String msg;
    private ArrayList<person> data;
    public class person{
        //private int id;
        //private int id1;
        private String groutname;
        private String groupid;
        private String name;
        private String persionid;
        private String userid;
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public person() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getGroutname() {
            return groutname;
        }

        public void setGroutname(String groutname) {
            this.groutname = groutname;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getPersionid() {
            return persionid;
        }

        public void setPersionid(String persionid) {
            this.persionid = persionid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        @Override
        public String toString() {
            return "groutname--"+groutname+"groupid--"+groupid+"persionid--"+persionid+"userid----"+userid;
        }
    }

    @Override
    public String toString() {
        return code+msg+data;
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

    public ArrayList<person>  getData() {
        return data;
    }

    public void setData(ArrayList<person>  data) {
        this.data = data;
    }


}
