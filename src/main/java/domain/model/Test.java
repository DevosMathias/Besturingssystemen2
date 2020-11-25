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
        if (date == null) {
            throw new IllegalArgumentException("No valid date given");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new DomainException("Date of a positive covid-19 test cannot be in the future.");
        }
        this.date = date;
    }
}
