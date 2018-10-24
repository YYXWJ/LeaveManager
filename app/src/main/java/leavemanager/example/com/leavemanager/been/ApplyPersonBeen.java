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
        private int id;
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "id--"+id+"name--"+name;
        }
    }
}
