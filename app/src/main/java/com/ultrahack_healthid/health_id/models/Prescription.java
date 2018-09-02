package com.ultrahack_healthid.health_id.models;

import java.util.Date;
import java.util.HashMap;

public class Prescription {

    private String id;
    private String recordId;
    private String status;

    private HashMap<String, Integer> medicine;

    public String getId() {
        return id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HashMap<String, Integer> getMedicine() {
        return medicine;
    }

    public void setMedicine(HashMap<String, Integer> medicine) {
        this.medicine = medicine;
    }

    public void setId(String id) {
        this.id = id;
    }
}
