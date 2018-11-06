package leavemanager.example.com.leavemanager.node;


public class ReceiveLeaveInfo {
    private String applyPersons;
    private String startTime;
    private String endTIme;
    private String place;
    private String event;
    private String permitPerson;
    private String permitTime;
    private String applicantId;

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplyPersons() {
        return applyPersons;
    }

    public void setApplyPersons(String applyPersons) {
        this.applyPersons = applyPersons;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(String endTIme) {
        this.endTIme = endTIme;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getPermitPerson() {
        return permitPerson;
    }

    public void setPermitPerson(String permitPerson) {
        this.permitPerson = permitPerson;
    }

    public String getPermitTime() {
        return permitTime;
    }

    public void setPermitTime(String permitTime) {
        this.permitTime = permitTime;
    }


}
