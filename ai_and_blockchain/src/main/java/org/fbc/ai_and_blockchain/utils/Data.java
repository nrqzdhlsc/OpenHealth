package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;

public class Data implements Serializable {
    private String dataId;
    private String owner;
    private String relevant;
    private String dataIndex;

    public Data(String dataId, String owner, String relevant, String dataIndex) {
        this.dataId = dataId;
        this.owner = owner;
        this.relevant = relevant;
        this.dataIndex = dataIndex;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRelevant() {
        return relevant;
    }

    public void setRelevant(String relevant) {
        this.relevant = relevant;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }
}
