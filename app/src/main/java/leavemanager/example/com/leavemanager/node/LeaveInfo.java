package leavemanager.example.com.leavemanager.node;

public class LeaveInfo {
    private Long id;

    private String sheetno;         //请假单号

    private String persionid;      //登陆人员ID

    private String fromdate;       //休假开始日期

    private String todate;          //休假结束日期

    private String leavesite;       //休假地点

    private String applicantid;     //请假人

    private String submitid;        //填写请假单子的人

    private String submitdate;      //填写请假单子的日期

    private String approveid;       //批准人

    //  @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private String approvedata;     //批准日期

    private String returndate;      //返回营区大门日期

    private String rtnpersion;      //返回营区大门确认人

    private String enddate;         //申请销假日期

    private String endpersion;      //申请销假的人

    private String confirmid;       //销假确认人

    private String confirmdate;     //销假确认日期

    private String indate;          //数据第一次输入日期

    private String lastdate;        //数据最后更新日期

    private String firstman;        //第一次插入数据人

    private String lastman;         //数据最后更新日期

    private String status;          //状态0 代表请假成功  1 代表批准  2 出大门 3 返回营区 4 请求销假 5 确认销假  -1 代表不批准' ,

    private String outdated;        //出门日期

    private String outpersion;      //外出营区大门确认人

    private String leaveevent;      //假期事项

    public Long getId(){

        return this.id;
    }
    public void setId(Long id){

        this.id = id;
    }
    public String getSheetno(){

        return this.sheetno;
    }
    public void setSheetno(String sheetno){

        this.sheetno = sheetno;
    }
    public String getPersionid(){

        return this.persionid;
    }
    public void setPersionid(String persionid){

        this.persionid = persionid;
    }
    public String getFromdate(){

        return this.fromdate;
    }
    public void setFromdate(String fromdate){

        this.fromdate = fromdate;
    }
    public String getTodate(){

        return this.todate;
    }
    public void setTodate(String todate){

        this.todate = todate;
    }
    public String getLeavesite(){

        return this.leavesite;
    }
    public void setLeavesite(String leavesite){

        this.leavesite = leavesite;
    }
    public String getApplicantid(){

        return this.applicantid;
    }
    public void setApplicantid(String applicantid){

        this.applicantid = applicantid;
    }
    public String getSubmitid(){

        return this.submitid;
    }
    public void setSubmitid(String submitid){

        this.submitid = submitid;
    }
    public String getSubmitdate(){

        return this.submitdate;
    }
    public void setSubmitdate(String submitdate){

        this.submitdate = submitdate;
    }
    public String getApproveid(){

        return this.approveid;
    }
    public void setApproveid(String approveid){

        this.approveid = approveid;
    }
    public String getApprovedata(){

        return this.approvedata;
    }
    public void setApprovedata(String approvedata){

        this.approvedata = approvedata;
    }


    public String getReturndate(){

        return this.returndate;
    }
    public void setRtnpersion(String rtnpersion){

        this.rtnpersion = rtnpersion;
    }

    public String getRtnpersion(){

        return this.rtnpersion;
    }
    public void setReturndate(String returndate){

        this.returndate = returndate;
    }



    public String getEnddate(){

        return this.enddate;
    }
    public void setEnddate(String enddate){

        this.enddate = enddate;
    }

    public String getEndpersion(){

        return this.endpersion;
    }
    public void setEndpersion(String endpersion){

        this.endpersion = endpersion;
    }



    public String getConfirmid(){

        return this.confirmid;
    }
    public void setConfirmid(String confirmid){

        this.confirmid = confirmid;
    }
    public String getConfirmdate(){

        return this.confirmdate;
    }
    public void setConfirmdate(String confirmdate){

        this.confirmdate = confirmdate;
    }
    public String getIndate(){

        return this.indate;
    }
    public void setIndate(String indate){

        this.indate = indate;
    }
    public String getLastdate(){

        return this.lastdate;
    }
    public void setLastdate(String lastdate){

        this.lastdate = lastdate;
    }
    public String getFirstman(){

        return this.firstman;
    }
    public void setFirstman(String firstman){

        this.firstman = firstman;
    }
    public String getLastman(){

        return this.lastman;
    }
    public void setLastman(String lastman){

        this.lastman = lastman;
    }

    public String getStatus(){

        return this.status;
    }
    public void setStatus(String status){

        this.status = status;
    }

    public String getOutdate(){

        return this.outdated;
    }
    public void setOutdate(String outdate){

        this.outdated = outdate;
    }

    public String getOutpersion(){

        return this.outpersion;
    }
    public void setOutpersion(String outpersion){

        this.outpersion = outpersion;
    }

    public String getLeaveevent(){

        return this.leaveevent;
    }
    public void setLeaveevent(String leaveevent){

        this.leaveevent = leaveevent;
    }

}