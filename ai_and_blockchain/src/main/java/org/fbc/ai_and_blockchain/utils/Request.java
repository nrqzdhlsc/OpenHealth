package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;
import java.math.BigInteger;

public class Request implements Serializable {
    private String requestId;
    private String owner;
    private String dataId;
    private BigInteger status;

    public Request(String requestId, String owner, String dataId, BigInteger status) {
        this.requestId = requestId;
        this.owner = owner;
        this.dataId = dataId;
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }
}
