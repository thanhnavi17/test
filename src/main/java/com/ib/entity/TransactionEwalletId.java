package com.ib.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionEwalletId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int trans_id;

    private String ewall_id;

    public int getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(int trans_id) {
        this.trans_id = trans_id;
    }

    public String getEwall_id() {
        return ewall_id;
    }

    public void setEwall_id(String ewall_id) {
        this.ewall_id = ewall_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionEwalletId)) return false;
        TransactionEwalletId that = (TransactionEwalletId) o;
        return getTrans_id() == that.getTrans_id() &&
                getEwall_id().equals(that.getEwall_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTrans_id(), getEwall_id());
    }
}
