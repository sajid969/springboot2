package com.rusucarla.Dto;



import java.sql.Date;

public class LendingsDto {
    private int id;
    private Date return_date;
    private int penalty;

    public LendingsDto(int id, Date return_date, int penalty) {
        this.id = id;
        this.return_date = return_date;
        this.penalty = penalty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
