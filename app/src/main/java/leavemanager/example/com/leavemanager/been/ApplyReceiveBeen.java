package leavemanager.example.com.leavemanager.been;


/***
 * 提交请假条之后返回值
 *
 */
public class ApplyReceiveBeen {
    private int code;
    private String msg;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
