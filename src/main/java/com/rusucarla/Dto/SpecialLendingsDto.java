package com.rusucarla.Dto;



import java.sql.Date;

public class SpecialLendingsDto {
    private int cid;
    private String name;
    private int bid;
    private String title;
    private Date return_date;
    private int penalty;

    public SpecialLendingsDto(int cid, String name, int bid, String title, Date return_date, int penalty) {
        this.cid = cid;
        this.name = name;
        this.bid = bid;
        this.title = title;
        this.return_date = return_date;
        this.penalty = penalty;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }
}
