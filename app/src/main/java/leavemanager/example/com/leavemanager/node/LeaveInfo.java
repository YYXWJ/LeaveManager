package leavemanager.example.com.leavemanager.node;



public class LeaveInfo {
    private Long id;

    private String qjrs;   //填的请假人

    private String name;   //请假人

    private String sheetno;

    private String persionid;

    private String fromdate;

    private String todate;

    private String leavesite;

    private String applicantid;//填写假单的人

    private String applicantname;

    private String userid;

    private String submitdate;

    private String approveid;//批假人id

    private String approvename;

    //  @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private String approvedata;

    private String returndate;

    private String rtnpersion;

    private String enddate;

    private String endpersion;//申请销假人

    private String confirmid;

    private String confirmdate;

    private String indate;

    private String lastdate;

    private String firstman;

    private String lastman;

    private String status;

    private String outdate;

    private String outpersion;

    private String leaveevent;

    private String note;

    private String relleaveid;//代请假人

    private String relleavename;

    private String relendid;//带销假人

    private String rank;

    public String getApplicantname() {
        return applicantname;
    }

    public void setApplicantname(String applicantname) {
        this.applicantname = applicantname;
    }

    public String getApprovename() {
        return approvename;
    }

    public void setApprovename(String approvename) {
        this.approvename = approvename;
    }

    public String getRelleavename() {
        return relleavename;
    }

    public void setRelleavename(String relleavename) {
        this.relleavename = relleavename;
    }

    public String getQjrs(){

        return this.qjrs;
    }
    public void setQjrs(String ps){

        this.qjrs= ps;
    }

    public String getName(){

        return this.name;
    }
    public void setName(String ps){

        this.name= ps;
    }



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
    public String getUserid(){

        return this.userid;
    }
    public void setUserid(String userid){

        this.userid = userid;
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

        return this.outdate;
    }
    public void setOutdate(String outdate){

        this.outdate = outdate;
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

    public String getNote(){

        return this.note;
    }
    public void setNote(String note){

        this.note = note;
    }




    public String getRelleaveid(){

        return this.relleaveid;
    }
    public void setRelleaveid(String relleaveid){

        this.relleaveid = relleaveid;
    }

    public String getRelendid(){

        return this.relendid;
    }
    public void setRelendid(String relendid){

        this.relendid = relendid;
    }

    public String getRank(){

        return this.rank;
    }
    public void setRank(String rank){

        this.rank = rank;
    }

}

