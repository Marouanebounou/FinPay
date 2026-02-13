package org.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class Statistique {
    private int id;
    private BigDecimal totalAmount;
    private BigDecimal totalComission;
    private Date dateOp;
    private int id_pai;

    public Statistique( BigDecimal totalAmount, BigDecimal totalComission, int id_pai , Date dateOp) {
        this.dateOp = dateOp;
        this.totalAmount = totalAmount;
        this.totalComission = totalComission;
        this.id_pai = id_pai;
    }

    public Date getDateOp() {
        return dateOp;
    }

    public void setDateOp(Date dateOp) {
        this.dateOp = dateOp;
    }

    public int getId_pai() {
        return id_pai;
    }

    public void setId_pai(int id_pai) {
        this.id_pai = id_pai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalComission() {
        return totalComission;
    }

    public void setTotalComission(BigDecimal totalComission) {
        this.totalComission = totalComission;
    }
}
