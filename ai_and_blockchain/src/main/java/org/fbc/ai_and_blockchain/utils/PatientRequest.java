package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;

public class PatientRequest implements Serializable {
    private String requestId;
    private String requestInfo;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PatientRequest(String requestId, String requestInfo, int status) {
        this.requestId = requestId;
        this.requestInfo = requestInfo;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }
}
