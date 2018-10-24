package leavemanager.example.com.leavemanager.been;

import java.util.ArrayList;

public class LoginBeen {
    private int code;
    private String msg;
    private ArrayList<person> data;
    public class person{
        private int id;
        private int id1;
        private String name;
        private String groupid;
        private String typing;
        private String groupname;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTyping() {
            return typing;
        }

        public void setTyping(String typing) {
            this.typing = typing;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId1() {
            return id1;
        }

        public void setId1(int id1) {
            this.id1 = id1;
        }

        public String getGroupid() {
            return groupid;
        }

        public void setGroupid(String groupid) {
            this.groupid = groupid;
        }

        public String getGroupname() {
            return groupname;
        }

        public void setGroupname(String groupname) {
            this.groupname = groupname;
        }



        @Override
        public String toString() {
            return "id--"+id+"id1--"+id1+"groupid--"+groupid+"typing--"+typing+"groupname--"+groupname;
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
