package org.fbc.ai_and_blockchain.utils;

import java.io.Serializable;
import java.util.List;

public class DataSharingResponse implements Serializable {
    private String title;
    private List<String> allInfo;

    public DataSharingResponse() {
    }

    public DataSharingResponse(String title, List<String> allInfo) {
        this.title = title;
        this.allInfo = allInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAllInfo() {
        return allInfo;
    }

    public void setAllInfo(List<String> allInfo) {
        this.allInfo = allInfo;
    }
}
