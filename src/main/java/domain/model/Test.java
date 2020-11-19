package domain.model;

import java.time.LocalDate;

public class Test {
    private String userid;
    private LocalDate date;

    public Test(String userid, LocalDate date) {
        setUserid(userid);
        setDate(date);
    }

    public Test() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
