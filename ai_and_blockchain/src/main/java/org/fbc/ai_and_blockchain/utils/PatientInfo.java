package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;

public class PatientInfo implements Serializable {
    private String name;
    private String dataId;
    private String dataURL;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public PatientInfo(String name, String dataId, int status) {
        this.name = name;
        this.dataId = dataId;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
}
