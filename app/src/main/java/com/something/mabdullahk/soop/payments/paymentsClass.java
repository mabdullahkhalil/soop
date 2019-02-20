package com.something.mabdullahk.soop.payments;

public class paymentsClass {

    String month;
    String paidAt;
    String amountPaid;
    String amountRemaining;
    String status;

    public paymentsClass(String month, String paidAt, String amountPaid, String amountRemaining, String status) {
        this.month = month;
        this.paidAt = paidAt;
        this.amountPaid = amountPaid;
        this.amountRemaining = amountRemaining;
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(String paidAt) {
        this.paidAt = paidAt;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(String amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
